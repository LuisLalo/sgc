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
import edu.uabc.app.model.Menu;
import edu.uabc.app.model.Permiso;
import edu.uabc.app.model.TipoDocumento;
import edu.uabc.app.model.UsuarioConsulta;
import edu.uabc.app.model.UsuarioDocumento;
import edu.uabc.app.service.IDepartamentosService;
import edu.uabc.app.service.IDocumentosConsultaService;
import edu.uabc.app.service.IMenuService;
import edu.uabc.app.service.IPermisoService;
import edu.uabc.app.service.ITiposDocumentosService;
import edu.uabc.app.service.IUsuarioDocumentoService;
import edu.uabc.app.service.IUsuariosConsultaService;
import edu.uabc.app.util.CrearMenu;

@Controller
@RequestMapping("/tablero_resultados")
public class TableroResultadoIndex {

	@Autowired
	private IUsuariosConsultaService serviceUsuariosConsulta;
	
	@Autowired 
	IDepartamentosService serviceDepartamentos;
	
	@Autowired
	ITiposDocumentosService serviceTiposDocumentos;
	
	@Autowired
	private IDocumentosConsultaService serviceDocumentosConsulta;
	
	@Autowired
	private IMenuService serviceMenu;
	
	@Autowired
	private IPermisoService servicePermiso;
	
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
		
		// Se busca el tipo de documento 
		TipoDocumento tipoDocumento = serviceTiposDocumentos.buscarPorNombre("Tablero de resultados");
		System.out.println(tipoDocumento);
		
		// Se busca el departamento que subió el documento
		Departamento departamento = serviceDepartamentos.buscarPorNombre("Seguimiento y Evaluación");
		System.out.println(departamento);
		
		// Se busca el listado de documentos que cumplen con el criterio
		List<DocumentoConsulta> listaDocumento = serviceDocumentosConsulta.buscarPorEstatusAndDepartamentoAndTipoDocumentoOrdenadoPorOrdenDocumento(100, departamento, tipoDocumento);
		model.addAttribute("documentos", listaDocumento);
		System.out.println(listaDocumento);
				
		return "tablero_resultados/tablero_resultados";
	}
	
	@GetMapping("/{id}")
	public String mostrar(@PathVariable ("id") int idDepartamento, Model model, Authentication authentication) {
		// Se agrega el nombre del usuario
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		System.out.println("numEmpleado antes del método: "+ usuarioAuth.getNum_empleado());
		
		// Se buscan los permisos a los que puede acceder el usuario
		List<Permiso> permiso = servicePermiso.buscarPorNumEmpleado(usuarioAuth.getNum_empleado());
		System.out.println("Permiso: "+ permiso);
		
		// Se buscan las opciones y secciones del menu generado por base de datos
		List<Menu> listaM = serviceMenu.buscarPorEstatusAndIdTipoVentanaOrderByOrden(1, 0);
		List<Menu> listaSM = serviceMenu.buscarPorEstatusAndIdTipoVentanaOrderByOrden(1, 1);
		List<Menu> listaSSM = serviceMenu.buscarPorEstatusAndIdTipoVentanaOrderByOrden(1, 2);
		
		// Se agrega el menu
		CrearMenu crearMenu = new CrearMenu();
		String menuCompleto = crearMenu.generarMenu(usuarioAuth.getNum_empleado(), permiso, listaM, listaSM, listaSSM);
		model.addAttribute("menuCompleto", menuCompleto);
				
		Departamento departamento = serviceDepartamentos.buscarPorId(idDepartamento);
		TipoDocumento tipoDocumento = serviceTiposDocumentos.buscarPorNombre("Indicadores");
		
		List<DocumentoConsulta> listaDocumento = serviceDocumentosConsulta.buscarPorEstatusAndDepartamentoAndTipoDocumento(100, departamento, tipoDocumento);
		model.addAttribute("departamentos", departamento);
		model.addAttribute("documentos", listaDocumento);
		
		return "/tablero_resultados/listTableroResultadoDepartamentos";
	}
}
