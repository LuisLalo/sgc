package edu.uabc.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.uabc.app.model.DocumentoActualizar;
import edu.uabc.app.model.Menu;
import edu.uabc.app.model.Permiso;
import edu.uabc.app.model.UsuarioConsulta;
import edu.uabc.app.service.IDocumentosActualizarService;
import edu.uabc.app.service.IMenuService;
import edu.uabc.app.service.IPermisoService;
import edu.uabc.app.service.IUsuariosConsultaService;
import edu.uabc.app.util.CrearMenu;

@Controller
@RequestMapping("/capacitacion")
public class CapacitacionController {

	@Autowired
	private IDocumentosActualizarService serviceDocumentos;
	
	@Autowired
	private IUsuariosConsultaService serviceUsuariosConsulta;
	
	@Autowired
	private IMenuService serviceMenu;
	
	@Autowired
	private IPermisoService servicePermiso;
	
	@GetMapping("/alta")
	public String mostrarAlta(Model model, Authentication authentication) {
		// Se agrega el nombre del usuario
				UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
				model.addAttribute("usuarioAuth", usuarioAuth);
				System.out.println("numEmpleado antes del m�todo: "+ usuarioAuth.getNum_empleado());
				
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
		
		List<DocumentoActualizar> lista = serviceDocumentos.buscarTodas();
		model.addAttribute("documentos", lista);
		return "capacitacion/alta-capacitacion";
	}
	
	@GetMapping("/registro")
	public String mostrarRegistro(Model model, Authentication authentication) {
		// Se agrega el nombre del usuario
				UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
				model.addAttribute("usuarioAuth", usuarioAuth);
				System.out.println("numEmpleado antes del m�todo: "+ usuarioAuth.getNum_empleado());
				
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
		
		List<DocumentoActualizar> lista = serviceDocumentos.buscarTodas();
		model.addAttribute("documentos", lista);
		return "capacitacion/registro-capacitacion";
	}
	
	@GetMapping("/consultar")
	public String mostrarConsultar(Model model, Authentication authentication) {
		// Se agrega el nombre del usuario
				UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
				model.addAttribute("usuarioAuth", usuarioAuth);
				System.out.println("numEmpleado antes del m�todo: "+ usuarioAuth.getNum_empleado());
				
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
		
		List<DocumentoActualizar> lista = serviceDocumentos.buscarTodas();
		model.addAttribute("documentos", lista);
		return "capacitacion/consultar-capacitacion";
	}
}
