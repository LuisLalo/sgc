package edu.uabc.app.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.uabc.app.util.CrearMenu;
import edu.uabc.app.util.EnviarCorreo;
import edu.uabc.app.util.Utileria;
import edu.uabc.app.model.ClasificadorDocumento;
import edu.uabc.app.model.Departamento;
import edu.uabc.app.model.DocumentoActualizar;
import edu.uabc.app.model.DocumentoConsulta;
import edu.uabc.app.model.LineaAutorizacion;
import edu.uabc.app.model.Menu;
import edu.uabc.app.model.Permiso;
import edu.uabc.app.model.TipoArchivo;
import edu.uabc.app.model.TipoDocumento;
import edu.uabc.app.model.UsuarioConsulta;
import edu.uabc.app.model.UsuarioDocumento;
import edu.uabc.app.model.UsuarioDocumentoConsulta;
import edu.uabc.app.service.IClasificadorDocumentosService;
import edu.uabc.app.service.IDepartamentosService;
import edu.uabc.app.service.IDocumentosActualizarService;
import edu.uabc.app.service.IDocumentosConsultaService;
import edu.uabc.app.service.ILineaAutorizacionService;
import edu.uabc.app.service.IMenuService;
import edu.uabc.app.service.IPermisoService;
import edu.uabc.app.service.ITiposArchivosService;
import edu.uabc.app.service.ITiposDocumentosService;
import edu.uabc.app.service.IUsuarioDocumentoConsultaService;
import edu.uabc.app.service.IUsuarioDocumentoService;
import edu.uabc.app.service.IUsuariosConsultaService;

@Controller
@RequestMapping("/documentos")
public class DocumentoController {

	@Autowired
	private IDepartamentosService serviceDepartamentos;
	
	@Autowired
	private IDocumentosActualizarService serviceDocumentosActualizar;
	
	@Autowired
	private IDocumentosConsultaService serviceDocumentosConsulta;
	
	@Autowired
	private ITiposArchivosService serviceTiposArchivos;
	
	@Autowired
	private ITiposDocumentosService serviceTiposDocumentos;
	
	@Autowired
	private IClasificadorDocumentosService serviceClasificadorDocumentos;
	
	@Autowired
	private IUsuariosConsultaService serviceUsuariosConsulta;
	
	@Autowired
	private ILineaAutorizacionService serviceLineaAutorizacion;
	
	@Autowired
	private IUsuarioDocumentoConsultaService serviceUsuarioDocumentoConsulta;
	
	@Autowired
	private IUsuarioDocumentoService serviceUsuarioDocumento;
	
	@Autowired
	private IMenuService serviceMenu;
	
	@Autowired
	private IPermisoService servicePermiso;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model, Authentication authentication) {
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
		
		//Lista de Tipos de Documentos
		List<TipoDocumento> listaTipoDocumento = new ArrayList<TipoDocumento>();
		
		//Lista de Clasificador de Documentos
		List<ClasificadorDocumento> listaClasificadorDocumento = new ArrayList<ClasificadorDocumento>();
		
		//Se identifica si el usuario es usuario tiene permisos de administrador o es usuario normal
		if(usuarioAuth.getRol().getNombre().equals("SGC")) {
			listaTipoDocumento = serviceTiposDocumentos.buscarTodas();
			listaClasificadorDocumento = serviceClasificadorDocumentos.buscarTodas();
			
		} else {
			List<TipoDocumento> listaTD = serviceTiposDocumentos.buscarTodas();
			listaClasificadorDocumento = serviceClasificadorDocumentos.buscarTodas();
			int contador = 0;
			for(int cont=0;cont<listaTD.size();cont++) {
				System.out.println("listaTD.get(cont).getNombre(): " + listaTD.get(cont).getNombre());
				switch (listaTD.get(cont).getNombre()) {
					case "Procedimientos":  listaTipoDocumento.add(contador, listaTD.get(cont));
					contador++;
					break;
					case "Instructivos": listaTipoDocumento.add(contador, listaTD.get(cont));
					contador++;
					break;
					case "Formatos": listaTipoDocumento.add(contador, listaTD.get(cont));
					contador++;
					break;
					case "Documentos varios": listaTipoDocumento.add(contador, listaTD.get(cont));
					contador++;
					break;
					case "Análisis de Riesgo": listaTipoDocumento.add(contador, listaTD.get(cont));
					contador++;
					break;
					case "Análisis FODA": listaTipoDocumento.add(contador, listaTD.get(cont));
					contador++;
					break;
					case "Gestión del Conocimiento": listaTipoDocumento.add(contador, listaTD.get(cont));
					contador++;
					break;
					case "Acciones Correctivas": listaTipoDocumento.add(contador, listaTD.get(cont));
					contador++;
					break;
					case "Proyectos de Mejora": listaTipoDocumento.add(contador, listaTD.get(cont));
					contador++;
					break;
					case "Plan de Acción": listaTipoDocumento.add(contador, listaTD.get(cont));
					contador++;
					break;
					case "Indicadores": listaTipoDocumento.add(contador, listaTD.get(cont));
					contador++;
					break;
				}
			}
			
		}
		
		model.addAttribute("tipoDocumento", listaTipoDocumento);
		model.addAttribute("clasificadorDocumento",  listaClasificadorDocumento);
		
		
		return "documentos/listTiposDocumentos";
		
		
		
		
		/* Código anterior para mostrar el listado de todos los documentos cargados por el usuario, por el volumen de documentos del Administrador del SGC es necesario dividirlos en los tipos de documentos
		//Lista de documentos subidos por el usuario
		List<UsuarioDocumentoConsulta> listaDocumento = serviceUsuarioDocumentoConsulta.buscarPorUsuario(usuarioAuth);
		System.out.println("Usuario listaUsuarioDocumentoConsulta: " + listaDocumento);

		model.addAttribute("documentos", listaDocumento);
		
		if(usuarioAuth.getRol().getNombre().equals("SGC")) {
			return "documentos/listDocumentosAdministrador";
		}
		else {
			return "documentos/listDocumentos";
		}*/
	}
	
	@GetMapping("/{id}")
	public String mostrarDocumentos(@PathVariable("id") int idTipoDocumento, Model model, Authentication authentication) {
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
		
		//Se identifica si el usuario es usuario tiene permisos de administrador o es usuario normal
		if(usuarioAuth.getRol().getNombre().equals("SGC")) {
			//Lista de todos los documentos cargados dentro del sistema
			System.out.println("idTipoDocumento" + idTipoDocumento);
			TipoDocumento tipoDocumento = serviceTiposDocumentos.buscarPorId(idTipoDocumento);
			List<DocumentoConsulta> listaDocumento = serviceDocumentosConsulta.buscarPorTipoDocumento(tipoDocumento);
			System.out.println("Usuario listaUsuarioDocumentoConsultaPorTipoDocumento: " + listaDocumento);
			System.out.println("TipoDocumento: " + tipoDocumento);
			model.addAttribute("tipoDocumento", tipoDocumento);
			model.addAttribute("documentos", listaDocumento);
			return "documentos/listDocumentosAdministrador";
		}
		else {
			//Lista de documentos cargados por el usuario
			UsuarioConsulta usuarioConsulta = serviceUsuariosConsulta.buscarPorId(usuarioAuth.getNum_empleado());
			List<UsuarioDocumentoConsulta> listaDocumento = serviceUsuarioDocumentoConsulta.buscarPorUsuario(usuarioConsulta);
			List<UsuarioDocumentoConsulta> listaD = new ArrayList<UsuarioDocumentoConsulta>();
			
			System.out.println("Usuario listaUsuarioDocumentoConsulta: " + listaDocumento);
			int contador = 0;
			for(int cont=0;cont<listaDocumento.size();cont++) {
				if(listaDocumento.get(cont).getDocumento().getTipoDocumento().getIdTipoDocumento()==idTipoDocumento) {
					listaD.add(contador, listaDocumento.get(cont));
					contador++;
				}
			}
			
			model.addAttribute("documentos", listaD);
			return "documentos/listDocumentos";
		}
	}
	
	@GetMapping("/nuevo")
	public String mostrarNuevo(@ModelAttribute DocumentoActualizar documento, Authentication authentication, Model model) {
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
		
		//Se agregan la lista de los Tipos de Archivo
		List<TipoArchivo> listaTipoArchivo = serviceTiposArchivos.buscarTodas();
		model.addAttribute("tipo_archivo", listaTipoArchivo);
		
		//Se agregan la lista de los tipos de documentos
		List<TipoDocumento> listaTipoDocumento = serviceTiposDocumentos.buscarTodas();
		model.addAttribute("tipo_documento", listaTipoDocumento);
		
		//Se agrega la lista del clasificador de documentos
		List<ClasificadorDocumento> listaClasificadorDocumento = serviceClasificadorDocumentos.buscarTodas();
		model.addAttribute("clasificador_documento", listaClasificadorDocumento);
				
		//Se agrega la lista de los departamentos
		List<Departamento> listaDepartamento = serviceDepartamentos.buscarTodas();
		model.addAttribute("departamentos", listaDepartamento);
		return "documentos/formDocumentosAdministrador";
	}
	
	@GetMapping("/crear")
	public String crear(@ModelAttribute DocumentoActualizar documento, Authentication authentication, Model model) {
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
		
		//Se agregan la lista de los Tipos de Archivo
		List<TipoArchivo> listaTipoArchivo = serviceTiposArchivos.buscarTodas();
		model.addAttribute("tipo_archivo", listaTipoArchivo);
		
		// Si el usuario es el administrador del SGC se le cargan todos los catálogos
		if(usuarioAuth.getRol().getNombre().equals("SGC")) {
			//Se agregan los tipos de documentos
			List<TipoDocumento> listaTipoDocumento = serviceTiposDocumentos.buscarTodas();
			model.addAttribute("tipo_documento", listaTipoDocumento);
			//Se agrega el clasificador de documentos
			List<ClasificadorDocumento> listaClasificadorDocumento = serviceClasificadorDocumentos.buscarTodas();
			model.addAttribute("clasificador_documento", listaClasificadorDocumento);
			
			//Se agregan los departamentos
			List<Departamento> listaDepartamento = serviceDepartamentos.buscarTodas();
			model.addAttribute("departamentos", listaDepartamento);
			
			return "documentos/formDocumentosAdministrador";
		}
		else {
			//Se agregan la lista de los tipos de documentos Procedimientos, Instructivo, Formato y Documentos Varios
			List<TipoDocumento> listaTipoDocumento = new ArrayList<TipoDocumento>();
			TipoDocumento tipoDocumento = serviceTiposDocumentos.buscarPorId(2);
			listaTipoDocumento.add(tipoDocumento);
			tipoDocumento = serviceTiposDocumentos.buscarPorId(3);
			listaTipoDocumento.add(tipoDocumento);
			tipoDocumento = serviceTiposDocumentos.buscarPorId(4);
			listaTipoDocumento.add(tipoDocumento);
			tipoDocumento = serviceTiposDocumentos.buscarPorId(5);
			listaTipoDocumento.add(tipoDocumento);
			tipoDocumento = serviceTiposDocumentos.buscarPorId(6);
			listaTipoDocumento.add(tipoDocumento);
			tipoDocumento = serviceTiposDocumentos.buscarPorId(7);
			listaTipoDocumento.add(tipoDocumento);
			tipoDocumento = serviceTiposDocumentos.buscarPorId(8);
			listaTipoDocumento.add(tipoDocumento);
			tipoDocumento = serviceTiposDocumentos.buscarPorId(15);
			listaTipoDocumento.add(tipoDocumento);
			tipoDocumento = serviceTiposDocumentos.buscarPorId(16);
			listaTipoDocumento.add(tipoDocumento);
			tipoDocumento = serviceTiposDocumentos.buscarPorId(17);
			listaTipoDocumento.add(tipoDocumento);
			tipoDocumento = serviceTiposDocumentos.buscarPorId(18);
			listaTipoDocumento.add(tipoDocumento);
			model.addAttribute("tipo_documento", listaTipoDocumento);
			return "documentos/formDocumentosNuevo";
		}
		
	}
	
	@PostMapping("/guardar")
	public String guardar(@ModelAttribute DocumentoActualizar documento, Authentication authentication, BindingResult result, RedirectAttributes attribute, @RequestParam("ruta") MultipartFile multiPart, HttpServletRequest request) throws IOException {
		System.out.println("Documento: " + documento);
		// Se identifica si hubo un error
		if (result.hasErrors()) {
			System.out.println(documento);
			System.out.println("Existieron errores");
			
			return "documentos/formDocumentosNuevo";
		}
		
		// Se busca la información del usuario que está guardando el documento
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		
		// Se identifica el departamento del usuario que guarda el documento
		Departamento departamento = serviceDepartamentos.buscarPorId(documento.getIdDepartamento());
		
		// Si el documento no trae id del departamento es porque lo guarda un usuario que solo guarda archivos de su departamento, se toma el id del departamento de los datos del usuario
		if((departamento==null) || (departamento.getId_departamento()==0)) {
			documento.setIdDepartamento(usuarioAuth.getDepartamento().getId_departamento());
			departamento = serviceDepartamentos.buscarPorId(usuarioAuth.getDepartamento().getId_departamento());
			System.out.println("usuario auth: " + usuarioAuth);
		}
		
		// Se buscan los id de los tipos de documentos Procedimientos, Instructivos, Formatos y del manual del SGC que se van a guardar
		int id_manualSGC = serviceTiposDocumentos.buscarPorNombre("Manual de Gestión SGC").getIdTipoDocumento();
		int id_procedimiento = serviceTiposDocumentos.buscarPorNombre("Procedimientos").getIdTipoDocumento();
		int id_instructivo = serviceTiposDocumentos.buscarPorNombre("Instructivos").getIdTipoDocumento();
		int id_formato = serviceTiposDocumentos.buscarPorNombre("Formatos").getIdTipoDocumento();
		int id_analisisRiesgo = serviceTiposDocumentos.buscarPorNombre("Análisis de Riesgo").getIdTipoDocumento();
		int id_analisisFoda = serviceTiposDocumentos.buscarPorNombre("Análisis FODA").getIdTipoDocumento();
		
		// ** Documetos que se guardan al hacer el cambio en el procedimiento para guardar todos los archivos
		int id_documentosVarios = serviceTiposDocumentos.buscarPorNombre("Documentos Varios").getIdTipoDocumento();
		int id_gestionConocimiento = serviceTiposDocumentos.buscarPorNombre("Gestión del Conocimiento").getIdTipoDocumento();
		int id_documentos_varios = serviceTiposDocumentos.buscarPorNombre("Documentos Varios").getIdTipoDocumento();
		int id_programaActividades = serviceTiposDocumentos.buscarPorNombre("Programa de Actividades").getIdTipoDocumento();
		int id_reunionDirectiva = serviceTiposDocumentos.buscarPorNombre("Reunión Directiva").getIdTipoDocumento();
		int id_auditoriaInterna = serviceTiposDocumentos.buscarPorNombre("Resultados de Auditoría Interna").getIdTipoDocumento();
		int id_auditoriaExterna = serviceTiposDocumentos.buscarPorNombre("Resultados de Auditoría Externa").getIdTipoDocumento();
		int id_climaLaboral = serviceTiposDocumentos.buscarPorNombre("Encuesta Clima Laboral").getIdTipoDocumento();
		int id_reunionesTrabajo = serviceTiposDocumentos.buscarPorNombre("Reuniones de Trabajo").getIdTipoDocumento();
		int id_accionesCorrectivas = serviceTiposDocumentos.buscarPorNombre("Acciones Correctivas").getIdTipoDocumento();
		int id_proyectosMejora = serviceTiposDocumentos.buscarPorNombre("Proyectos de Mejora").getIdTipoDocumento();
		int id_planAccion = serviceTiposDocumentos.buscarPorNombre("Plan de Acción").getIdTipoDocumento();
		int id_indicadores = serviceTiposDocumentos.buscarPorNombre("Indicadores").getIdTipoDocumento();
		
		
		// Se identifica que el documento sea del tipo procedimiento, instructivo o formato
		if((documento.getIdTipoDocumento()==id_manualSGC)||(documento.getIdTipoDocumento()==id_procedimiento) ||(documento.getIdTipoDocumento()==id_instructivo)||(documento.getIdTipoDocumento()==id_formato)||(documento.getIdTipoDocumento()==id_analisisRiesgo)||(documento.getIdTipoDocumento()==id_analisisFoda)
				||(documento.getIdTipoDocumento()==id_documentosVarios)||(documento.getIdTipoDocumento()==id_gestionConocimiento)||(documento.getIdTipoDocumento()==id_documentos_varios)||(documento.getIdTipoDocumento()==id_programaActividades)||(documento.getIdTipoDocumento()==id_reunionDirectiva)
				||(documento.getIdTipoDocumento()==id_auditoriaInterna)||(documento.getIdTipoDocumento()==id_auditoriaExterna)||(documento.getIdTipoDocumento()==id_climaLaboral)||(documento.getIdTipoDocumento()==id_reunionesTrabajo)||(documento.getIdTipoDocumento()==id_accionesCorrectivas)
				||(documento.getIdTipoDocumento()==id_proyectosMejora)||(documento.getIdTipoDocumento()==id_planAccion)||(documento.getIdTipoDocumento()==id_indicadores)) {
			
			// Se identifica si el usuario que guarda el documento es el administrador del SGC y realizó el guardado desde el formulario básico
			if((usuarioAuth.getRol().getNombre().equals("SGC")) && (documento.getEstatus()==0)) {
				// //Se agrega el estatus para que inicie la linea de autorización
				System.out.println("Documento SGC: " + documento);
				documento.setEstatus(1);
				System.out.println("Documento SGC: " + documento);
			}
			if((usuarioAuth.getRol().getNombre().equals("SGC")) && (documento.getEstatus()==100)) {
				// Si el administrador autorizó el documento de forma directa, se publicará de forma inmediata después de guardarse
				System.out.println("Documento SGC: " + documento);
			}
			else {
				// El usuario no es el administrador del SGC
				
				//Se agrega el estatus para que inicie la linea de autorización
				System.out.println("Documento: " + documento);
				documento.setEstatus(1);
				System.out.println("Documento: " + documento);
				//Se agrega el departamento correspondiente
				documento.setIdDepartamento(usuarioAuth.getDepartamento().getId_departamento());
			}
			
			// Se identifica el tipo de documento
			String tipoDocumento = serviceTiposDocumentos.buscarPorId(documento.getIdTipoDocumento()).getRuta();
			
			// Se identifica la extensión del archivo que se va a guardar
			String extension = Utileria.agregarExtensionArchivos(multiPart);
			List<TipoArchivo> listaTipoArchivo = serviceTiposArchivos.buscarTodas();
			int id_tipo_archivo = Utileria.identificarExtensionArchivos(extension, listaTipoArchivo);
			extension = extension.toLowerCase();
			System.out.println("Extension: " + extension);
			documento.setIdTipoArchivo(id_tipo_archivo);
			
			// Se guarda el archivo en el directorio del disco duro
			if(!multiPart.isEmpty()) {
				String nombreDocumento = Utileria.guardarDocumento(multiPart, request, tipoDocumento, departamento.getRuta(), extension);
				documento.setRuta(nombreDocumento);
			}
			
			// Se insertan los datos del documento en la base de datos
			System.out.println("Documento guardado: " + documento);
			serviceDocumentosActualizar.insertar(documento);
			
			// Se insertan los valores a la tabla usuario_documento
			// Se obtiene el id del último documento guardado en la base de datos
			int idUltimoDocumento = serviceDocumentosConsulta.buscarPrimeroPorIdDocumentoOrdenadoDesc().getIdDocumento();
			
			// Se obtiene el id del último registro en la tabla usuario_documento, se utiliza el modelo UsuarioDocumento
			UsuarioDocumento ultimoUsuarioDocumento = serviceUsuarioDocumento.buscarPrimeroPorIdUsuarioDocumentoOrdenadosDesc();
			
			int idUltimoUsuarioDocumento;
			
			// Se verifica que el valor no sea null
			if((ultimoUsuarioDocumento!=null)){
				// Se toma el valor que tiene el id del último registro de la tabla usuario_documento y se aumenta
				idUltimoUsuarioDocumento = serviceUsuarioDocumento.buscarPrimeroPorIdUsuarioDocumentoOrdenadosDesc().getIdUsuarioDocumento();
				idUltimoUsuarioDocumento++;
			}
			else {
				// Se inicia con el valor 1
				idUltimoUsuarioDocumento=1;
			}
			// Se agregan los campos que se requieren para la inserción
			UsuarioDocumento usuarioDocumento = new UsuarioDocumento();
			usuarioDocumento.setIdUsuarioDocumento(idUltimoUsuarioDocumento);
			usuarioDocumento.setNumEmpleado(usuarioAuth.getNum_empleado());
			usuarioDocumento.setIdDocumento(idUltimoDocumento);
			System.out.println("Resultado Usuario Documento: " + usuarioDocumento);
			// Se realiza la inserción a la tabla
			serviceUsuarioDocumento.insertar(usuarioDocumento);
			
			attribute.addFlashAttribute("mensaje", "El registro fue guardado");
		}
		
		
		return "redirect:/documentos/index";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	@GetMapping(value="/editar/{id}")
	public String editar(@PathVariable("id") int id_documento, Model model, Authentication authentication) {
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
		
		List<ClasificadorDocumento> listaClasificadorDocumento = serviceClasificadorDocumentos.buscarTodas();
		model.addAttribute("clasificador_documento", listaClasificadorDocumento);
		List<Departamento> listaDepartamento = serviceDepartamentos.buscarTodas();
		model.addAttribute("departamentos", listaDepartamento);
		List<TipoDocumento> listaTipoDocumento = serviceTiposDocumentos.buscarTodas();
		model.addAttribute("tipo_documento", listaTipoDocumento);
		DocumentoActualizar documento = serviceDocumentosActualizar.buscarPorId(id_documento);
		System.out.println("Documento por editar: " + documento);
		System.out.println("Fecha del documento: " + documento.getFechaCreacion());
		//Date date = Utileria.devolverFecha();
		//System.out.println("Fecha con formato nuevo: " + date);
		model.addAttribute("documentoActualizar", documento);
		
		// Se identifica si el usuario es el administrador del SGC para mostrarle el formulario completo
		if(usuarioAuth.getRol().getNombre().equals("SGC")) {
			return "documentos/formDocumentosAdministrador";
		}
		else {
			return "documentos/formDocumentosNuevo";
		}
		
	}
	
	@GetMapping(value="eliminar/{id}")
	public String eliminar(@PathVariable("id") int id_documento, RedirectAttributes attributes) {
		serviceDocumentosActualizar.eliminar(id_documento);
		attributes.addFlashAttribute("mensaje", "El documento fue eliminado");
		return "redirect:/documentos/index";
	}
	
	@GetMapping(value="/autorizar")
	public String documentosPorAutorizar(Authentication authentication, Model model) {
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
		
		// Documentos a autorizar para el puesto de contador
		if(usuarioAuth.getPuesto().getNombre().equals("Contador")) {
			// Se buscan los departamentos que integran la Contaduría
			Departamento apoyoInformatico = serviceDepartamentos.buscarPorNombre("Apoyo Informático");
			Departamento contabilidad = serviceDepartamentos.buscarPorNombre("Contabilidad");
			Departamento controlPatrimonial = serviceDepartamentos.buscarPorNombre("Control Patrimonial");
			System.out.println("Departamento 1: " + apoyoInformatico);
			System.out.println("Departamento 2: " + contabilidad);
			System.out.println("Departamento 3: " + controlPatrimonial);
			// Se identifica el total de niveles de la línea de autorización de cada departamanto
			int nivelesApoyoInformatico = (serviceLineaAutorizacion.buscarPorDepartamento(apoyoInformatico.getId_departamento())).size();
			int nivelesContabilidad = (serviceLineaAutorizacion.buscarPorDepartamento(contabilidad.getId_departamento())).size();
			int nivelesControlPatrimonial = (serviceLineaAutorizacion.buscarPorDepartamento(controlPatrimonial.getId_departamento())).size();
			System.out.println("Niveles Departamento 1: " + nivelesApoyoInformatico);
			System.out.println("Niveles Departamento 2: " + nivelesContabilidad);
			System.out.println("Niveles Departamento 3: " + nivelesControlPatrimonial);
			// Se identifica el nivel del Contador dentro de la línea de autorización de cada departamento
			LineaAutorizacion lAI = serviceLineaAutorizacion.buscarPorNumEmpleadoAndDepartamento(usuarioAuth.getNum_empleado(), apoyoInformatico.getId_departamento());
			LineaAutorizacion lC = serviceLineaAutorizacion.buscarPorNumEmpleadoAndDepartamento(usuarioAuth.getNum_empleado(), contabilidad.getId_departamento());
			LineaAutorizacion lCP = serviceLineaAutorizacion.buscarPorNumEmpleadoAndDepartamento(usuarioAuth.getNum_empleado(), controlPatrimonial.getId_departamento());
			System.out.println("linea autorizacion 1: " + lAI);
			System.out.println("linea autorizacion 2: " + lC);
			System.out.println("linea autorizacion 3: " + lCP);
			
			List<DocumentoConsulta> listaDocAI = new LinkedList<>();
			List<DocumentoConsulta> listaDocC = new LinkedList<>();
			List<DocumentoConsulta> listaDocCP = new LinkedList<>();
			List<DocumentoConsulta> listaDocumentoConsulta = new LinkedList<>();
			
			// Se buscan los documentos pendientes de autorización mientras se encuentren niveles en las líneas de autorización
			if(lAI!=null) {
				listaDocAI = serviceDocumentosConsulta.buscarPorEstatusAndDepartamento((lAI.getNivel()-1), apoyoInformatico);
				int cont = 0;
				while(cont<listaDocAI.size()) {
					listaDocumentoConsulta.add(listaDocAI.get(cont));
					cont++;
				}
			}
			if(lC!=null) {
				listaDocC = serviceDocumentosConsulta.buscarPorEstatusAndDepartamento((lC.getNivel()-1), contabilidad);
				int cont = 0;
				while(cont<listaDocC.size()) {
					listaDocumentoConsulta.add(listaDocC.get(cont));
					cont++;
				}
			}
			if(lCP!=null) {
				listaDocCP = serviceDocumentosConsulta.buscarPorEstatusAndDepartamento((lCP.getNivel()-1), controlPatrimonial);
				int cont = 0;
				while(cont<listaDocCP.size()) {
					listaDocumentoConsulta.add(listaDocCP.get(cont));
					cont++;
				}
			}
			// Se agrega el listado al modelo para agregarlo a la vista
			model.addAttribute("documentos", listaDocumentoConsulta);
			
			return "documentos/listDocumentosPorAutorizar";
		}
		
		// Documentos a autorizar para el puesto de Jefe de la Unidad de Presupuesto y Finanzas
		if(usuarioAuth.getPuesto().getNombre().equals("Jefa de Presupuestos y Finanzas")) {
			// Se buscan los departamentos que integran la Unidad de Presupuesto y Finanzas
			Departamento presupuestos = serviceDepartamentos.buscarPorNombre("Presupuestos");
			Departamento finanzas = serviceDepartamentos.buscarPorNombre("Finanzas");

			System.out.println("Departamento 1: " + presupuestos);
			System.out.println("Departamento 2: " + finanzas);

			// Se identifica el total de niveles de la línea de autorización de cada departamanto
			int nivelesPresupuestos = (serviceLineaAutorizacion.buscarPorDepartamento(presupuestos.getId_departamento())).size();
			int nivelesFinanzas = (serviceLineaAutorizacion.buscarPorDepartamento(finanzas.getId_departamento())).size();

			System.out.println("Niveles Departamento 1: " + nivelesPresupuestos);
			System.out.println("Niveles Departamento 2: " + nivelesFinanzas);

			// Se identifica el nivel del Jefe de la Unidad de Presupuesto y Finanzas dentro de la línea de autorización de cada departamento
			LineaAutorizacion lP = serviceLineaAutorizacion.buscarPorNumEmpleadoAndDepartamento(usuarioAuth.getNum_empleado(), presupuestos.getId_departamento());
			LineaAutorizacion lF = serviceLineaAutorizacion.buscarPorNumEmpleadoAndDepartamento(usuarioAuth.getNum_empleado(),  finanzas.getId_departamento());

			System.out.println("linea autorizacion 1: " + lP);
			System.out.println("linea autorizacion 2: " + lF);

			
			List<DocumentoConsulta> listaDocP = new LinkedList<>();
			List<DocumentoConsulta> listaDocF = new LinkedList<>();
			List<DocumentoConsulta> listaDocumentoConsulta = new LinkedList<>();
			
			// Se buscan los documentos pendientes de autorización mientras se encuentren niveles en las líneas de autorización
			if(lP!=null) {
				listaDocP = serviceDocumentosConsulta.buscarPorEstatusAndDepartamento((lP.getNivel()-1), presupuestos);
				int cont = 0;
				while(cont<listaDocP.size()) {
					listaDocumentoConsulta.add(listaDocP.get(cont));
					cont++;
				}
			}
			if(lF!=null) {
				listaDocF = serviceDocumentosConsulta.buscarPorEstatusAndDepartamento((lF.getNivel()-1), finanzas);
				int cont = 0;
				while(cont<listaDocF.size()) {
					listaDocumentoConsulta.add(listaDocF.get(cont));
					cont++;
				}
			}
			
			// Se agrega el listado al modelo para agregarlo a la vista
			model.addAttribute("documentos", listaDocumentoConsulta);
			
			return "documentos/listDocumentosPorAutorizar";
		}
		
		// Documentos a autorizar para el puesto de Administrador del SGC
		if(usuarioAuth.getRol().getNombre().equals("SGC")) {
			// Se buscan los departamentos que integran la Contaduría
			Departamento apoyoInformatico = serviceDepartamentos.buscarPorNombre("Apoyo Informático");
			Departamento contabilidad = serviceDepartamentos.buscarPorNombre("Contabilidad");
			Departamento controlPatrimonial = serviceDepartamentos.buscarPorNombre("Control Patrimonial");
			Departamento presupuestos = serviceDepartamentos.buscarPorNombre("Presupuestos");
			Departamento finanzas = serviceDepartamentos.buscarPorNombre("Finanzas");
			System.out.println("Departamento 1: " + apoyoInformatico);
			System.out.println("Departamento 2: " + contabilidad);
			System.out.println("Departamento 3: " + controlPatrimonial);
			// Se identifica el total de niveles de la línea de autorización de cada departamanto
			int nivelesApoyoInformatico = (serviceLineaAutorizacion.buscarPorDepartamento(apoyoInformatico.getId_departamento())).size();
			int nivelesContabilidad = (serviceLineaAutorizacion.buscarPorDepartamento(contabilidad.getId_departamento())).size();
			int nivelesControlPatrimonial = (serviceLineaAutorizacion.buscarPorDepartamento(controlPatrimonial.getId_departamento())).size();
			int nivelesPresupuestos = (serviceLineaAutorizacion.buscarPorDepartamento(presupuestos.getId_departamento())).size();
			int nivelesFinanzas = (serviceLineaAutorizacion.buscarPorDepartamento(finanzas.getId_departamento())).size();
			System.out.println("Niveles Departamento 1: " + nivelesApoyoInformatico);
			System.out.println("Niveles Departamento 2: " + nivelesContabilidad);
			System.out.println("Niveles Departamento 3: " + nivelesControlPatrimonial);
			System.out.println("Niveles Departamento 4: " + nivelesPresupuestos);
			System.out.println("Niveles Departamento 5: " + nivelesFinanzas);
			// Se identifica el nivel del Tesorero o Administrador del SGC dentro de la línea de autorización de cada departamento
			LineaAutorizacion lAI = serviceLineaAutorizacion.buscarPorNumEmpleadoAndDepartamento(usuarioAuth.getNum_empleado(), apoyoInformatico.getId_departamento());
			LineaAutorizacion lC = serviceLineaAutorizacion.buscarPorNumEmpleadoAndDepartamento(usuarioAuth.getNum_empleado(), contabilidad.getId_departamento());
			LineaAutorizacion lCP = serviceLineaAutorizacion.buscarPorNumEmpleadoAndDepartamento(usuarioAuth.getNum_empleado(), controlPatrimonial.getId_departamento());
			LineaAutorizacion lP = serviceLineaAutorizacion.buscarPorNumEmpleadoAndDepartamento(usuarioAuth.getNum_empleado(), presupuestos.getId_departamento());
			LineaAutorizacion lF = serviceLineaAutorizacion.buscarPorNumEmpleadoAndDepartamento(usuarioAuth.getNum_empleado(),  finanzas.getId_departamento());
			System.out.println("linea autorizacion 1: " + lAI);
			System.out.println("linea autorizacion 2: " + lC);
			System.out.println("linea autorizacion 3: " + lCP);
			
			List<DocumentoConsulta> listaDocAI = new LinkedList<>();
			List<DocumentoConsulta> listaDocC = new LinkedList<>();
			List<DocumentoConsulta> listaDocCP = new LinkedList<>();
			List<DocumentoConsulta> listaDocP = new LinkedList<>();
			List<DocumentoConsulta> listaDocF = new LinkedList<>();
			List<DocumentoConsulta> listaDocumentoConsulta = new LinkedList<>();
			
			// Se buscan los documentos pendientes de autorización mientras se encuentren niveles en las líneas de autorización
			if(lAI!=null) {
				listaDocAI = serviceDocumentosConsulta.buscarPorEstatusAndDepartamento((lAI.getNivel()-1), apoyoInformatico);
				int cont = 0;
				while(cont<listaDocAI.size()) {
					listaDocumentoConsulta.add(listaDocAI.get(cont));
					cont++;
				}
			}
			if(lC!=null) {
				listaDocC = serviceDocumentosConsulta.buscarPorEstatusAndDepartamento((lC.getNivel()-1), contabilidad);
				int cont = 0;
				while(cont<listaDocC.size()) {
					listaDocumentoConsulta.add(listaDocC.get(cont));
					cont++;
				}
			}
			if(lCP!=null) {
				listaDocCP = serviceDocumentosConsulta.buscarPorEstatusAndDepartamento((lCP.getNivel()-1), controlPatrimonial);
				int cont = 0;
				while(cont<listaDocCP.size()) {
					listaDocumentoConsulta.add(listaDocCP.get(cont));
					cont++;
				}
			}
			if(lP!=null) {
				listaDocP = serviceDocumentosConsulta.buscarPorEstatusAndDepartamento((lP.getNivel()-1), presupuestos);
				int cont = 0;
				while(cont<listaDocP.size()) {
					listaDocumentoConsulta.add(listaDocP.get(cont));
					cont++;
				}
			}
			if(lF!=null) {
				listaDocF = serviceDocumentosConsulta.buscarPorEstatusAndDepartamento((lF.getNivel()-1), finanzas);
				int cont = 0;
				while(cont<listaDocF.size()) {
					listaDocumentoConsulta.add(listaDocF.get(cont));
					cont++;
				}
			}
			// Se agrega el listado al modelo para agregarlo a la vista
			model.addAttribute("documentos", listaDocumentoConsulta);
			
			return "documentos/listDocumentosPorAutorizar";
		}
		
		// Documentos a autorizar para el puesto de Tesorero
		if(usuarioAuth.getPuesto().getNombre().equals("Tesorero")) {
			// Se buscan los departamentos que integran la Contaduría
			Departamento apoyoInformatico = serviceDepartamentos.buscarPorNombre("Apoyo Informático");
			Departamento contabilidad = serviceDepartamentos.buscarPorNombre("Contabilidad");
			Departamento controlPatrimonial = serviceDepartamentos.buscarPorNombre("Control Patrimonial");
			Departamento presupuestos = serviceDepartamentos.buscarPorNombre("Presupuestos");
			Departamento finanzas = serviceDepartamentos.buscarPorNombre("Finanzas");
			Departamento seguimientoEvaluacion = serviceDepartamentos.buscarPorNombre("Seguimiento y Evaluación");
			System.out.println("Departamento 1: " + apoyoInformatico);
			System.out.println("Departamento 2: " + contabilidad);
			System.out.println("Departamento 3: " + controlPatrimonial);
			// Se identifica el total de niveles de la línea de autorización de cada departamanto
			int nivelesApoyoInformatico = (serviceLineaAutorizacion.buscarPorDepartamento(apoyoInformatico.getId_departamento())).size();
			int nivelesContabilidad = (serviceLineaAutorizacion.buscarPorDepartamento(contabilidad.getId_departamento())).size();
			int nivelesControlPatrimonial = (serviceLineaAutorizacion.buscarPorDepartamento(controlPatrimonial.getId_departamento())).size();
			int nivelesPresupuestos = (serviceLineaAutorizacion.buscarPorDepartamento(presupuestos.getId_departamento())).size();
			int nivelesFinanzas = (serviceLineaAutorizacion.buscarPorDepartamento(finanzas.getId_departamento())).size();
			int nivelesSeguimientoEvaluacion = (serviceLineaAutorizacion.buscarPorDepartamento(seguimientoEvaluacion.getId_departamento())).size();
			System.out.println("Niveles Departamento 1: " + nivelesApoyoInformatico);
			System.out.println("Niveles Departamento 2: " + nivelesContabilidad);
			System.out.println("Niveles Departamento 3: " + nivelesControlPatrimonial);
			System.out.println("Niveles Departamento 4: " + nivelesPresupuestos);
			System.out.println("Niveles Departamento 5: " + nivelesFinanzas);
			// Se identifica el nivel del Tesorero o Administrador del SGC dentro de la línea de autorización de cada departamento
			LineaAutorizacion lAI = serviceLineaAutorizacion.buscarPorNumEmpleadoAndDepartamento(usuarioAuth.getNum_empleado(), apoyoInformatico.getId_departamento());
			LineaAutorizacion lC = serviceLineaAutorizacion.buscarPorNumEmpleadoAndDepartamento(usuarioAuth.getNum_empleado(), contabilidad.getId_departamento());
			LineaAutorizacion lCP = serviceLineaAutorizacion.buscarPorNumEmpleadoAndDepartamento(usuarioAuth.getNum_empleado(), controlPatrimonial.getId_departamento());
			LineaAutorizacion lP = serviceLineaAutorizacion.buscarPorNumEmpleadoAndDepartamento(usuarioAuth.getNum_empleado(), presupuestos.getId_departamento());
			LineaAutorizacion lF = serviceLineaAutorizacion.buscarPorNumEmpleadoAndDepartamento(usuarioAuth.getNum_empleado(),  finanzas.getId_departamento());
			LineaAutorizacion lSE = serviceLineaAutorizacion.buscarPorNumEmpleadoAndDepartamento(usuarioAuth.getNum_empleado(),  seguimientoEvaluacion.getId_departamento());
			System.out.println("linea autorizacion 1: " + lAI);
			System.out.println("linea autorizacion 2: " + lC);
			System.out.println("linea autorizacion 3: " + lCP);
			
			List<DocumentoConsulta> listaDocAI = new LinkedList<>();
			List<DocumentoConsulta> listaDocC = new LinkedList<>();
			List<DocumentoConsulta> listaDocCP = new LinkedList<>();
			List<DocumentoConsulta> listaDocP = new LinkedList<>();
			List<DocumentoConsulta> listaDocF = new LinkedList<>();
			List<DocumentoConsulta> listaDocSE = new LinkedList<>();
			List<DocumentoConsulta> listaDocumentoConsulta = new LinkedList<>();
			
			// Se buscan los documentos pendientes de autorización mientras se encuentren niveles en las líneas de autorización
			if(lAI!=null) {
				listaDocAI = serviceDocumentosConsulta.buscarPorEstatusAndDepartamento((lAI.getNivel()-1), apoyoInformatico);
				int cont = 0;
				while(cont<listaDocAI.size()) {
					listaDocumentoConsulta.add(listaDocAI.get(cont));
					cont++;
				}
			}
			if(lC!=null) {
				listaDocC = serviceDocumentosConsulta.buscarPorEstatusAndDepartamento((lC.getNivel()-1), contabilidad);
				int cont = 0;
				while(cont<listaDocC.size()) {
					listaDocumentoConsulta.add(listaDocC.get(cont));
					cont++;
				}
			}
			if(lCP!=null) {
				listaDocCP = serviceDocumentosConsulta.buscarPorEstatusAndDepartamento((lCP.getNivel()-1), controlPatrimonial);
				int cont = 0;
				while(cont<listaDocCP.size()) {
					listaDocumentoConsulta.add(listaDocCP.get(cont));
					cont++;
				}
			}
			if(lP!=null) {
				listaDocP = serviceDocumentosConsulta.buscarPorEstatusAndDepartamento((lP.getNivel()-1), presupuestos);
				int cont = 0;
				while(cont<listaDocP.size()) {
					listaDocumentoConsulta.add(listaDocP.get(cont));
					cont++;
				}
			}
			if(lF!=null) {
				listaDocF = serviceDocumentosConsulta.buscarPorEstatusAndDepartamento((lF.getNivel()-1), finanzas);
				int cont = 0;
				while(cont<listaDocF.size()) {
					listaDocumentoConsulta.add(listaDocF.get(cont));
					cont++;
				}
			}
			if(lSE!=null) {
				listaDocSE = serviceDocumentosConsulta.buscarPorEstatusAndDepartamento((lSE.getNivel()-1), seguimientoEvaluacion);
				int cont = 0;
				while(cont<listaDocSE.size()) {
					listaDocumentoConsulta.add(listaDocSE.get(cont));
					cont++;
				}
			}
			// Se agrega el listado al modelo para agregarlo a la vista
			model.addAttribute("documentos", listaDocumentoConsulta);
			
			return "documentos/listDocumentosPorAutorizar";
		}
		
		else {
			//Se buscan el departamento y el nivel del empleado
			LineaAutorizacion lineaAutorizacion = serviceLineaAutorizacion.buscarPorNumEmpleado(usuarioAuth.getNum_empleado());
			Departamento departamento = serviceDepartamentos.buscarPorId(usuarioAuth.getDepartamento().getId_departamento());
			
			//Se buscan los documentos pendientes de autorizar
			List<DocumentoConsulta> listaDocumentoConsulta = serviceDocumentosConsulta.buscarPorEstatusAndDepartamento((lineaAutorizacion.getNivel()-1), departamento);
			model.addAttribute("documentos", listaDocumentoConsulta);
			System.out.println(listaDocumentoConsulta);
			
			return "documentos/listDocumentosPorAutorizar";
		}
	}
	
	@GetMapping(value="/autorizar/editar/{id}")
	public String documentosAutorizarEditar(@PathVariable("id") int id_documento, Model model, Authentication authentication) {
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
				
		List<TipoArchivo> listaTipoArchivo = serviceTiposArchivos.buscarTodas();
		model.addAttribute("tipo_archivo", listaTipoArchivo);
		List<TipoDocumento> listaTipoDocumento = serviceTiposDocumentos.buscarTodas();
		model.addAttribute("tipo_documento", listaTipoDocumento);
		List<ClasificadorDocumento> listaClasificadorDocumento = serviceClasificadorDocumentos.buscarTodas();
		model.addAttribute("clasificador_documento", listaClasificadorDocumento);
		List<Departamento> listaDepartamento = serviceDepartamentos.buscarTodas();
		model.addAttribute("departamentos", listaDepartamento);
		DocumentoActualizar documento = serviceDocumentosActualizar.buscarPorId(id_documento);
		model.addAttribute("documentoActualizar", documento);
		return "documentos/formDocumentosNuevo";
	}
	
	@GetMapping(value="/autorizar/{id}")
	public String documentosAutorizar(@PathVariable("id") int id_documento, RedirectAttributes attribute) throws Exception {
		// Se identifica el documento que se va a autorizar
		DocumentoActualizar documentoActualizar = serviceDocumentosActualizar.buscarPorId(id_documento);
		List<LineaAutorizacion> listaLineaAutorizacion = serviceLineaAutorizacion.buscarPorDepartamento(documentoActualizar.getIdDepartamento());
		
		// Se compara que el tamaño de la linea de autorización sea mayor al estatus del documento
		if(listaLineaAutorizacion.size()>documentoActualizar.getEstatus()){
			int estatus = documentoActualizar.getEstatus();
			estatus++;
			
			// Se valida que sea el último nivel de validación para su publicación
			if(listaLineaAutorizacion.size()==estatus) {
				// Se hace actualiza con estatus de publicación
				estatus=100;
				documentoActualizar.setEstatus(estatus);
				serviceDocumentosActualizar.insertar(documentoActualizar);
			}
			else {
				// Se hace actualiza el estatus para que suba al siguiente nivel
				documentoActualizar.setEstatus(estatus);
				serviceDocumentosActualizar.insertar(documentoActualizar);
				
				EnviarCorreo.EnviarNotificacion();
				
			}
			
			attribute.addFlashAttribute("mensaje", "El registro fue aprobado");
		}
		else {
			attribute.addFlashAttribute("mensaje", "Existieron errores");
		}
		return "redirect:/documentos/autorizar";
	}
	
	// Método pendiente de implementar
	@GetMapping(value="/devolver/{id}")
	public String documentosDevolver(@PathVariable("id") int id_documento, RedirectAttributes attribute) {
		DocumentoActualizar documentoActualizar = serviceDocumentosActualizar.buscarPorId(id_documento);
		List<LineaAutorizacion> listaLineaAutorizacion = serviceLineaAutorizacion.buscarPorDepartamento(documentoActualizar.getIdDepartamento());
		
		// Se compara que el tamaño de la linea de autorización sea mayor al estatus del documento
		if(listaLineaAutorizacion.size()>documentoActualizar.getEstatus()){
			int estatus = documentoActualizar.getEstatus();
			estatus--;
			documentoActualizar.setEstatus(estatus);
			serviceDocumentosActualizar.insertar(documentoActualizar);
			attribute.addFlashAttribute("mensaje", "El documento fue devuelto");
		}
		else {
			//attribute.addFlashAttribute("mensaje", "Existieron errores");
		}
		return "redirect:/documentos/autorizar";
	}
	
	@GetMapping(value="/editar/{id}/administrador")
	public String editarAdministrador(@PathVariable("id") int id_documento, Model model) {
		List<TipoArchivo> listaTipoArchivo = serviceTiposArchivos.buscarTodas();
		model.addAttribute("tipo_archivo", listaTipoArchivo);
		List<TipoDocumento> listaTipoDocumento = serviceTiposDocumentos.buscarTodas();
		model.addAttribute("tipo_documento", listaTipoDocumento);
		List<ClasificadorDocumento> listaClasificadorDocumento = serviceClasificadorDocumentos.buscarTodas();
		model.addAttribute("clasificador_documento", listaClasificadorDocumento);
		List<Departamento> listaDepartamento = serviceDepartamentos.buscarTodas();
		model.addAttribute("departamentos", listaDepartamento);
		DocumentoActualizar documento = serviceDocumentosActualizar.buscarPorId(id_documento);
		model.addAttribute("documento", documento);
		return "documentos/formDocumentosAdministrador";
	}
	
	@RequestMapping("verificar")
	public @ResponseBody String verificarDocumento(@RequestParam("id") int id) {
		
		String msg = "";
		
		if(id==1) {
			msg = id + "Ya Existe!";
		}
		
		
		return msg;
	}
}
