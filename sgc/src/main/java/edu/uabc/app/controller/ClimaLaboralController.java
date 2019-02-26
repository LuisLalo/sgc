package edu.uabc.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.uabc.app.model.DocumentoActualizar;
import edu.uabc.app.model.DocumentoConsulta;
import edu.uabc.app.model.LineaAutorizacion;
import edu.uabc.app.model.TipoDocumento;
import edu.uabc.app.model.UsuarioConsulta;
import edu.uabc.app.service.IDocumentosActualizarService;
import edu.uabc.app.service.IDocumentosConsultaService;
import edu.uabc.app.service.ILineaAutorizacionService;
import edu.uabc.app.service.ITiposDocumentosService;
import edu.uabc.app.service.IUsuariosConsultaService;
import edu.uabc.app.util.Utileria;

@Controller
@RequestMapping("/clima_laboral")
public class ClimaLaboralController {

	@Autowired
	private IDocumentosActualizarService serviceDocumentos;
	
	@Autowired
	private ILineaAutorizacionService serviceLineaAutorizacion;
	
	@Autowired
	private IUsuariosConsultaService serviceUsuariosConsulta;
	
	@Autowired
	private IDocumentosConsultaService serviceDocumentosConsulta;
	
	@Autowired
	private ITiposDocumentosService serviceTiposDocumentos;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model, Authentication authentication) {
		//Se agrega el nombre del usuario
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		/*
		List<DocumentoActualizar> listaDocumento = serviceDocumentos.buscarTodas();
		List<LineaAutorizacion> lineaAutorizacion = serviceLineaAutorizacion.buscarTodas();
		//List<DocumentoActualizar> lista = Utileria.identificarEncuestaClimaLaboral(listaDocumento, lineaAutorizacion.size());
		model.addAttribute("documentos", listaDocumento);*/
		
		TipoDocumento tipoDocumento = serviceTiposDocumentos.buscarPorNombre("Encuesta Clima Laboral");
		
		List<DocumentoConsulta> documentos = serviceDocumentosConsulta.buscarPorTipoDocumento(tipoDocumento);
		model.addAttribute("documentos", documentos);
		
		return "clima_laboral/listClimaLaboral";
	}
}
