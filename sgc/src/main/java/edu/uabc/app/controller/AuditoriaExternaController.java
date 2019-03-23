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
import edu.uabc.app.model.Menu;
import edu.uabc.app.model.TipoDocumento;
import edu.uabc.app.model.UsuarioConsulta;
import edu.uabc.app.service.IDocumentosActualizarService;
import edu.uabc.app.service.IDocumentosConsultaService;
import edu.uabc.app.service.ILineaAutorizacionService;
import edu.uabc.app.service.IMenuService;
import edu.uabc.app.service.ITiposDocumentosService;
import edu.uabc.app.service.IUsuariosConsultaService;
import edu.uabc.app.util.CrearMenu;
import edu.uabc.app.util.Utileria;

@Controller
@RequestMapping("/auditoria_externa")
public class AuditoriaExternaController {
	
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
	
	@Autowired
	private IMenuService serviceMenu;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model, Authentication authentication) {
		//Se agrega el nombre del usuario
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		// Se agrega el menu generado por base de datos
		List<Menu> listaMenu = serviceMenu.buscarPorEstatusAndIdTipoVentanaOrderByOrden(1, 0);
		List<Menu> listaSubMenu = serviceMenu.buscarPorEstatusAndIdTipoVentanaOrderByOrden(1, 1);
		List<Menu> listaSubSubMenu = serviceMenu.buscarPorEstatusAndIdTipoVentanaOrderByOrden(1, 2);
		
		String menuCompleto = CrearMenu.menu(listaMenu, listaSubMenu, listaSubSubMenu);
		model.addAttribute("menuCompleto", menuCompleto);
		
		/*
		List<DocumentoActualizar> listaDocumento = serviceDocumentos.buscarTodas();
		List<LineaAutorizacion> lineaAutorizacion = serviceLineaAutorizacion.buscarPorDepartamento(1);
		//List<DocumentoActualizar> lista = Utileria.identificarAuditoriaExterna(listaDocumento, lineaAutorizacion.size());
		model.addAttribute("documentos", listaDocumento);
		*/
		
		TipoDocumento tipoDocumento = serviceTiposDocumentos.buscarPorNombre("Auditoria Externa");
		
		List<DocumentoConsulta> documentos = serviceDocumentosConsulta.buscarPorTipoDocumento(tipoDocumento);
		model.addAttribute("documentos", documentos);
		
		return "auditoria_externa/listAuditoriaExterna";
	}
}
