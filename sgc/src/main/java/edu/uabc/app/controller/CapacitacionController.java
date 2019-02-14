package edu.uabc.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.uabc.app.model.DocumentoActualizar;
import edu.uabc.app.model.UsuarioConsulta;
import edu.uabc.app.service.IDocumentosActualizarService;
import edu.uabc.app.service.IUsuariosConsultaService;

@Controller
@RequestMapping("/capacitacion")
public class CapacitacionController {

	@Autowired
	private IDocumentosActualizarService serviceDocumentos;
	
	@Autowired
	private IUsuariosConsultaService serviceUsuariosConsulta;
	
	@GetMapping("/alta")
	public String mostrarAlta(Model model, Authentication authentication) {
		// Se agrega el nombre del usuario
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		List<DocumentoActualizar> lista = serviceDocumentos.buscarTodas();
		model.addAttribute("documentos", lista);
		return "capacitacion/alta-capacitacion";
	}
	
	@GetMapping("/registro")
	public String mostrarRegistro(Model model, Authentication authentication) {
		// Se agrega el nombre del usuario
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		List<DocumentoActualizar> lista = serviceDocumentos.buscarTodas();
		model.addAttribute("documentos", lista);
		return "capacitacion/registro-capacitacion";
	}
	
	@GetMapping("/consultar")
	public String mostrarConsultar(Model model, Authentication authentication) {
		// Se agrega el nombre del usuario
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		List<DocumentoActualizar> lista = serviceDocumentos.buscarTodas();
		model.addAttribute("documentos", lista);
		return "capacitacion/consultar-capacitacion";
	}
}
