package edu.uabc.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.uabc.app.model.Menu;
import edu.uabc.app.model.UsuarioConsulta;
import edu.uabc.app.service.IMenuService;
import edu.uabc.app.service.IUsuariosConsultaService;
import edu.uabc.app.util.CrearMenu;

@Controller
@RequestMapping("/mensajes")
public class MensajeController {

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
		List<Menu> listaMenu = serviceMenu.buscarPorEstatusAndTipoVentana(1, 0);
		List<Menu> listaSubMenu = serviceMenu.buscarPorEstatusAndTipoVentana(1, 1);
		List<Menu> listaSubSubMenu = serviceMenu.buscarPorEstatusAndTipoVentana(1, 2);
		
		String menu = CrearMenu.menu(listaMenu, listaSubMenu, listaSubSubMenu);
		model.addAttribute("menu", menu);
		
		return "mensajes/mensaje";
	}
	
	@GetMapping("/nuevo")
	public String mostrarNuevoMensaje(Model model, Authentication authentication) {
		// Se agrega el nombre del usuario
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		// Se agrega el menu generado por base de datos
		List<Menu> listaMenu = serviceMenu.buscarPorEstatusAndTipoVentana(1, 0);
		List<Menu> listaSubMenu = serviceMenu.buscarPorEstatusAndTipoVentana(1, 1);
		List<Menu> listaSubSubMenu = serviceMenu.buscarPorEstatusAndTipoVentana(1, 2);
		
		String menu = CrearMenu.menu(listaMenu, listaSubMenu, listaSubSubMenu);
		model.addAttribute("menu", menu);
		
		return "mensajes/nuevo-mensaje";
	}
	
	@GetMapping("/enviados")
	public String mostrarMensajeEnviado(Model model, Authentication authentication) {
		// Se agrega el nombre del usuario
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		// Se agrega el menu generado por base de datos
		List<Menu> listaMenu = serviceMenu.buscarPorEstatusAndTipoVentana(1, 0);
		List<Menu> listaSubMenu = serviceMenu.buscarPorEstatusAndTipoVentana(1, 1);
		List<Menu> listaSubSubMenu = serviceMenu.buscarPorEstatusAndTipoVentana(1, 2);
		
		String menu = CrearMenu.menu(listaMenu, listaSubMenu, listaSubSubMenu);
		model.addAttribute("menu", menu);
		
		return "mensajes/enviados";
	}
	
	@GetMapping("/recibidos")
	public String mostrarMensajeRecibido(Model model, Authentication authentication) {
		// Se agrega el nombre del usuario
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		// Se agrega el menu generado por base de datos
		List<Menu> listaMenu = serviceMenu.buscarPorEstatusAndTipoVentana(1, 0);
		List<Menu> listaSubMenu = serviceMenu.buscarPorEstatusAndTipoVentana(1, 1);
		List<Menu> listaSubSubMenu = serviceMenu.buscarPorEstatusAndTipoVentana(1, 2);
		
		String menu = CrearMenu.menu(listaMenu, listaSubMenu, listaSubSubMenu);
		model.addAttribute("menu", menu);
		
		return "mensajes/recibidos";
	}
}
