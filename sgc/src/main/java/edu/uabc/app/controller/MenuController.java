package edu.uabc.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.uabc.app.model.EstatusMenu;
import edu.uabc.app.model.Menu;
import edu.uabc.app.model.Permiso;
import edu.uabc.app.model.PermisoActualizar;
import edu.uabc.app.model.UsuarioConsulta;
import edu.uabc.app.service.IEstatusMenuService;
import edu.uabc.app.service.IMenuService;
import edu.uabc.app.service.IPermisoActualizarService;
import edu.uabc.app.service.IPermisoService;
import edu.uabc.app.service.IUsuariosConsultaService;
import edu.uabc.app.util.CrearMenu;

@Controller
@RequestMapping("menu")
public class MenuController {
	
	@Autowired
	private IUsuariosConsultaService serviceUsuariosConsulta;
	
	@Autowired
	private IMenuService serviceMenu;
	
	@Autowired
	private IPermisoService servicePermiso;
	
	@Autowired
	private IEstatusMenuService serviceEstatusMenu;
	
	@Autowired
	private IPermisoActualizarService servicePermisoActualizar;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model, Authentication authentication) {
		// Se agrega el nombre del usuario
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		System.out.println("numEmpleado antes del método: "+ usuarioAuth.getNum_empleado());
		
		// Se buscan los permisos a los que puede acceder el usuario
		List<Permiso> permiso = servicePermiso.buscarPorNumEmpleado(usuarioAuth.getNum_empleado());
		System.out.println("Permiso: "+ permiso);
		
		// Se buscan las opciones y secciones del menu generado por base de datos
		List<Menu> listaM = serviceMenu.buscarPorEstatusAndIdTipoVentanaOrderByOrden(1, 0);
		List<Menu> listaSM = serviceMenu.buscarPorEstatusAndIdTipoVentanaOrderByOrden(1, 1);
		List<Menu> listaSSM = serviceMenu.buscarPorEstatusAndIdTipoVentanaOrderByOrden(1, 2);
			
		// Se agrega el menu
		CrearMenu crearMenu = new CrearMenu();
		String menuCompleto = crearMenu.generarMenu(usuarioAuth.getNum_empleado(), permiso, listaM, listaSM, listaSSM);
		model.addAttribute("menuCompleto", menuCompleto);
				
		for(GrantedAuthority rol: authentication.getAuthorities()) {
			System.out.println("Rol: " + rol.getAuthority());
			//System.out.println("Menu: " + listaMenu);
		}
		
		// Se buscan las opciones del menu para mostrarse en la vista
		List<Menu> listaMenu = serviceMenu.buscarPorIdTipoVentana(0);
		
		System.out.println("listaMenu: " + listaMenu);
		model.addAttribute("listaMenu", listaMenu);
		return "menu/menu";
	}
	
	@GetMapping("/crear")
	public String crearMenu(@ModelAttribute Menu menu, Authentication authentication, Model model) {
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		// Se agrega el menu generado por base de datos
		List<Menu> listaMenu = serviceMenu.buscarPorEstatusAndIdTipoVentanaOrderByOrden(1, 0);
		List<Menu> listaSubMenu = serviceMenu.buscarPorEstatusAndIdTipoVentanaOrderByOrden(1, 1);
		List<Menu> listaSubSubMenu = serviceMenu.buscarPorEstatusAndIdTipoVentanaOrderByOrden(1, 2);
		
		String menuCompleto = CrearMenu.menu(listaMenu, listaSubMenu, listaSubSubMenu);
		model.addAttribute("menuCompleto", menuCompleto);
		
		List<EstatusMenu> estatusMenu = serviceEstatusMenu.buscarTodas();
		model.addAttribute("estatusMenu", estatusMenu);
		
		return "menu/formMenu";
	}
	
	@PostMapping("/guardar")
	public String guardarMenu(@ModelAttribute Menu menu, BindingResult result, RedirectAttributes attribute, Pageable page) {
		// Se identifica si hubo un error
		if (result.hasErrors()) {
			System.out.println("Existieron errores");
			
			return "menu/formMenu";
		}
		
		// Si la parte de orden viene en cero se agrega la siguiente al consecutivo ya que es la primera vez que se guarda en la BD
		if((menu.getOrden()==0) || (menu.getIdEstatus()==0)) {
			// Se busca el consecutivo que le toca a la nueva opción del menu
			List<Menu> lista = serviceMenu.buscarPorEstatusAndIdTipoVentanaOrderByOrden(1, 0);
			menu.setOrden(lista.size());
			
			// Se agregan los valores faltantes 
			menu.setLiga("#");
			menu.setIdTipoVentana(0);
			menu.setRelacion(0);
			System.out.println("menu: " + menu);
			
			// Se realiza la inserción en la tabla
			serviceMenu.insertar(menu);
			
			// Se cambia el orden de la opción "Cerrar Sesión"
			Menu menu1 = serviceMenu.buscarPorId(9);
			menu1.setOrden(lista.size()+1);
			serviceMenu.insertar(menu1);
			
			// Se tiene que dar el permiso a todos los usuarios para que aparezca la opción
			List<UsuarioConsulta> usuarioConsulta = serviceUsuariosConsulta.buscaTodo();
			for(int cont=0;cont<usuarioConsulta.size();cont++) {
				int consecutivo = serviceMenu.buscarTodas().size();
				
				PermisoActualizar permiso = new PermisoActualizar();
				permiso.setNumEmpleado(usuarioConsulta.get(cont).getNum_empleado());
				permiso.setIdMenu(consecutivo);
				permiso.setIdEstatus(1);
				
				servicePermisoActualizar.insertar(permiso);
				
				System.out.println("menu2: " + consecutivo);
				
			}
		}
		else if(menu.getOrden()!=0) {
			
			// Se obtiene el valor en el campo orden
			List<Menu> lista = serviceMenu.buscarPorIdTipoVentana(0);
			
			serviceMenu.insertar(menu);
			
			for(int cont = 0;cont<lista.size();cont++) {
				Menu menu1 = lista.get(cont);
				menu1.setOrden(cont+1);
				serviceMenu.insertar(menu1);
			}
			
			for(int cont = menu.getOrden();cont<lista.size();cont++) {
				Menu menu1 = lista.get(cont-1);
				menu1.setOrden(cont+1);
				serviceMenu.insertar(menu1);
			}
			
			// Se realiza la inserción en la tabla
			serviceMenu.insertar(menu);
		}
		
		attribute.addFlashAttribute("mensaje", "El registro fue guardado");
		
		return "redirect:/menu/index";
	}
	
	@PostMapping("/guardarSub")
	public String guardarSubMenu(@ModelAttribute Menu menu, BindingResult result, RedirectAttributes attribute) {
		// Se identifica si hubo un error
		if (result.hasErrors()) {
			System.out.println("Existieron errores");
			
			return "menu/formMenu";
		}
		
		// Se agregan los valores faltantes 
		menu.setLiga("#");
		menu.setIdTipoVentana(0);
		menu.setRelacion(0);
		menu.setOrden(9);
		System.out.println("menu: " + menu);
		// Se realiza la inserción en la tabla
		serviceMenu.insertar(menu);
		
		attribute.addFlashAttribute("mensaje", "El registro fue guardado");
		
		return "redirect:/menu/index";
	}
	
	@GetMapping("/editar/{id}")
	public String editarMenu(@PathVariable("id") int idMenu, @ModelAttribute Menu menu, Model model, Authentication authentication) {
		// Se agrega el nombre del usuario
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		System.out.println("numEmpleado antes del método: "+ usuarioAuth.getNum_empleado());
		
		// Se buscan los permisos a los que puede acceder el usuario
		List<Permiso> permiso = servicePermiso.buscarPorNumEmpleado(usuarioAuth.getNum_empleado());
		System.out.println("Permiso: "+ permiso);
		
		// Se buscan las opciones y secciones del menu generado por base de datos
		List<Menu> listaM = serviceMenu.buscarPorEstatusAndIdTipoVentanaOrderByOrden(1, 0);
		List<Menu> listaSM = serviceMenu.buscarPorEstatusAndIdTipoVentanaOrderByOrden(1, 1);
		List<Menu> listaSSM = serviceMenu.buscarPorEstatusAndIdTipoVentanaOrderByOrden(1, 2);
		
		// Se agrega el menu
		CrearMenu crearMenu = new CrearMenu();
		String menuCompleto = crearMenu.generarMenu(usuarioAuth.getNum_empleado(), permiso, listaM, listaSM, listaSSM);
		model.addAttribute("menuCompleto", menuCompleto);
		
		// Se busca la opción que seleccionó el usuario
		Menu opcion = serviceMenu.buscarPorId(idMenu);
		model.addAttribute("menu", opcion);
		
		List<EstatusMenu> estatusMenu = serviceEstatusMenu.buscarTodas();
		model.addAttribute("estatusMenu", estatusMenu);
		
		return "menu/subMenu";
	}
	
	@GetMapping("/opcion/{id}")
	public String opcionesMenu(@PathVariable("id") int idMenu, @ModelAttribute Menu menu, Model model, Authentication authentication) {
		// Se agrega el nombre del usuario
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		// Se agrega el menu generado por base de datos
		List<Menu> listaMenu = serviceMenu.buscarPorEstatusAndIdTipoVentanaOrderByOrden(1, 0);
		List<Menu> listaSubMenu = serviceMenu.buscarPorEstatusAndIdTipoVentanaOrderByOrden(1, 1);
		List<Menu> listaSubSubMenu = serviceMenu.buscarPorEstatusAndIdTipoVentanaOrderByOrden(1, 2);
		
		String menuCompleto = CrearMenu.menu(listaMenu, listaSubMenu, listaSubSubMenu);
		model.addAttribute("menuCompleto", menuCompleto);
		
		// Se busca el nombre de la opción para mostrar el nombre dentro de la vista
		Menu opcion = serviceMenu.buscarPorId(idMenu);
		model.addAttribute("modelo", opcion);
		System.out.println("opcion: " + opcion);
		
		// Se agrega el submenu de la opción seleccionada
		List<Menu> listaSubMenuDos = serviceMenu.buscarPorEstatusAndIdTipoVentanaAndRelacion(1, 1, idMenu);
		System.out.println("idMenu: " + idMenu);
		System.out.println("listaSubMenuDos: " + listaSubMenuDos);
		model.addAttribute("listaSubMenuDos", listaSubMenuDos);

		List<EstatusMenu> estatusMenu = serviceEstatusMenu.buscarTodas();
		model.addAttribute("estatusMenu", estatusMenu);
		
		return "menu/opcionSubMenu";
	}
	
	@GetMapping("/opcion/{id}/seccion")
	public String opcionesSubMenu(@PathVariable("id") int idMenu, @ModelAttribute Menu menu, Model model, Authentication authentication) {
		// Se agrega el nombre del usuario
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		System.out.println("numEmpleado antes del método: "+ usuarioAuth.getNum_empleado());
		
		// Se buscan los permisos a los que puede acceder el usuario
		List<Permiso> permiso = servicePermiso.buscarPorNumEmpleado(usuarioAuth.getNum_empleado());
		System.out.println("Permiso: "+ permiso);
		
		// Se buscan las opciones y secciones del menu generado por base de datos
		List<Menu> listaM = serviceMenu.buscarPorEstatusAndIdTipoVentanaOrderByOrden(1, 0);
		List<Menu> listaSM = serviceMenu.buscarPorEstatusAndIdTipoVentanaOrderByOrden(1, 1);
		List<Menu> listaSSM = serviceMenu.buscarPorEstatusAndIdTipoVentanaOrderByOrden(1, 2);
		
		// Se agrega el menu
		CrearMenu crearMenu = new CrearMenu();
		String menuCompleto = crearMenu.generarMenu(usuarioAuth.getNum_empleado(), permiso, listaM, listaSM, listaSSM);
		model.addAttribute("menuCompleto", menuCompleto);
		
		for(GrantedAuthority rol: authentication.getAuthorities()) {
			System.out.println("Rol: " + rol.getAuthority());
			//System.out.println("Menu: " + listaMenu);
		}
		
		// Se busca el nombre de la opción para mostrar el nombre dentro de la vista
		Menu opcion = serviceMenu.buscarPorId(idMenu);
		model.addAttribute("modelo", opcion);
		System.out.println("opcion: " + opcion);
		
		// Se agrega el submenu de la opción seleccionada
		List<Menu> listaSubMenuDos = serviceMenu.buscarPorEstatusAndIdTipoVentanaAndRelacion(1, 2, idMenu);
		System.out.println("idMenu: " + idMenu);
		System.out.println("listaSubMenuDos: " + listaSubMenuDos);
		model.addAttribute("listaSubMenuDos", listaSubMenuDos);

		return "menu/opcionSubMenu";
	}
	
	@GetMapping("/opcion/{id}/crear")
	public String crearSubMenu(@PathVariable("id") int idMenu, @ModelAttribute Menu menu, Authentication authentication, Model model) {
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		System.out.println("idMenu: " + idMenu);
		// Se agrega el menu generado por base de datos
		List<Menu> listaMenu = serviceMenu.buscarPorEstatusAndIdTipoVentanaOrderByOrden(1, 0);
		List<Menu> listaSubMenu = serviceMenu.buscarPorEstatusAndIdTipoVentanaOrderByOrden(1, 1);
		List<Menu> listaSubSubMenu = serviceMenu.buscarPorEstatusAndIdTipoVentanaOrderByOrden(1, 2);
		
		// Se busca el nombre de la opción para mostrar el nombre dentro de la vista
		Menu opcion = serviceMenu.buscarPorId(idMenu);
		model.addAttribute("modelo", opcion);
		
		String menuCompleto = CrearMenu.menu(listaMenu, listaSubMenu, listaSubSubMenu);
		model.addAttribute("menuCompleto", menuCompleto);
		
		List<EstatusMenu> estatusMenu = serviceEstatusMenu.buscarTodas();
		model.addAttribute("estatusMenu", estatusMenu);
		
		return "menu/formSubMenu";
	}
	
	@PostMapping("/opcion/{id}/guardar")
	public String guardarSubMenu(@ModelAttribute Menu menu, @PathVariable("id") int idMenu, BindingResult result, RedirectAttributes attribute) {
		// Se identifica si hubo un error
		if (result.hasErrors()) {
			System.out.println("Existieron errores");
			
			return "menu/formSubMenu";
		}
		
		// Se agregan los valores faltantes 
		String liga = menu.getNombre();
		liga = liga.replaceAll("de", "");
		liga = liga.replaceAll(" ", "_");
		menu.setLiga(liga);
		menu.setIdTipoVentana(1);
		menu.setRelacion(idMenu);
		
		// Se busca el consecutivo que le corresponde a la nueva opción
		Menu opcion = serviceMenu.buscarPorId(idMenu);
		List<Menu> lista = serviceMenu.buscarPorEstatusAndIdTipoVentanaAndRelacionOrderByOrden(opcion.getIdEstatus(), opcion.getIdTipoVentana(), opcion.getRelacion());
		
		int contador = 0;
		
		do {
			contador++;
		} while (contador<lista.size());
		
		menu.setOrden(contador);
		
		System.out.println("menu: " + menu);
		// Se realiza la inserción en la tabla
		serviceMenu.insertar(menu);
		
		attribute.addFlashAttribute("mensaje", "El registro fue guardado");
		
		return "redirect:/menu/opcion/{id}";
	}
}
