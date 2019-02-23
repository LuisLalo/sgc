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
@RequestMapping("/plan_accion")
public class PlanAccionController {

	@Autowired
	private IUsuariosConsultaService serviceUsuariosConsulta;
	
	@Autowired 
	IDepartamentosService serviceDepartamentos;
	
	@Autowired
	ITiposDocumentosService serviceTiposDocumentos;
	
	@Autowired
	private IDocumentosConsultaService serviceDocumentosConsulta;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model, Authentication authentication) {
		// Se agrega el nombre del usuario
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		List<Departamento> listaDepartamento = serviceDepartamentos.buscarTodas();
		model.addAttribute("departamentos", listaDepartamento);
		
		return "plan_accion/listPlanAccion";
	}
	
	@GetMapping("/{id}")
	public String mostrar(@PathVariable ("id") int idDepartamento, Model model, Authentication authentication) {
		//Se agrega el nombre del usuario
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		Departamento departamento = serviceDepartamentos.buscarPorId(idDepartamento);
		TipoDocumento tipoDocumento = serviceTiposDocumentos.buscarPorNombre("Plan de Acción");
		
		List<DocumentoConsulta> listaDocumento = serviceDocumentosConsulta.buscarPorEstatusAndDepartamentoAndTipoDocumento(100, departamento, tipoDocumento);
		model.addAttribute("departamentos", departamento);
		model.addAttribute("documentos", listaDocumento);
		
		return "/plan_accion/listPlanAccionDepartamentos";
	}
}
