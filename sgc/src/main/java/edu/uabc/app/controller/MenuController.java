package edu.uabc.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.uabc.app.model.Menu;
import edu.uabc.app.model.UsuarioConsulta;
import edu.uabc.app.service.IMenuService;
import edu.uabc.app.service.IUsuariosConsultaService;
import edu.uabc.app.util.CrearMenu;

@Controller
@RequestMapping("menu")
public class MenuController {
	
	@Autowired
	private IUsuariosConsultaService serviceUsuariosConsulta;
	
	@Autowired
	private IMenuService serviceMenu;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model, Authentication authentication) {
		// Se agrega el nombre del usuario
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		// Se agrega el menu generado por base de datos
		List<Menu> listaMenu = serviceMenu.buscarPorEstatusAndIdTipoVentanaOrderByOrden(1, 0);
		List<Menu> listaSubMenu = serviceMenu.buscarPorEstatusAndIdTipoVentanaOrderByOrden(1, 1);
		List<Menu> listaSubSubMenu = serviceMenu.buscarPorEstatusAndIdTipoVentanaOrderByOrden(1, 2);
		
		String menuCompleto = CrearMenu.menu(listaMenu, listaSubMenu, listaSubSubMenu);
		model.addAttribute("menuCompleto", menuCompleto);
		
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
		
		return "menu/formMenu";
	}
	
	@PostMapping("/guardar")
	public String guardarMenu(@ModelAttribute Menu menu, BindingResult result, RedirectAttributes attribute) {
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
		
		// Se agrega el menu generado por base de datos
		List<Menu> listaMenu = serviceMenu.buscarPorEstatusAndIdTipoVentanaOrderByOrden(1, 0);
		List<Menu> listaSubMenu = serviceMenu.buscarPorEstatusAndIdTipoVentanaOrderByOrden(1, 1);
		List<Menu> listaSubSubMenu = serviceMenu.buscarPorEstatusAndIdTipoVentanaOrderByOrden(1, 2);
		
		String menuCompleto = CrearMenu.menu(listaMenu, listaSubMenu, listaSubSubMenu);
		model.addAttribute("menuCompleto", menuCompleto);
		
		// Se busca la opción que seleccionó el usuario
		Menu opcion = serviceMenu.buscarPorId(idMenu);
		model.addAttribute("menu", opcion);
		
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
