package edu.uabc.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.uabc.app.model.Departamento;
import edu.uabc.app.model.DocumentoConsulta;
import edu.uabc.app.model.TipoDocumento;
import edu.uabc.app.model.UsuarioConsulta;
import edu.uabc.app.service.IDepartamentosService;
import edu.uabc.app.service.IDocumentosConsultaService;
import edu.uabc.app.service.ITiposDocumentosService;
import edu.uabc.app.service.IUsuariosConsultaService;

@Controller
@RequestMapping("/gestion_conocimiento")
public class GestionConocimientoController {

	@Autowired
	private IUsuariosConsultaService serviceUsuariosConsulta;
	
	@Autowired
	private IDepartamentosService serviceDepartamentos;
	
	@Autowired
	ITiposDocumentosService serviceTiposDocumentos;
	
	@Autowired
	IDocumentosConsultaService serviceDocumentosConsulta;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model, Authentication authentication) {
		// Se agrega el nombre del usuario
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		// Se agregan los departamentos
		List<Departamento> departamento = serviceDepartamentos.buscarTodas();
		model.addAttribute("departamentos", departamento);
		return "gestion_conocimiento/listGestionConocimiento";
	}
	
	@GetMapping("/{id}")
	public String mostrarGestionConocimiento(@PathVariable("id") int id_departamento, Model model, Authentication authentication) {
		// Se agrega el nombre del usuario
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		//Se busca el tipo de documento
		TipoDocumento tipoDocumento = serviceTiposDocumentos.buscarPorNombre("Gesti�n del Conocimiento");
		
		//Nombre del departamento
		Departamento departamento = serviceDepartamentos.buscarPorId(id_departamento);
		model.addAttribute("departamentos", departamento);
		
		//Se crea la lista con los procedimientos guardados
		List<DocumentoConsulta> documento = serviceDocumentosConsulta.buscarPorEstatusAndDepartamentoAndTipoDocumento(100, departamento, tipoDocumento);
		model.addAttribute("documentos", documento);
		return "gestion_conocimiento/listGestionConocimientoDepartamentos";
	}
}
