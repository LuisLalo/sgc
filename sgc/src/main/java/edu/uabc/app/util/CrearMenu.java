package edu.uabc.app.util;

import java.util.List;

import edu.uabc.app.model.Menu;

public class CrearMenu {

	public static String menu(List<Menu> listaMenu, List<Menu> listaSubMenu, List<Menu> listaSubSubMenu/*, falta agregar la tabla de los permisos*/) {
		
		// Se buscan la cantidad de elementos que conforman el menu
		int size = listaMenu.size();
		
		// Se crean las variables con el contenido del código que crea el menu
		String nombre, liga, menuCompleto;
		String comienzo = "<!-- Menu de generado por base de datos -->\r\n" + 
				"<div class=\"container\">\r\n" + 
				"<nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\">" +
				"<a class=\"navbar-brand\" href=\"/sgc/\">\r\n" + 
				"                	<img src=\"/sgc/resources/images/ISOTIPO_Una-tinta_FONDO-OSCURO.png\" width=\"50\" height=\"30\" class=\"d-inline-block align-top\" alt=\"Inicio\">\r\n" + 
				"                </a>\r\n" + 
				"                <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarNavDropdown\" aria-controls=\"navbarNavDropdown\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\r\n" + 
				"                    <span class=\"navbar-toggler-icon\"></span>\r\n" + 
				"                </button>\r\n" + 
				"<div class=\"collapse navbar-collapse\" id=\"navbarNavDropdown\">\r\n" + 
				"                    <ul class=\"navbar-nav\">";
		String navItemDropdownInicio = "<li class=\"nav-item dropdown\">\r\n" + 
				"                            <a class=\"nav-link dropdown-toggle\" href=\"";
		String navItemDropdownMedio = "\" id=\"navbarDropdownMenuLink\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\r\n";
		String navItemDropdownFin = "</a>\r\n";
		String dropdownToggleInicio = "<li><a class=\"dropdown-item dropdown-toggle\" href=\"#\">";
		String dropdownToggleMedio = "\">";
		String dropdownToggleFin = "</a>";
		String dropdownItemInicio = "<ul class=\"dropdown-menu\">\r\n" + 
				"                                        <li><a class=\"dropdown-item\" href=\"";
		String dropdownItemmedio = "\">";
		String dropdownItemFin = "</a></li>\r\n";
		String dropdownItemUltimo = "</ul>\r\n" + 
				"								</li>\r\n";
		String navItemInicio = "<li class=\"nav-item\">\r\n" + 
				"                            <a class=\"nav-link\" href=\"";
		String navItemMedio = "\">";
		String navItemFin = "<span class=\"sr-only\">(current)</span></a>\r\n" + 
				"                        </li>\r\n";
		String fin = "</ul></nav></div>";
		menuCompleto = comienzo;
		for(int num=0;num<size;num++) {
			if(num!=0) {
				if(listaMenu.get(num).getIdSubmenu()==1) {
					nombre = listaMenu.get(num).getNombre();
					liga = listaMenu.get(num).getLiga();
					//System.out.println("Menu Crear nombre: " + nombre);
					//System.out.println("Menu Crear liga: " + liga);
					
					menuCompleto = menuCompleto.concat(navItemDropdownInicio);
					menuCompleto = menuCompleto.concat(liga);
					menuCompleto = menuCompleto.concat(navItemDropdownMedio);
					menuCompleto = menuCompleto.concat(nombre);
					menuCompleto = menuCompleto.concat(navItemDropdownFin);
				}
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
