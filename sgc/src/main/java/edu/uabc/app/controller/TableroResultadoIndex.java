package edu.uabc.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.uabc.app.model.UsuarioConsulta;
import edu.uabc.app.model.UsuarioDocumento;
import edu.uabc.app.service.IDocumentosConsultaService;
import edu.uabc.app.service.IUsuarioDocumentoService;
import edu.uabc.app.service.IUsuariosConsultaService;

@Controller
@RequestMapping("/tablero_resultados")
public class TableroResultadoIndex {

	@Autowired
	private IUsuariosConsultaService serviceUsuariosConsulta;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model, Authentication authentication) {
		// Se agrega el nombre del usuario
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
				
		return "tablero_resultados/tablero_resultados";
	}
}
