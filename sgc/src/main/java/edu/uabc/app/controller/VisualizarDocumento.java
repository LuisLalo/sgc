package edu.uabc.app.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.uabc.app.model.ClasificadorDocumento;
import edu.uabc.app.model.Departamento;
import edu.uabc.app.model.DocumentoConsulta;
import edu.uabc.app.model.Menu;
import edu.uabc.app.model.Permiso;
import edu.uabc.app.model.TipoDocumento;
import edu.uabc.app.model.UsuarioConsulta;
import edu.uabc.app.service.IClasificadorDocumentosService;
import edu.uabc.app.service.IDepartamentosService;
import edu.uabc.app.service.IDocumentosConsultaService;
import edu.uabc.app.service.IMenuService;
import edu.uabc.app.service.IPermisoService;
import edu.uabc.app.service.ITiposDocumentosService;
import edu.uabc.app.service.IUsuariosConsultaService;
import edu.uabc.app.util.CrearMenu;

@Controller
@RequestMapping("/{nombre}")
public class VisualizarDocumento {

	@Autowired
	private IDepartamentosService serviceDepartamentos;
	
	@Autowired
	private IUsuariosConsultaService serviceUsuariosConsulta;
	
	@Autowired
	private ITiposDocumentosService serviceTiposDocumentos;
	
	@Autowired
	private IDocumentosConsultaService serviceDocumentosConsulta;
	
	@Autowired
	private IMenuService serviceMenu;
	
	@Autowired
	private IPermisoService servicePermiso;
	
	@Autowired
	private IClasificadorDocumentosService serviceClasificadorDocumentos;
	
	@GetMapping("/index")
	public String mostrarIndex(@PathVariable ("nombre") String nombre, Model model, Authentication authentication) {
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
		
		// Se busca el menu completo para buscar el documento que le corresponde
		List<Menu> listaTotal = serviceMenu.buscarTodas();
		
		TipoDocumento tipoDocumento = new TipoDocumento();
		
		for(int cont=0;cont<listaTotal.size();cont++) {
			String sinAcentos = StringUtils.stripAccents(listaTotal.get(cont).getNombre());
			listaTotal.get(cont).setNombre(sinAcentos);
			System.out.println("nombre: " + nombre);
			System.out.println("listaTotal.get(cont).getNombre().toLowerCase()): " + listaTotal.get(cont).getNombre().toLowerCase().replaceAll(" ", "_"));
			if(nombre.equals(listaTotal.get(cont).getNombre().toLowerCase().replaceAll(" ", "_"))){
				tipoDocumento = serviceTiposDocumentos.buscarPorNombre(listaTotal.get(cont).getNombre());
			}
		}
		System.out.println("tipoDocumento: " + tipoDocumento);
		model.addAttribute("tipoDocumento", tipoDocumento);
		
		List<Departamento> listaDepartamento = serviceDepartamentos.buscarTodas();
		model.addAttribute("departamentos", listaDepartamento);
				
		return "visualizar_documentos/listDocumento";
	}
	
	//Controlador para ver las opciones de documentos varios
	@GetMapping("/{nombre1}/index")
	public String mostrarDocumentosVariosIndex(@PathVariable ("nombre1") String nombre, Model model, Authentication authentication) {
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
		
		// Se agregan el listado del clasificador de documentos
		System.out.println("Nombre: " + nombre);
		String liga = "/sgc/documentos_varios/" + nombre + "/index";
		System.out.println("liga: " + liga);
		Menu menu = serviceMenu.buscarPorLiga(liga);
		System.out.println("menu: " + menu);
		ClasificadorDocumento clasificadorDocumento = serviceClasificadorDocumentos.buscarPorId(menu.getIdClasificadorDocumento());
		model.addAttribute("clasificadorDocumento", clasificadorDocumento);
		System.out.println("idClasificadorDocumento: " + clasificadorDocumento.getId_clasificador_documento());
		
		// Se agregan los documentos asociados a la vista
		List<DocumentoConsulta> listaDocumento = serviceDocumentosConsulta.buscarPorClasificadorDocumentoAndEstatus(clasificadorDocumento.getId_clasificador_documento(), 100);
		System.out.println("listaDocumento: " + listaDocumento);
		model.addAttribute("documentos", listaDocumento);
		
		// Se agregan el listado de los departamentos
		List<Departamento> departamento = serviceDepartamentos.buscarTodas();
		model.addAttribute("departamentos", departamento);
		
		return "documentos_varios/listDocumentosVarios";
	}
	
	@GetMapping("/{id}")
	public String mostrarFormatos(@PathVariable ("nombre") String nombre, @PathVariable("id") int id_departamento, Model model, Authentication authentication) {
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
		
		//Nombre del departamento
		Departamento departamento = serviceDepartamentos.buscarPorId(id_departamento);
		model.addAttribute("departamentos", departamento);
		
		// Se busca el menu completo para buscar el documento que le corresponde
		List<Menu> listaTotal = serviceMenu.buscarTodas();
		
		TipoDocumento tipoDocumento = new TipoDocumento();
		
		for(int cont=0;cont<listaTotal.size();cont++) {
			String sinAcentos = StringUtils.stripAccents(listaTotal.get(cont).getNombre());
			listaTotal.get(cont).setNombre(sinAcentos);
			if(nombre.equals(listaTotal.get(cont).getNombre().toLowerCase().replaceAll(" ", "_"))){
				tipoDocumento = serviceTiposDocumentos.buscarPorNombre(listaTotal.get(cont).getNombre());
			}
		}
		System.out.println(tipoDocumento);
		model.addAttribute("tipoDocumento", tipoDocumento);
		
		//Se crea la lista con los documentos guardados
		List<DocumentoConsulta> documento = serviceDocumentosConsulta.buscarPorEstatusAndDepartamentoAndTipoDocumento(100, departamento, tipoDocumento);
		System.out.println("listaDocumento" + documento);
		model.addAttribute("documentos", documento);
		return "visualizar_documentos/listDocumentoDepartamento";
	}
}
