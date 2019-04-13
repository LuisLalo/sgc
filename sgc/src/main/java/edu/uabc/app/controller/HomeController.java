package edu.uabc.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.uabc.app.model.Departamento;
import edu.uabc.app.model.DocumentoConsulta;
import edu.uabc.app.model.Menu;
import edu.uabc.app.model.Permiso;
import edu.uabc.app.model.TipoDocumento;
import edu.uabc.app.model.UsuarioConsulta;
import edu.uabc.app.service.IDepartamentosService;
import edu.uabc.app.service.IDocumentosConsultaService;
import edu.uabc.app.service.IMenuService;
import edu.uabc.app.service.IPermisoService;
import edu.uabc.app.service.ITiposDocumentosService;
import edu.uabc.app.service.IUsuariosConsultaService;
import edu.uabc.app.util.CrearMenu;

@Controller
public class HomeController {
	
	@Autowired
	private IUsuariosConsultaService serviceUsuariosConsulta;
	
	@Autowired
	private IMenuService serviceMenu;
	
	@Autowired
	private IPermisoService servicePermiso;
	
	@Autowired
	private ITiposDocumentosService serviceTiposDocumentos;
	
	@Autowired
	private IDepartamentosService serviceDepartamentos;
	
	@Autowired
	private IDocumentosConsultaService serviceDocumentosConsulta;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String mostrarLogin() {
		return "login";
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String mostrarPrincipal(Authentication authentication, Model model) {
		System.out.println("Username: " + authentication.getName());
		System.out.println("Username: " + authentication.getDetails());
		System.out.println("Username: " + authentication.toString());
		System.out.println("Username: " + authentication.getCredentials());
		System.out.println("Username: " + authentication.getClass());
		System.out.println("Username: " + authentication.getPrincipal());
		
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
		
		for(GrantedAuthority rol: authentication.getAuthorities()) {
			System.out.println("Rol: " + rol.getAuthority());
			//System.out.println("Menu: " + listaMenu);
		}
		
		return "home";
	}
	// Controlador pendinete de eliminacion
	/*@GetMapping(value="{id}")
	public String controladorPrincipal(@PathVariable ("id") int idMenu, Authentication authentication, Model model) {
		
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
		
		String vista = "";
		
		switch(idMenu){
		case 2: vista = "perfil/perfilUsuario";
			break;
		case 3: break;
		case 4: break;
		case 5: break;
		case 6: break;
		case 7: break;
		case 8: break;
		case 9: break;
		case 10: vista = "programa_actividades/programa_actividades";
			break;
		case 11: vista = "reuniondirectiva/listReunionDirectiva";
			break;
		case 12: vista = "auditoria_interna/listAuditoriaInterna";
			break;
		case 13: vista = "auditoria_externa/listAuditoriaExterna";
			break;
		case 14: vista = "clima_laboral/listClimaLaboral";
			break;
		case 15: vista = "reunion_trabajo/listReunionTrabajo";
			break;
		case 16: vista = "politica/politica";
			break;
		case 17: 
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
			vista = "manual/manual";
			break;
	/*	case 18: vista = "gestion_conocimiento/listGestionConocimiento";
			break;
		case 19: vista = "procedimientos/listProcedimientos";
			break;
		case 20: vista = "instructivos/listInstructivos";
			break;
		case 21: vista = "formatos/listFormatos";
			break;
		case 22: vista = "documentos_varios/listDocumentosVarios";
			break;
		case 23: vista = "accion_correctiva/listAccionCorrectiva";
			break;
		case 24: break;
		case 25: vista = "proyecto_mejora/listProyectoMejora";
			break;
		case 36: 
			
			vista = "usuarios/listUsuarios";
		break;
		}
		
		return vista;
	}*/
	/*
	@RequestMapping(value="/documentos-autorizar", method=RequestMethod.GET)
	public String mostrarDocumentosAutorizar() {
		return "documentos-autorizar";
	}
	
	@RequestMapping(value="/menu", method=RequestMethod.GET)
	public String mostrarMenu() {
		return "menu";
	}*/
	
	@RequestMapping(value="/alta-capacitacion", method=RequestMethod.GET)
	public String mostrarAltaCapacitacion() {
		return "alta-capacitacion";
	}
	
	@RequestMapping(value="/registro-capacitacion", method=RequestMethod.GET)
	public String mostrarRegistroCapacitacion() {
		return "registro-capacitacion";
	}
	
	@RequestMapping(value="/consultar-capacitacion", method=RequestMethod.GET)
	public String mostrarConsultarCapacitacion() {
		return "consultar-capacitacion";
	}
	
	@RequestMapping(value="/convocatoria", method=RequestMethod.GET)
	public String mostrarConvocatoria() {
		return "convocatoria";
	}
}
