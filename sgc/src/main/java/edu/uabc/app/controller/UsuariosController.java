package edu.uabc.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.uabc.app.model.Departamento;
import edu.uabc.app.model.EstatusUsuario;
import edu.uabc.app.model.Menu;
import edu.uabc.app.model.Permiso;
import edu.uabc.app.model.PermisoActualizar;
import edu.uabc.app.model.Puesto;
import edu.uabc.app.model.Rol;
import edu.uabc.app.model.UsuarioActualizar;
import edu.uabc.app.model.UsuarioConsulta;
import edu.uabc.app.service.IDepartamentosService;
import edu.uabc.app.service.IEstatusUsuarioService;
import edu.uabc.app.service.IMenuService;
import edu.uabc.app.service.IPermisoActualizarService;
import edu.uabc.app.service.IPermisoService;
import edu.uabc.app.service.IPuestosService;
import edu.uabc.app.service.IRolesService;
import edu.uabc.app.service.IUsuariosActualizarService;
import edu.uabc.app.service.IUsuariosConsultaService;
import edu.uabc.app.util.AsignarPermisos;
import edu.uabc.app.util.CrearMenu;
import edu.uabc.app.util.EnviarCorreo;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

	@Autowired
	private IUsuariosConsultaService serviceUsuariosConsulta;
	
	@Autowired
	private IUsuariosActualizarService serviceUsuariosActualizar;
	
	@Autowired
	private IPuestosService servicePuestos;
	
	@Autowired
	private IDepartamentosService serviceDepartamentos;
	
	@Autowired
	private IRolesService serviceRoles;
	
	@Autowired
	private IEstatusUsuarioService serviceEstatusUsuario;
	
	@Autowired
	private IMenuService serviceMenu;
	
	@Autowired
	private IPermisoService servicePermiso;
	
	@Autowired
	private IPermisoActualizarService servicePermisoActualizar;
	
	@GetMapping("/indexPaginate")
	public String mostrarIndexPaginado(Authentication authentication, Model model, Pageable page) {
		Page<UsuarioConsulta> lista = serviceUsuariosConsulta.buscarTodas(page);
		System.out.println("Lista de usuarios: " + lista);
		model.addAttribute("usuarios", lista);
		
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
		
		// Se envía correo con la notificación al usuario
		//EnviarCorreo.EnviarNotificacion();
		
		return "usuarios/listUsuarios";
	}
	
	@GetMapping("/crear")
	public String crear(@ModelAttribute UsuarioActualizar usuarioActualizar, Authentication authentication, Model model) {
		List<Puesto> listaPuesto = servicePuestos.buscarTodas();
		List<Departamento> listaDepartamento = serviceDepartamentos.buscarTodas();
		List<Rol> listaRol = serviceRoles.buscarTodas();
		model.addAttribute("puestos", listaPuesto);
		model.addAttribute("departamentos", listaDepartamento);
		model.addAttribute("roles", listaRol);
		List<EstatusUsuario> listaEstatusUsuario = serviceEstatusUsuario.buscarTodas();
		model.addAttribute("estatusUsuario", listaEstatusUsuario);
		
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		// Se agrega el menu generado por base de datos
		List<Menu> listaMenu = serviceMenu.buscarPorEstatusAndIdTipoVentanaOrderByOrden(1, 0);
		List<Menu> listaSubMenu = serviceMenu.buscarPorEstatusAndIdTipoVentanaOrderByOrden(1, 1);
		List<Menu> listaSubSubMenu = serviceMenu.buscarPorEstatusAndIdTipoVentanaOrderByOrden(1, 2);
		
		String menuCompleto = CrearMenu.menu(listaMenu, listaSubMenu, listaSubSubMenu);
		model.addAttribute("menuCompleto", menuCompleto);
		
		return "usuarios/formUsuariosNuevo";
	}
	
	@PostMapping("/guardar")
	public String guardar(@ModelAttribute UsuarioActualizar usuario, BindingResult result, RedirectAttributes attribute, HttpServletRequest request) {
		
		if (result.hasErrors()) {
			System.out.println("Existieron errores");
			
			return "usuarios/formUsuarios";
		}
		
		System.out.println("Recibiendo objeto usuario: " + usuario);
		
		// Encriptación del password de los usuarios
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(usuario.getContrasena());
		usuario.setContrasena(hashedPassword);
		
		// Se hace el guardado del usuario en la base de datos
		serviceUsuariosActualizar.insertar(usuario);
		
		// Se buscan las opciones completas del menu
		List<Menu> listaMenu = serviceMenu.buscarTodas();
		
		// Se asignan los permisos dependiendo del rol asignado
		List<PermisoActualizar> lista = AsignarPermisos.asignarPermiso(usuario, listaMenu);
		
		attribute.addFlashAttribute("mensaje", "El registro fue guardado");
		return "redirect:/usuarios/index";
	}
	
	@GetMapping(value="/editar/{id}")
	public String editar(@PathVariable("id") int num_empleado, Authentication authentication, Model model) {
	
		List<Puesto> listaPuesto = servicePuestos.buscarTodas();
		List<Departamento> listaDepartamento = serviceDepartamentos.buscarTodas();
		List<Rol> listaRol = serviceRoles.buscarTodas();
		model.addAttribute("puestos", listaPuesto);
		model.addAttribute("departamentos", listaDepartamento);
		model.addAttribute("roles", listaRol);
		UsuarioActualizar usuario = serviceUsuariosActualizar.buscarPorId(num_empleado);
		model.addAttribute("usuario", usuario);
		List<EstatusUsuario> listaEstatusUsuario = serviceEstatusUsuario.buscarTodas();
		model.addAttribute("estatusUsuario", listaEstatusUsuario);
		
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		// Se agrega el menu generado por base de datos
		List<Menu> listaMenu = serviceMenu.buscarPorEstatusAndIdTipoVentana(1, 0);
		List<Menu> listaSubMenu = serviceMenu.buscarPorEstatusAndIdTipoVentana(1, 1);
		List<Menu> listaSubSubMenu = serviceMenu.buscarPorEstatusAndIdTipoVentana(1, 2);
		
		String menuCompleto = CrearMenu.menu(listaMenu, listaSubMenu, listaSubSubMenu);
		model.addAttribute("menuCompleto", menuCompleto);
		
		return "usuarios/formUsuariosEditar";
	}
	
	@GetMapping(value="eliminar/{id}")
	public String eliminar(@PathVariable("id") int num_empleado, RedirectAttributes attributes) {
		
		serviceUsuariosActualizar.eliminar(num_empleado);
		attributes.addFlashAttribute("mensaje", "El usuario fue eliminado");
		return "redirect:/usuarios/index";
	}
	
	// Controladores de los permisos a las secciones a los que tiene acceso el usuario
	@GetMapping(value="permisos/{id}")
	public String permisos(@PathVariable("id") int numEmpleado, Authentication authentication, Model model) {
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
		
		List<Permiso> lista = servicePermiso.buscarPorNumEmpleado(numEmpleado);
		List<Permiso> listaPermiso = new ArrayList<Permiso>();
		// Ciclo para identificar si el usuario tiene permisos para las secciones
		int contador = 0;
		for(int cont=0;cont<lista.size();cont++) {
			System.out.println("Contador: " + cont);
			System.out.println("Tamaño de la lista: " + lista.size());
		System.out.println("Valor idTipoVentana: " + lista.get(cont).getMenu().getIdTipoVentana());
			System.out.println("Valor lista: " + lista.get(cont));
			// se identifica que la sección cumpla con idMenu y este activo
			if(lista.get(cont).getMenu().getIdTipoVentana()==0) {
				
				System.out.println("Permiso: " + listaPermiso);
				listaPermiso.add(contador, lista.get(cont));
				System.out.println("Permiso 1: " + listaPermiso);
				contador++;
			}
		}
		
		model.addAttribute("permiso", listaPermiso);
		
		System.out.println("Permiso: " + listaPermiso);
		
		return "usuarios/permisos";
	}
	
	@GetMapping(value="permisos/{id}/seccion/{id1}")
	public String modificarPermisos(@PathVariable("id") int numEmpleado, @PathVariable("id1") int idMenu, Authentication authentication, Model model) {
		// Se agrega el nombre del usuario
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		// Se buscan los datos del usuario
		UsuarioConsulta usuarioConsulta = serviceUsuariosConsulta.buscarPorId(numEmpleado);
		model.addAttribute("usuarioConsulta", usuarioConsulta);
		
		// Se agrega el menu generado por base de datos
		List<Menu> listaMenu = serviceMenu.buscarPorEstatusAndIdTipoVentana(1, 0);
		List<Menu> listaSubMenu = serviceMenu.buscarPorEstatusAndIdTipoVentana(1, 1);
		List<Menu> listaSubSubMenu = serviceMenu.buscarPorEstatusAndIdTipoVentana(1, 2);
		
		String menuCompleto = CrearMenu.menu(listaMenu, listaSubMenu, listaSubSubMenu);
		model.addAttribute("menuCompleto", menuCompleto);
		
		// Se busca la opción del menu al que pertenecen las opciones
		PermisoActualizar permisoActualizar = servicePermisoActualizar.buscarPorIdPermiso(idMenu);
		//System.out.println("idMenu: " + idMenu);
		//System.out.println("permisoActualizar obtenido del base de datos: " + permisoActualizar);
		Menu menu = serviceMenu.buscarPorId(permisoActualizar.getIdMenu());
		//System.out.println("Menu obtenido del base de datos: " + menu);
		Permiso permiso = servicePermiso.buscarPorNumEmpleadoAndMenu(numEmpleado, menu);
		//System.out.println("Permiso obtenido del base de datos: " + permiso);
		model.addAttribute("permisoSeccion", permiso);
		
		// Se buscan los permisos del usuario
		List<Permiso> lista = servicePermiso.buscarPorNumEmpleado(numEmpleado);
		List<Permiso> listaPermiso = new ArrayList<Permiso>();
		// Ciclo para identificar si el usuario tiene permisos para las secciones
		int contador = 0;
		for(int cont=0;cont<lista.size();cont++) {
			System.out.println("Contador: " + cont);
			System.out.println("Tamaño de la lista: " + lista.size());
			System.out.println("Valor Relacion: " + lista.get(cont).getMenu().getRelacion());
			System.out.println("Valor Relacion1: " + lista.get(contador).getMenu().getRelacion());
			System.out.println("idMenu: " + idMenu);
			System.out.println("Valor lista: " + lista.get(cont));
			
			// se identifica que la sección cumpla con idMenu y este activo
			if(lista.get(cont).getMenu().getRelacion()==permisoActualizar.getIdMenu()) {
				
				System.out.println("Permiso: " + listaPermiso);
				listaPermiso.add(contador, lista.get(cont));
				System.out.println("Permiso 1: " + listaPermiso);
				contador++;
			}
		}
		model.addAttribute("permiso", listaPermiso);
		
		return "usuarios/modificarPermisos";
	}
	
	@GetMapping(value="permisos/{id}/cambiar/{id1}")
	public String cambiarPermiso(@PathVariable("id") int numEmpleado, @PathVariable("id1") int idMenu, RedirectAttributes attributes) {
		
		attributes.addFlashAttribute("mensaje", "El permiso fue actualizado");
		
		Permiso permiso = servicePermiso.buscarPorIdPermisoAndNumEmpleado(idMenu, numEmpleado);
		System.out.println("Permiso que se va a actualizar: " + permiso);
		
		// Se cambia el estatus dependiendo del valor almacenado en la base de datos
		PermisoActualizar permisoActualizar = new PermisoActualizar();
		//System.out.println("Valor permiso: " + permiso.getEstatusPermiso().getIdEstatus());
		if(permiso.getEstatusPermiso().getIdEstatus()==0) {
			permisoActualizar.setIdPermiso(idMenu);
			permisoActualizar.setNumEmpleado(numEmpleado);
			permisoActualizar.setIdMenu(permiso.getMenu().getIdMenu());
			permisoActualizar.setIdEstatus(1);
			//System.out.println("Valor permisoActualizar: " + permisoActualizar);
		}
		else if(permiso.getEstatusPermiso().getIdEstatus()==1) {
			permisoActualizar.setIdPermiso(idMenu);
			permisoActualizar.setNumEmpleado(numEmpleado);
			permisoActualizar.setIdMenu(permiso.getMenu().getIdMenu());
			permisoActualizar.setIdEstatus(0);
		}
		
		// Se hace la actualización en la base de datos
		servicePermisoActualizar.insertar(permisoActualizar);
		
		return "redirect:/usuarios/permisos/{id}";
	}
	
	@GetMapping(value="permisos/{id}/cambiar/{id1}/seccion/{id2}")
	public String cambiarPermisoIndividual(@PathVariable("id") int numEmpleado, @PathVariable("id1") int idMenu, @PathVariable("id2") int idMenu1, RedirectAttributes attributes) {
		
		attributes.addFlashAttribute("mensaje", "El permiso fue actualizado");
		
		Permiso permiso = servicePermiso.buscarPorIdPermisoAndNumEmpleado(idMenu1, numEmpleado);
		System.out.println("Permiso que se va a actualizar: " + permiso);
		
		// Se cambia el estatus dependiendo del valor almacenado en la base de datos
		PermisoActualizar permisoActualizar = new PermisoActualizar();
		//System.out.println("Valor permiso: " + permiso.getEstatusPermiso().getIdEstatus());
		if(permiso.getEstatusPermiso().getIdEstatus()==0) {
			permisoActualizar.setIdPermiso(idMenu1);
			permisoActualizar.setNumEmpleado(numEmpleado);
			permisoActualizar.setIdMenu(permiso.getMenu().getIdMenu());
			permisoActualizar.setIdEstatus(1);
			//System.out.println("Valor permisoActualizar: " + permisoActualizar);
		}
		else if(permiso.getEstatusPermiso().getIdEstatus()==1) {
			permisoActualizar.setIdPermiso(idMenu1);
			permisoActualizar.setNumEmpleado(numEmpleado);
			permisoActualizar.setIdMenu(permiso.getMenu().getIdMenu());
			permisoActualizar.setIdEstatus(0);
		}
		
		// Se hace la actualización en la base de datos
		servicePermisoActualizar.insertar(permisoActualizar);
		
		return "redirect:/usuarios/permisos/{id}/seccion/{id1}";
	}
	
	@GetMapping(value="permisos/{id}/seccion/{id1}/seccion/{id2}")
	public String modificarPermisoIndividual(@PathVariable("id") int numEmpleado, @PathVariable("id1") int idMenu, @PathVariable("id2") int idMenu1, RedirectAttributes attributes, Model model, Authentication authentication) {
		
		attributes.addFlashAttribute("mensaje", "El permiso fue actualizado");
		
		
		// Se agrega el nombre del usuario
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		// Se buscan los datos del usuario
		UsuarioConsulta usuarioConsulta = serviceUsuariosConsulta.buscarPorId(numEmpleado);
		model.addAttribute("usuarioConsulta", usuarioConsulta);
		
		// Se agrega el menu generado por base de datos
		List<Menu> listaMenu = serviceMenu.buscarPorEstatusAndIdTipoVentana(1, 0);
		List<Menu> listaSubMenu = serviceMenu.buscarPorEstatusAndIdTipoVentana(1, 1);
		List<Menu> listaSubSubMenu = serviceMenu.buscarPorEstatusAndIdTipoVentana(1, 2);
		
		String menuCompleto = CrearMenu.menu(listaMenu, listaSubMenu, listaSubSubMenu);
		model.addAttribute("menuCompleto", menuCompleto);
		
		// Se busca la opción del menu al que pertenecen las opciones
		PermisoActualizar permisoActualizar = servicePermisoActualizar.buscarPorIdPermiso(idMenu1);
		System.out.println("idMenu1: " + idMenu1);
		//System.out.println("permisoActualizar obtenido del base de datos: " + permisoActualizar);
		Menu menu = serviceMenu.buscarPorId(permisoActualizar.getIdMenu());
		System.out.println("Menu obtenido del base de datos: " + menu);
		Permiso permiso = servicePermiso.buscarPorNumEmpleadoAndMenu(numEmpleado, menu);
		System.out.println("Permiso obtenido del base de datos: " + permiso);
		model.addAttribute("permisoSeccion", permiso);
		
		// Se buscan los permisos del usuario
		List<Permiso> lista = servicePermiso.buscarPorNumEmpleado(numEmpleado);
		List<Permiso> listaPermiso = new ArrayList<Permiso>();
		// Ciclo para identificar si el usuario tiene permisos para las secciones
		int contador = 0;
		for(int cont=0;cont<lista.size();cont++) {
			System.out.println("Contador: " + cont);
			System.out.println("Tamaño de la lista: " + lista.size());
			System.out.println("Valor Relacion: " + lista.get(cont).getMenu().getRelacion());
			System.out.println("Valor Relacion1: " + permisoActualizar.getIdMenu());
			System.out.println("idMenu: " + idMenu1);
			System.out.println("Valor lista: " + lista.get(cont));
			
			// se identifica que la sección cumpla con idMenu y este activo
			if(lista.get(cont).getMenu().getRelacion()==permisoActualizar.getIdMenu()) {
				
				System.out.println("Permiso: " + listaPermiso);
				listaPermiso.add(contador, lista.get(cont));
				System.out.println("Permiso 1: " + listaPermiso);
				contador++;
			}
		}
		
		model.addAttribute("permiso", listaPermiso);
		
		return "usuarios/modificarSubPermisos";
	}
}