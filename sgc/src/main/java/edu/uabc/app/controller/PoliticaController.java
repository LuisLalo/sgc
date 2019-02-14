package edu.uabc.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.uabc.app.model.UsuarioConsulta;
import edu.uabc.app.service.IUsuariosConsultaService;

@Controller
@RequestMapping("/politica")
public class PoliticaController {

	@Autowired
	private IUsuariosConsultaService serviceUsuariosConsulta;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model, Authentication authentication) {
		// Se agrega el nombre del usuario
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		return "politica/politica";
	}
}
