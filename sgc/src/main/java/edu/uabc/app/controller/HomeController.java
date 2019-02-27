package edu.uabc.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.uabc.app.model.Menu;
import edu.uabc.app.model.UsuarioConsulta;
import edu.uabc.app.service.IMenuService;
import edu.uabc.app.service.IUsuariosConsultaService;

@Controller
public class HomeController {
	
	@Autowired
	private IUsuariosConsultaService serviceUsuariosConsulta;
	
	@Autowired
	private IMenuService serviceMenu;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String mostrarLogin() {
		return "login";
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String mostrarPrincipal(Authentication authentication, Model model) {
		System.out.println("Username: " + authentication.getName());
		System.out.println("Username: " + authentication.getDetails());
		System.out.println("Username: " + authentication.toString());
		System.out.println("Username: " + authentication.getCredentials());
		System.out.println("Username: " + authentication.getClass());
		System.out.println("Username: " + authentication.getPrincipal());
		
		// Se agrega el nombre del usuario
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		List<Menu> listaMenu = serviceMenu.buscarPorEstatus(1);
		model.addAttribute("menu", listaMenu);
		
		
		for(GrantedAuthority rol: authentication.getAuthorities()) {
			System.out.println("Rol: " + rol.getAuthority());
			System.out.println("Menu: " + listaMenu);
		}
		
		
		
		return "home";
	}
	
	@RequestMapping(value="/documentos-autorizar", method=RequestMethod.GET)
	public String mostrarDocumentosAutorizar() {
		return "documentos-autorizar";
	}
	
	@RequestMapping(value="/menu", method=RequestMethod.GET)
	public String mostrarMenu() {
		return "menu";
	}
	
	@RequestMapping(value="/alta-capacitacion", method=RequestMethod.GET)
	public String mostrarAltaCapacitacion() {
		return "alta-capacitacion";
	}
	
	@RequestMapping(value="/registro-capacitacion", method=RequestMethod.GET)
	public String mostrarRegistroCapacitacion() {
		return "registro-capacitacion";
	}
	
	@RequestMapping(value="/consultar-capacitacion", method=RequestMethod.GET)
	public String mostrarConsultarCapacitacion() {
		return "consultar-capacitacion";
	}
	
	@RequestMapping(value="/convocatoria", method=RequestMethod.GET)
	public String mostrarConvocatoria() {
		return "convocatoria";
	}
}
