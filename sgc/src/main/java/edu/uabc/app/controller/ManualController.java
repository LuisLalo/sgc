package edu.uabc.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.uabc.app.model.Departamento;
import edu.uabc.app.model.DocumentoConsulta;
import edu.uabc.app.model.Menu;
import edu.uabc.app.model.TipoDocumento;
import edu.uabc.app.model.UsuarioConsulta;
import edu.uabc.app.service.IDepartamentosService;
import edu.uabc.app.service.IDocumentosConsultaService;
import edu.uabc.app.service.IMenuService;
import edu.uabc.app.service.ITiposDocumentosService;
import edu.uabc.app.service.IUsuariosConsultaService;
import edu.uabc.app.util.CrearMenu;

@Controller
@RequestMapping("/manual")
public class ManualController {
	
	@Autowired
	private IDocumentosConsultaService serviceDocumentosConsulta;
	
	@Autowired
	private IUsuariosConsultaService serviceUsuariosConsulta;
	
	@Autowired
	private ITiposDocumentosService serviceTiposDocumentos;
	
	@Autowired
	private IDepartamentosService serviceDepartamentos;
	
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
		
		// Se identifica cual es el id del Manual de Gestión SGC
		TipoDocumento tipoDocumentoSGC = serviceTiposDocumentos.buscarPorNombre("Manual de Gestión SGC");
		System.out.println("TipoDocmento: " + tipoDocumentoSGC);
		// Se identifica cuale es el id del departamento de gestión de calidad
		Departamento departamento = serviceDepartamentos.buscarPorNombre("Seguimiento y Evaluación");
		System.out.println("Departamento: " + departamento);
		//Se buscan las partes que integran el Manual de Gestión que estén con estatus para publicación
		List<DocumentoConsulta> listaSGC = serviceDocumentosConsulta.buscarPorEstatusAndDepartamentoAndTipoDocumentoOrdenadoPorOrdenDocumento(100, departamento, tipoDocumentoSGC);
		System.out.println("Manual de Gestión: " + listaSGC);
		model.addAttribute("documentos", listaSGC);
		return "manual/manual";
	}
}
