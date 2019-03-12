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

import edu.uabc.app.model.ActividadAccion;
import edu.uabc.app.model.ActividadAccionConsulta;
import edu.uabc.app.model.Departamento;
import edu.uabc.app.model.DocumentoConsulta;
import edu.uabc.app.model.Menu;
import edu.uabc.app.model.ProvieneDe;
import edu.uabc.app.model.ProyectoMejora;
import edu.uabc.app.model.TipoDocumento;
import edu.uabc.app.model.TipoProyecto;
import edu.uabc.app.model.UsuarioConsulta;
import edu.uabc.app.service.IActividadAccionConsultaService;
import edu.uabc.app.service.IActividadAccionService;
import edu.uabc.app.service.IDepartamentosService;
import edu.uabc.app.service.IDocumentosConsultaService;
import edu.uabc.app.service.IMenuService;
import edu.uabc.app.service.IProvieneDeService;
import edu.uabc.app.service.IProyectoMejoraService;
import edu.uabc.app.service.ITiposDocumentosService;
import edu.uabc.app.service.ITiposProyectosService;
import edu.uabc.app.service.IUsuariosConsultaService;
import edu.uabc.app.util.CrearMenu;

@Controller
@RequestMapping("proyecto_mejora")
public class ProyectoMejoraController {
	
	@Autowired
	private IUsuariosConsultaService serviceUsuariosConsulta;
	
	@Autowired
	private IProvieneDeService serviceProvieneDe;
	
	@Autowired
	private ITiposProyectosService serviceTiposProyectos;
	
	@Autowired
	private IDepartamentosService serviceDepartamentos;
	
	@Autowired
	private IProyectoMejoraService serviceProyectoMejora;
	
	@Autowired
	private IActividadAccionConsultaService serviceActividadAccionConsulta;
	
	@Autowired
	private IActividadAccionService serviceActividadAccion;
	
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
		List<ProyectoMejora> listaProyectoMejora = serviceProyectoMejora.buscarTodas();
		model.addAttribute("proyectoMejora", listaProyectoMejora);*/
		
		// Se agrega el menu generado por base de datos
		List<Menu> listaMenu = serviceMenu.buscarPorEstatusAndTipoVentana(1, 0);
		List<Menu> listaSubMenu = serviceMenu.buscarPorEstatusAndTipoVentana(1, 1);
		List<Menu> listaSubSubMenu = serviceMenu.buscarPorEstatusAndTipoVentana(1, 2);
		
		String menu = CrearMenu.menu(listaMenu, listaSubMenu, listaSubSubMenu);
		model.addAttribute("menu", menu);
		List<Departamento> listaDepartamento = serviceDepartamentos.buscarTodas();
		model.addAttribute("departamentos", listaDepartamento);
		
		return "proyecto_mejora/listProyectoMejora";
	}
	
	@GetMapping("/crear")
	public String crear(@ModelAttribute ProyectoMejora proyectoMejora, Model model, Authentication authentication) {
		// Se agrega el nombre del usuario
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		// Se cargan los catálogos necesarios para llenar el formulario del proyecto de mejora
		List<ProvieneDe> listaProvieneDe = serviceProvieneDe.buscarTodas();
		model.addAttribute("provieneDe", listaProvieneDe);
		List<TipoProyecto> listaTipoProyecto = serviceTiposProyectos.buscarTodas();
		model.addAttribute("tipoProyecto", listaTipoProyecto);
		List<UsuarioConsulta> listaUsuarios = serviceUsuariosConsulta.buscarTodas();
		System.out.println("Usuarios: " + listaUsuarios);
		model.addAttribute("responsable", listaUsuarios);
		
		
		// Se identifica si el usuario tiene el rod de administrador del SGC para mostrarle el listado completo de los usuarios
		if(usuarioAuth.getRol().getNombre().equals("SGC")) {
			System.out.println("Se identificó que el  usuario es el administrador del SGC");
			List<Departamento> listaDepartamento = serviceDepartamentos.buscarTodas();
			System.out.println("Departamentos: " + listaDepartamento);
			model.addAttribute("areaAplicacion", listaDepartamento);
			model.addAttribute("areaResponsable", listaDepartamento);
			List<UsuarioConsulta> listaUsuarioConsulta = serviceUsuariosConsulta.buscarTodas();
			model.addAttribute("usuario", listaUsuarioConsulta);
			return "proyecto_mejora/formProyectoMejora";
		}
		else {
			System.out.println("Se identificó que el  usuario no es el administrador del SGC");
			Departamento departamento = serviceDepartamentos.buscarPorId(usuarioAuth.getDepartamento().getId_departamento());
			model.addAttribute("departamento", departamento);
			List<UsuarioConsulta> listaUsuarioConsulta = serviceUsuariosConsulta.buscarPorDepartamento(usuarioAuth.getDepartamento());
			model.addAttribute("usuario", listaUsuarioConsulta);
			return "proyecto_mejora/formProyectoMejora";
		}
	}
	
	@PostMapping("/guardarProyectoMejora")
	public String guardar(@ModelAttribute ProyectoMejora proyectoMejora, BindingResult result, RedirectAttributes attribute, HttpServletRequest request) {
		
		if (result.hasErrors()) {
			System.out.println(proyectoMejora);
			System.out.println("Existieron errores");
			System.out.println("result: " + result);
			
			return "proyecto_mejora/formProyectoMejora";
		}
		
		serviceProyectoMejora.insertar(proyectoMejora);
		
		attribute.addFlashAttribute("mensaje", "El registro fue guardado");
		return "redirect:/proyecto_mejora/crearActividad";
	}
	
	@GetMapping("/crearActividad")
	public String crearActividad(@ModelAttribute ActividadAccion actividadAccion, Model model, Authentication authentication) {
		// Se agrega el nombre del usuario
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		// Se buscan las personas que pertenecen al departamento
		List<UsuarioConsulta> listaUsuarioConsulta = serviceUsuariosConsulta.buscarPorDepartamento(usuarioAuth.getDepartamento());
		model.addAttribute("usuario", listaUsuarioConsulta);
		
		// Se busca el última proyecto de mejora del departamento
		ProyectoMejora proyectoMejora = serviceProyectoMejora.buscarTopDepartamentoOrdenadoPorIdProyectoDesc(usuarioAuth.getDepartamento().getId_departamento());
		System.out.println("proyectoMejora: " + proyectoMejora);
		// Se buscan el listado de las actividades de la acción correctiva para mostrarla
		List<ActividadAccionConsulta> listaActividadAccionConsulta = serviceActividadAccionConsulta.buscarPorIdProyecto(proyectoMejora.getIdProyecto());
		System.out.println("listaActividadAccionConsulta: " + listaActividadAccionConsulta);
		model.addAttribute("actividadAccionConsulta", listaActividadAccionConsulta);
		
		return "proyecto_mejora/formAccionActividad";
	}
	
	@PostMapping("/guardarActividad")
	public String guardarActividad(@ModelAttribute ActividadAccion actividadAccion, BindingResult result, RedirectAttributes attribute, HttpServletRequest request, Authentication authentication, Model model) {
		
		// Se agrega el nombre del usuario
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		System.out.println("Actividad accion: " + actividadAccion);
		if (result.hasErrors()) {
			System.out.println(actividadAccion);
			System.out.println("Existieron errores");
			System.out.println("result: " + result);
			
			return "proyecto_mejora/formAccionActividad";
		}
		
		// Se busca el último proyecto de mejora del departamento
		ProyectoMejora proyectoMejora = serviceProyectoMejora.buscarTopDepartamentoOrdenadoPorIdProyectoDesc(usuarioAuth.getDepartamento().getId_departamento());
		System.out.println("proyectoMejora: " + proyectoMejora);
		
		actividadAccion.setIdProyecto(proyectoMejora.getIdProyecto());
		serviceActividadAccion.insertar(actividadAccion);
		
		attribute.addFlashAttribute("mensaje", "El registro fue guardado");
		return "redirect:/proyecto_mejora/crearActividad";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	@GetMapping("/{id}")
	public String mostrar(@PathVariable ("id") int idDepartamento, Model model, Authentication authentication) {
		//Se agrega el nombre del usuario
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		Departamento departamento = serviceDepartamentos.buscarPorId(idDepartamento);
		TipoDocumento tipoDocumento = serviceTiposDocumentos.buscarPorNombre("Proyectos de Mejora");
		
		List<DocumentoConsulta> listaDocumento = serviceDocumentosConsulta.buscarPorEstatusAndDepartamentoAndTipoDocumento(100, departamento, tipoDocumento);
		model.addAttribute("departamentos", departamento);
		model.addAttribute("documentos", listaDocumento);
		
		return "/proyecto_mejora/listProyectoMejoraDepartamentos";
	}
}
