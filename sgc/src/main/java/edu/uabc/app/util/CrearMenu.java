package edu.uabc.app.util;

import java.util.List;

import edu.uabc.app.model.Menu;

public class CrearMenu {

	public static String menu(List<Menu> listaMenu, List<Menu> listaSubMenu, List<Menu> listaSubSubMenu/*, falta agregar la tabla de los permisos*/) {
		
		// Se buscan la cantidad de elementos que conforman el menu
		int sizeListaMenu = listaMenu.size();
		int sizeListaSubMenu = listaSubMenu.size();
		int sizeListaSubSubMenu = listaSubSubMenu.size();
		
		// Se crean las variables con el contenido del código que crea el menu
		String nombre, liga, menuCompleto;
		String comienzo = "<!-- Menu de generado por base de datos -->\r\n" + 
				"<div class=\"container\">\r\n" + 
				"	<nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\">\r\n" +
				"		<a class=\"navbar-brand\" href=\"/sgc/\"><img src=\"/sgc/resources/images/ISOTIPO_Una-tinta_FONDO-OSCURO.png\" width=\"50\" height=\"30\" class=\"d-inline-block align-top\" alt=\"Inicio\"></a>\r\n" + 
				"        <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarNavDropdown\" aria-controls=\"navbarNavDropdown\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\r\n" + 
				"       		<span class=\"navbar-toggler-icon\"></span>\r\n" + 
				"        </button>\r\n" + 
				"		<div class=\"collapse navbar-collapse\" id=\"navbarNavDropdown\">\r\n" + 
				"       		<ul class=\"navbar-nav\">\r\n";
		
		String navItemInicio = "       			<li class=\"nav-item\">\r\n" + 
				"       				<a class=\"nav-link\" href=\"";
		String navItemMedio = "\">";
		String navItemFin = "<span class=\"sr-only\">(current)</span></a>\r\n" + 
				"       			</li>\r\n";
		
		String navItemDropdownInicio = "       			<li class=\"nav-item dropdown\">\r\n" + 
				"       	       		<a class=\"nav-link dropdown-toggle\" href=\"";
		String navItemDropdownMedio = "\" id=\"navbarDropdownMenuLink\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">";
		String navItemDropdownFin = "</a>\r\n";
		
		String dropdownToggleInicio = "       					<li><a class=\"dropdown-item dropdown-toggle\" href=\"";
		String dropdownToggleMedio = "\">";
		String dropdownToggleFin = "</a>\r\n" + 
				"       						<ul class=\"dropdown-menu\">\r\n";
		
		String dropdownItemInicio = "       				<ul class=\"dropdown-menu\" aria-labelledby=\"navbarDropdownMenuLink\">\r\n";
		String dropdownItemInicioDos = "       					<li><a class=\"dropdown-item\" href=\"";
		String dropdownItemMedio = "\">";
		String dropdownItemFin = "</a></li>\r\n";
		String dropdownItemUltimo = "       				</ul>\r\n";
		String dropdownItemUltimoFin = "       			</li>\r\n";
		
		String dropdownItemSubSubMenuInicio = "       							<li><a class=\"dropdown-item\" href=\"";
		String dropdownItemSubSubMenuMedio = "\">";
		String dropdownItemSubSubMenuFin = "</a></li>\r\n";
		String dropdownItemSubSubMenuUltimo = "       						</ul>\r\n";
	//	String dropdownItemSubSubMenuUltimoFin = "</ul>\r\n" + 
	//			"								</li>\r\n";
		String fin = "</ul></nav></div>";
		menuCompleto = comienzo;
		for(int num=0;num<sizeListaMenu;num++) {
			if(num!=0) {
				// Se buscan las opciones del menu que tienen submenu 
				if(listaMenu.get(num).getIdSubmenu()==1) {
					nombre = listaMenu.get(num).getNombre();
					liga = listaMenu.get(num).getLiga();
					
					menuCompleto = menuCompleto.concat(navItemDropdownInicio);
					menuCompleto = menuCompleto.concat(liga);
					menuCompleto = menuCompleto.concat(navItemDropdownMedio);
					menuCompleto = menuCompleto.concat(nombre);
					menuCompleto = menuCompleto.concat(navItemDropdownFin);
					
					// Contador del submenu
					int contador=0;
					//int contadorUno=0;
					// Se agregan los submenus
					for(int cont=0;cont<sizeListaSubMenu;cont++) {
						// Se identifica la relación del submenu con el menu
						if(listaSubMenu.get(cont).getRelacion()==num+1) {
							nombre = listaSubMenu.get(cont).getNombre();
							liga = listaSubMenu.get(cont).getLiga();
							
							// Se identifica si el submenu tiene sub-submenu
							if(listaSubMenu.get(cont).getIdSubmenu()==1) {
								// Se identifica si el submenu es el primero en la lista
								if(contador==0) {
									menuCompleto = menuCompleto.concat(dropdownToggleInicio);
									menuCompleto = menuCompleto.concat(liga);
									menuCompleto = menuCompleto.concat(dropdownToggleMedio);
									menuCompleto = menuCompleto.concat(nombre);
									menuCompleto = menuCompleto.concat(dropdownToggleFin);
									contador++;
									
									// Se agrega el sub-submenu
									for (int contUno=0;contUno<sizeListaSubSubMenu;contUno++) {
										// Se identifica la relación del sub-submenu con el submenu
										System.out.println(listaSubMenu.get(cont).getIdMenu());
										System.out.println(listaSubSubMenu.get(contUno).getRelacion());
										if(listaSubMenu.get(cont).getIdMenu()==listaSubSubMenu.get(contUno).getRelacion()) {
											nombre = listaSubSubMenu.get(contUno).getNombre();
											liga = listaSubSubMenu.get(contUno).getLiga();
											
											// Se agrega el código html con el sub-submenu
											menuCompleto = menuCompleto.concat(dropdownItemSubSubMenuInicio);
											menuCompleto = menuCompleto.concat(liga);
											menuCompleto = menuCompleto.concat(dropdownItemSubSubMenuMedio);
											menuCompleto = menuCompleto.concat(nombre);
											menuCompleto = menuCompleto.concat(dropdownItemSubSubMenuFin);
											
										}
										
										// Se identifica si es el último sub-submenu para agregar la etiqueta de cierre
										if(contUno==sizeListaSubSubMenu-1) {
											menuCompleto = menuCompleto.concat(dropdownItemSubSubMenuUltimo);
										}
									}
								}
								// El submenu no es el primero de la lista
								else {
									menuCompleto = menuCompleto.concat(dropdownToggleInicio);
									menuCompleto = menuCompleto.concat(liga);
									menuCompleto = menuCompleto.concat(dropdownToggleMedio);
									menuCompleto = menuCompleto.concat(nombre);
									menuCompleto = menuCompleto.concat(dropdownToggleFin);
									
									// Se agrega el sub-submenu
									for (int contUno=0;contUno<sizeListaSubSubMenu;contUno++) {
										// Se identifica la relación del sub-submenu con el submenu
										System.out.println("id subMenu:" + listaSubMenu.get(cont).getIdMenu());
										System.out.println("id sub-submenu relación: " + listaSubSubMenu.get(contUno).getRelacion());
										if(listaSubMenu.get(cont).getIdMenu()==listaSubSubMenu.get(contUno).getRelacion()) {
											nombre = listaSubSubMenu.get(contUno).getNombre();
											liga = listaSubSubMenu.get(contUno).getLiga();
											
											// Se agrega el código html con el sub-submenu
											menuCompleto = menuCompleto.concat(dropdownItemSubSubMenuInicio);
											menuCompleto = menuCompleto.concat(liga);
											menuCompleto = menuCompleto.concat(dropdownItemSubSubMenuMedio);
											menuCompleto = menuCompleto.concat(nombre);
											menuCompleto = menuCompleto.concat(dropdownItemSubSubMenuFin);
											
										}
										
										// Se identifica si es el último sub-submenu para agregar la etiqueta de cierre
										if(contUno==sizeListaSubSubMenu-1) {
											menuCompleto = menuCompleto.concat(dropdownItemSubSubMenuUltimo);
										}
									}
								}
							}
							// Se identifica si el submenu no tiene sub-submenu
							else {
								// Se identifica si el submenu es el primero en la lista
								if(contador==0) {
									//System.out.println("Contador cont fuera de if: " + cont);
									menuCompleto = menuCompleto.concat(dropdownItemInicio);
									menuCompleto = menuCompleto.concat(dropdownItemInicioDos);
									menuCompleto = menuCompleto.concat(liga);
									menuCompleto = menuCompleto.concat(dropdownItemMedio);
									menuCompleto = menuCompleto.concat(nombre);
									menuCompleto = menuCompleto.concat(dropdownItemFin);
									contador++;
								}
								// El submenu no es el primedo de la lista
								else {
									menuCompleto = menuCompleto.concat(dropdownItemInicioDos);
									menuCompleto = menuCompleto.concat(liga);
									menuCompleto = menuCompleto.concat(dropdownItemMedio);
									menuCompleto = menuCompleto.concat(nombre);
									menuCompleto = menuCompleto.concat(dropdownItemFin);
								}
							}
						}
						 // Se identifica si el elemento es el último de la lista
						if(cont==sizeListaSubMenu-1) {
							if(contador==0) {
								menuCompleto = menuCompleto.concat(dropdownItemUltimoFin);
							//System.out.println("Contador cont dentro de if: " + cont);
							//System.out.println("Tamaño de lista SubMenu: " + sizeListaSubMenu);
							}
							else {
								menuCompleto = menuCompleto.concat(dropdownItemUltimo);
								menuCompleto = menuCompleto.concat(dropdownItemUltimoFin);
							}
						}
					}
				}
				// Opciones del menu que no tienen submenu
				else {
					menuCompleto = menuCompleto.concat(navItemInicio);
					nombre = listaMenu.get(num).getNombre();
					liga = listaMenu.get(num).getLiga();
					menuCompleto = menuCompleto.concat(liga);
					menuCompleto = menuCompleto.concat(navItemMedio);
					menuCompleto = menuCompleto.concat(nombre);
					menuCompleto = menuCompleto.concat(navItemFin);
				}
			}
		}
		menuCompleto = menuCompleto.concat(fin);
		System.out.println("menuCompleto: " + menuCompleto);
		
		return menuCompleto;
	}
}
