package edu.uabc.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.uabc.app.model.DocumentoActualizar;
import edu.uabc.app.model.LineaAutorizacion;
import edu.uabc.app.model.UsuarioConsulta;
import edu.uabc.app.service.IDocumentosActualizarService;
import edu.uabc.app.service.ILineaAutorizacionService;
import edu.uabc.app.service.IUsuariosConsultaService;
import edu.uabc.app.util.Utileria;

@Controller
@RequestMapping("/reunion_trabajo")
public class ReunionTrabajoController {

	@Autowired
	private IDocumentosActualizarService serviceDocumentos;
	
	@Autowired
	private ILineaAutorizacionService serviceLineaAutorizacion;
	
	@Autowired
	private IUsuariosConsultaService serviceUsuariosConsulta;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model, Authentication authentication) {
		//Se agrega el nombre del usuario
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		List<DocumentoActualizar> listaDocumento = serviceDocumentos.buscarTodas();
		List<LineaAutorizacion> lineaAutorizacion = serviceLineaAutorizacion.buscarTodas();
		//List<DocumentoActualizar> lista = Utileria.identificarReunionTrabajo(listaDocumento, lineaAutorizacion.size());
		model.addAttribute("documentos", listaDocumento);
		return "reunion_trabajo/listReunionTrabajo";
	}
}
