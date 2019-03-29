package edu.uabc.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.uabc.app.model.AccionCorrectiva;
import edu.uabc.app.model.ActividadAccion;
import edu.uabc.app.model.ActividadAccionConsulta;
import edu.uabc.app.model.CausaRaiz;
import edu.uabc.app.model.Departamento;
import edu.uabc.app.model.DocumentoConsulta;
import edu.uabc.app.model.EstatusAccion;
import edu.uabc.app.model.Evaluacion;
import edu.uabc.app.model.Menu;
import edu.uabc.app.model.ObservacionNorma;
import edu.uabc.app.model.ProvieneDe;
import edu.uabc.app.model.TipoAuditoria;
import edu.uabc.app.model.TipoDocumento;
import edu.uabc.app.model.UsuarioConsulta;
import edu.uabc.app.service.IAccionesCorrectivasService;
import edu.uabc.app.service.IActividadAccionConsultaService;
import edu.uabc.app.service.IActividadAccionService;
import edu.uabc.app.service.ICausaRaizService;
import edu.uabc.app.service.IDepartamentosService;
import edu.uabc.app.service.IDocumentosConsultaService;
import edu.uabc.app.service.IEstatusAccionService;
import edu.uabc.app.service.IEvaluacionService;
import edu.uabc.app.service.IMenuService;
import edu.uabc.app.service.IObservacionNormaService;
import edu.uabc.app.service.IProvieneDeService;
import edu.uabc.app.service.ITiposAuditoriaService;
import edu.uabc.app.service.ITiposDocumentosService;
import edu.uabc.app.service.IUsuariosConsultaService;
import edu.uabc.app.util.CrearMenu;

@Controller
@RequestMapping("/accion_correctiva")
public class AccionCorrectivaController {

	@Autowired
	private IAccionesCorrectivasService serviceAccionesCorrectivas;
	
	@Autowired
	private IDepartamentosService serviceDepartamentos;
	
	@Autowired
	private IUsuariosConsultaService serviceUsuariosConsulta;
	
	@Autowired
	private IProvieneDeService serviceProvieneDe;
	
	@Autowired
	private ICausaRaizService serviceCausaRaiz;
	
	@Autowired
	private IEstatusAccionService serviceEstatusAccion;
	
	@Autowired
	private ITiposAuditoriaService serviceTipoAuditoria;
	
	@Autowired
	private IObservacionNormaService serviceObservacionNorma;
	
	@Autowired
	private IEvaluacionService serviceEvaluacion;
	
	@Autowired
	private IActividadAccionService serviceActividadAccion;
	
	@Autowired
	private IActividadAccionConsultaService serviceActividadAccionConsulta;
	
	@Autowired
	ITiposDocumentosService serviceTiposDocumentos;
	
	@Autowired
	private IDocumentosConsultaService serviceDocumentosConsulta;
	
	@Autowired
	private IMenuService serviceMenu;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model, Authentication authentication) {
		// Se agrega el nombre del usuario
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		/*
		List<AccionCorrectiva>listaAccionCorrectiva = serviceAccionesCorrectivas.buscarTodas();
		System.out.println("Accion correctiva: " + listaAccionCorrectiva);
		model.addAttribute("accionCorrectiva", listaAccionCorrectiva);*/
		
		// Se agrega el menu generado por base de datos
		List<Menu> listaMenu = serviceMenu.buscarPorEstatusAndIdTipoVentanaOrderByOrden(1, 0);
		List<Menu> listaSubMenu = serviceMenu.buscarPorEstatusAndIdTipoVentanaOrderByOrden(1, 1);
		List<Menu> listaSubSubMenu = serviceMenu.buscarPorEstatusAndIdTipoVentanaOrderByOrden(1, 2);
		
		String menuCompleto = CrearMenu.menu(listaMenu, listaSubMenu, listaSubSubMenu);
		model.addAttribute("menuCompleto", menuCompleto);
		
		List<Departamento> listaDepartamento = serviceDepartamentos.buscarTodas();
		model.addAttribute("departamentos", listaDepartamento);
		
		return "accion_correctiva/listAccionCorrectiva";
	}
	
	@GetMapping("/crear")
	public String crear(@ModelAttribute AccionCorrectiva accionCorrectiva, Model model, Authentication authentication) {
		// Se agrega el nombre del usuario
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		// Se cargan los catálogos necesarios para llenar el formulario de la acción correctiva
		List<ProvieneDe> listaProvieneDe = serviceProvieneDe.buscarTodas();
		model.addAttribute("provieneDe", listaProvieneDe);
		List<TipoAuditoria> listaTipoAuditoria = serviceTipoAuditoria.buscarTodas();
		model.addAttribute("tipoAuditoria", listaTipoAuditoria);
		System.out.println("Tipo Auditoria: " + listaTipoAuditoria);
		List<CausaRaiz> listaCausaRaiz = serviceCausaRaiz.buscarTodas();
		System.out.println(listaCausaRaiz);
		model.addAttribute("causaRaiz", listaCausaRaiz);
		List<EstatusAccion> listaEstatusAccion = serviceEstatusAccion.buscarTodas();
		model.addAttribute("estatusAccion", listaEstatusAccion);
		System.out.println(listaEstatusAccion);
		List<ObservacionNorma> listaObservacionNorma = serviceObservacionNorma.buscarTodas();
		model.addAttribute("observacionNorma", listaObservacionNorma);
		List<Evaluacion> listaEvaluacion = serviceEvaluacion.buscarTodas();
		model.addAttribute("evaluacion", listaEvaluacion);
		
		// Se identifica si el usuario tiene el rod de administrador del SGC para mostrarle el listado completo de los usuarios
		if(usuarioAuth.getRol().getNombre().equals("SGC")) {
			System.out.println("Se identificó que el  usuario es el administrador del SGC");
			List<Departamento> listaDepartamento = serviceDepartamentos.buscarTodas();
			model.addAttribute("departamento", listaDepartamento);
			// se comenta porque se cambió el query para hacer la paginación
			//List<UsuarioConsulta> listaUsuarioConsulta = serviceUsuariosConsulta.buscarTodas();
			//model.addAttribute("usuario", listaUsuarioConsulta);
			return "accion_correctiva/formAccionCorrectivaAdministrador";
		}
		else {
			System.out.println("Se identificó que el  usuario no es el administrador del SGC");
			Departamento departamento = serviceDepartamentos.buscarPorId(usuarioAuth.getDepartamento().getId_departamento());
			model.addAttribute("departamento", departamento);
			List<UsuarioConsulta> listaUsuarioConsulta = serviceUsuariosConsulta.buscarPorDepartamento(usuarioAuth.getDepartamento());
			model.addAttribute("usuario", listaUsuarioConsulta);
			return "accion_correctiva/formAccionCorrectiva";
		}
		
	}
	
	@PostMapping("/guardarAccionCorrectiva")
	public String guardar(@ModelAttribute AccionCorrectiva accionCorrectiva, BindingResult result, RedirectAttributes attribute, HttpServletRequest request) {
		System.out.println("accionCorrectiva: " + accionCorrectiva);
		if (result.hasErrors()) {
			System.out.println(accionCorrectiva);
			System.out.println("Existieron errores");
			System.out.println("result: " + result);
			
			return "accion_correctiva/formAccionCorrectiva";
		}
		
		serviceAccionesCorrectivas.insertar(accionCorrectiva);
		
		attribute.addFlashAttribute("mensaje", "El registro fue guardado");
		return "redirect:/accion_correctiva/crearActividad";
	}
	
	@GetMapping("/crearActividad")
	public String crearActividad(@ModelAttribute ActividadAccion actividadAccion, Model model, Authentication authentication) {
		// Se agrega el nombre del usuario
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		// Se buscan las personas que pertenecen al departamento
		List<UsuarioConsulta> listaUsuarioConsulta = serviceUsuariosConsulta.buscarPorDepartamento(usuarioAuth.getDepartamento());
		model.addAttribute("usuario", listaUsuarioConsulta);
		
		// Se busca la última acción correctiva para el departamento
		AccionCorrectiva accionCorrectiva = serviceAccionesCorrectivas.buscarTopDepartamentoOrdenadoPorIdAccionDesc(usuarioAuth.getDepartamento().getId_departamento());
		System.out.println("accionCorrectiva : " + accionCorrectiva);
		System.out.println("id accion correctiva : " + accionCorrectiva.getIdAccion());
		// Se buscan el listado de las actividades de la acción correctiva para mostrarla
		List<ActividadAccionConsulta> listaActividadAccionConsulta = serviceActividadAccionConsulta.buscarPorIdAccion(accionCorrectiva.getIdAccion());
		System.out.println("ActividadAccionConsulta : " + listaActividadAccionConsulta);
		model.addAttribute("actividadAccionConsulta", listaActividadAccionConsulta);
		
		return "accion_correctiva/formAccionActividad";
	}
	
	@PostMapping("/guardarActividad")
	public String guardarActividad(@ModelAttribute ActividadAccion actividadAccion, BindingResult result, RedirectAttributes attribute, HttpServletRequest request, Authentication authentication, Model model) {
		// Se agrega el nombre del usuario
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		System.out.println("accionCorrectiva: " + actividadAccion);
		if (result.hasErrors()) {
			System.out.println(actividadAccion);
			System.out.println("Existieron errores");
			System.out.println("result: " + result);
			
			return "accion_correctiva/formAccionActividad";
		}
		
		// Se busca la última acción correctiva del departamento
		AccionCorrectiva accionCorrectiva = serviceAccionesCorrectivas.buscarTopDepartamentoOrdenadoPorIdAccionDesc(usuarioAuth.getDepartamento().getId_departamento());
		System.out.println("AccionCorrectiva: " + accionCorrectiva);
		
		actividadAccion.setIdAccion(accionCorrectiva.getIdAccion());
		serviceActividadAccion.insertar(actividadAccion);
		
		attribute.addFlashAttribute("mensaje", "El registro fue guardado");
		return "redirect:/accion_correctiva/crearActividad";
		//return "accion_correctiva/formAccionActividad";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	@GetMapping(value="/editar/{id}")
	public String editar(@PathVariable("id") int id_accion, Model model) {
		AccionCorrectiva accionCorrectiva = serviceAccionesCorrectivas.buscarPorId(id_accion);
		model.addAttribute("accion_correctiva", accionCorrectiva);
		return "accion_correctiva/formAccionCorrectiva";
	}
	
	@GetMapping(value="eliminar/{id}")
	public String eliminar(@PathVariable("id") int id_accion, RedirectAttributes attributes) {
		serviceAccionesCorrectivas.eliminar(id_accion);
		attributes.addFlashAttribute("mensaje", "La acción correctiva fue eliminada");
		return "redirect:/accion_correctiva/index";
	}
	
	@GetMapping("/{id}")
	public String mostrar(@PathVariable ("id") int idDepartamento, Model model, Authentication authentication) {
		//Se agrega el nombre del usuario
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		Departamento departamento = serviceDepartamentos.buscarPorId(idDepartamento);
		TipoDocumento tipoDocumento = serviceTiposDocumentos.buscarPorNombre("Acciones Correctivas");
		
		List<DocumentoConsulta> listaDocumento = serviceDocumentosConsulta.buscarPorEstatusAndDepartamentoAndTipoDocumento(100, departamento, tipoDocumento);
		model.addAttribute("departamentos", departamento);
		model.addAttribute("documentos", listaDocumento);
		System.out.println("Documento encontrado:" + listaDocumento);
		System.out.println("Departamento:" + departamento);
		System.out.println("Tipos documento:" + tipoDocumento);
		return "/accion_correctiva/listAccionCorrectivaDepartamentos";
	}
}
