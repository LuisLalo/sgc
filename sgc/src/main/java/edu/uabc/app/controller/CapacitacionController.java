package edu.uabc.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.uabc.app.model.DocumentoActualizar;
import edu.uabc.app.model.Menu;
import edu.uabc.app.model.UsuarioConsulta;
import edu.uabc.app.service.IDocumentosActualizarService;
import edu.uabc.app.service.IMenuService;
import edu.uabc.app.service.IUsuariosConsultaService;
import edu.uabc.app.util.CrearMenu;

@Controller
@RequestMapping("/capacitacion")
public class CapacitacionController {

	@Autowired
	private IDocumentosActualizarService serviceDocumentos;
	
	@Autowired
	private IUsuariosConsultaService serviceUsuariosConsulta;
	
	@Autowired
	private IMenuService serviceMenu;
	
	@GetMapping("/alta")
	public String mostrarAlta(Model model, Authentication authentication) {
		// Se agrega el nombre del usuario
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		// Se agrega el menu generado por base de datos
		List<Menu> listaMenu = serviceMenu.buscarPorEstatusAndTipoVentana(1, 0);
		List<Menu> listaSubMenu = serviceMenu.buscarPorEstatusAndTipoVentana(1, 1);
		List<Menu> listaSubSubMenu = serviceMenu.buscarPorEstatusAndTipoVentana(1, 2);
		
		String menu = CrearMenu.menu(listaMenu, listaSubMenu, listaSubSubMenu);
		model.addAttribute("menu", menu);
		
		List<DocumentoActualizar> lista = serviceDocumentos.buscarTodas();
		model.addAttribute("documentos", lista);
		return "capacitacion/alta-capacitacion";
	}
	
	@GetMapping("/registro")
	public String mostrarRegistro(Model model, Authentication authentication) {
		// Se agrega el nombre del usuario
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		// Se agrega el menu generado por base de datos
		List<Menu> listaMenu = serviceMenu.buscarPorEstatusAndTipoVentana(1, 0);
		List<Menu> listaSubMenu = serviceMenu.buscarPorEstatusAndTipoVentana(1, 1);
		List<Menu> listaSubSubMenu = serviceMenu.buscarPorEstatusAndTipoVentana(1, 2);
		
		String menu = CrearMenu.menu(listaMenu, listaSubMenu, listaSubSubMenu);
		model.addAttribute("menu", menu);
		
		List<DocumentoActualizar> lista = serviceDocumentos.buscarTodas();
		model.addAttribute("documentos", lista);
		return "capacitacion/registro-capacitacion";
	}
	
	@GetMapping("/consultar")
	public String mostrarConsultar(Model model, Authentication authentication) {
		// Se agrega el nombre del usuario
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		// Se agrega el menu generado por base de datos
		List<Menu> listaMenu = serviceMenu.buscarPorEstatusAndTipoVentana(1, 0);
		List<Menu> listaSubMenu = serviceMenu.buscarPorEstatusAndTipoVentana(1, 1);
		List<Menu> listaSubSubMenu = serviceMenu.buscarPorEstatusAndTipoVentana(1, 2);
		
		String menu = CrearMenu.menu(listaMenu, listaSubMenu, listaSubSubMenu);
		model.addAttribute("menu", menu);
		
		List<DocumentoActualizar> lista = serviceDocumentos.buscarTodas();
		model.addAttribute("documentos", lista);
		return "capacitacion/consultar-capacitacion";
	}
}
