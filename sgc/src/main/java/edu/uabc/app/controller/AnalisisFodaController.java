package edu.uabc.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.uabc.app.model.Departamento;
import edu.uabc.app.model.DocumentoActualizar;
import edu.uabc.app.model.DocumentoConsulta;
import edu.uabc.app.model.TipoArchivo;
import edu.uabc.app.model.TipoDocumento;
import edu.uabc.app.model.UsuarioConsulta;
import edu.uabc.app.model.UsuarioDocumento;
import edu.uabc.app.model.UsuarioDocumentoConsulta;
import edu.uabc.app.service.IDepartamentosService;
import edu.uabc.app.service.IDocumentosActualizarService;
import edu.uabc.app.service.IDocumentosConsultaService;
import edu.uabc.app.service.ITiposArchivosService;
import edu.uabc.app.service.ITiposDocumentosService;
import edu.uabc.app.service.IUsuarioDocumentoConsultaService;
import edu.uabc.app.service.IUsuarioDocumentoService;
import edu.uabc.app.service.IUsuariosConsultaService;
import edu.uabc.app.util.Utileria;

@Controller
@RequestMapping("/analisis_foda")
public class AnalisisFodaController {

	@Autowired
	private IDepartamentosService serviceDepartamentos;
	
	@Autowired
	private IUsuariosConsultaService serviceUsuariosConsulta;
	
	@Autowired
	ITiposDocumentosService serviceTiposDocumentos;
	
	@Autowired
	private IDocumentosActualizarService serviceDocumentosActualizar;
	
	@Autowired
	private IUsuarioDocumentoConsultaService serviceUsuarioDocumentoConsulta;
	
	@Autowired
	private IDocumentosConsultaService serviceDocumentosConsulta;
	
	@Autowired
	private ITiposArchivosService serviceTiposArchivos;
	
	@Autowired
	private IUsuarioDocumentoService serviceUsuarioDocumento;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model, Authentication authentication) {
		// Se agrega el nombre del usuario
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		/*
		List<Departamento> listaDepartamentos = serviceDepartamentos.buscarTodas();
		model.addAttribute("departamentos", listaDepartamentos);
		
		List<UsuarioDocumentoConsulta> listaDocumento = serviceUsuarioDocumentoConsulta.buscarPorUsuario(usuarioAuth);
		System.out.println("Usuario listaUsuarioDocumentoConsulta: " + listaDocumento);

		model.addAttribute("documentos", listaDocumento);
		*/
		
		List<Departamento> listaDepartamento = serviceDepartamentos.buscarTodas();
		model.addAttribute("departamentos", listaDepartamento);
		
		return "analisis_foda/listAnalisisFoda";
	}
	
	@GetMapping("/crear")
	public String crearAnalisisFoda(@ModelAttribute DocumentoActualizar documentoActualizar, Model model, Authentication authentication) {
		// Se agrega el nombre del usuario
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		TipoDocumento tipoDocumento = serviceTiposDocumentos.buscarPorNombre("Análisis FODA");
		model.addAttribute("tipoDocumento", tipoDocumento);

		return "analisis_foda/formAnalisisFoda";
	}
	
	@PostMapping("/guardar")
	public String guardar(@ModelAttribute DocumentoActualizar documento, Authentication authentication, BindingResult result, RedirectAttributes attribute, @RequestParam("ruta") MultipartFile multiPart, HttpServletRequest request) {
		System.out.println("documento analisis de riesgo: " + documento);
		// Se identifica si hubo un error
		if (result.hasErrors()) {
			System.out.println(documento);
			System.out.println("Existieron errores");
			
			return "analisis_foda/formAnalisisFoda";
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
				
				int id_analisisRiesgo = serviceTiposDocumentos.buscarPorNombre("Análisis de Riesgo").getIdTipoDocumento();
				
				// Se identifica que el documento sea del tipo procedimiento, instructivo o formato
						if((documento.getIdTipoDocumento()==id_analisisRiesgo)) {
							
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
							
							//Se identifica la extensión del archivo que se va a guardar
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
							
							//Se insertan los datos del documento en la base de datos
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
		
		attribute.addFlashAttribute("mensaje", "El registro fue guardado");
		
		return "redirect:/analisis_foda/index";
	}
	
	@GetMapping("/{id}")
	public String mostrar(@PathVariable ("id") int idDepartamento, Model model, Authentication authentication) {
		//Se agrega el nombre del usuario
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		Departamento departamento = serviceDepartamentos.buscarPorId(idDepartamento);
		TipoDocumento tipoDocumento = serviceTiposDocumentos.buscarPorNombre("Análisis FODA");
		
		List<DocumentoConsulta> listaDocumento = serviceDocumentosConsulta.buscarPorEstatusAndDepartamentoAndTipoDocumento(100, departamento, tipoDocumento);
		model.addAttribute("departamentos", departamento);
		model.addAttribute("documentos", listaDocumento);
		
		return "analisis_foda/listAnalisisFodaDepartamentos";
	}
}
