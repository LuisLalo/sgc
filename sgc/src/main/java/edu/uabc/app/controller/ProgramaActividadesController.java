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
import edu.uabc.app.service.IDepartamentosService;
import edu.uabc.app.service.IDocumentosConsultaService;
import edu.uabc.app.service.IMenuService;
import edu.uabc.app.service.IPermisoService;
import edu.uabc.app.service.ITiposDocumentosService;
import edu.uabc.app.service.IUsuariosConsultaService;
import edu.uabc.app.util.CrearMenu;

@Controller
@RequestMapping("/programa_actividades")
public class ProgramaActividadesController {

	@Autowired
	private IDepartamentosService serviceDepartamentos;
	
	@Autowired
	private IUsuariosConsultaService serviceUsuariosConsulta;
	
	@Autowired
	private IDocumentosConsultaService serviceDocumentosConsulta;
	
	@Autowired
	private ITiposDocumentosService serviceTiposDocumentos;
	
	@Autowired
	private IMenuService serviceMenu;
	
	@Autowired
	private IPermisoService servicePermiso;
	
	@GetMapping("/index")
	public String mostrarIndex(Authentication authentication, Model model) {
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
		
		//Se busca el tipo de documento
		TipoDocumento tipoDocumento = serviceTiposDocumentos.buscarPorNombre("Programa de Actividades");
		
		List<DocumentoConsulta> documentos = serviceDocumentosConsulta.buscarPorTipoDocumento(tipoDocumento);
		System.out.println("tipoDocumento" + tipoDocumento);
		System.out.println("documentos" + documentos);
		model.addAttribute("documentos", documentos);
		
		return "programa_actividades/programa_actividades";
	}
	
	@GetMapping("/gestion_calidad/{id}")
	public String mostrarProgramaActividadesGestionCalidad(@PathVariable("id") int id_departamento, Model model) {
		//Nombre del departamento
		Departamento departamento = serviceDepartamentos.buscarPorId(id_departamento);
		model.addAttribute("departamentos", departamento);

		
		return "programa_actividades/listProgramaActividadesDepartamento";
	}
	
	@GetMapping("/apoyo_informatico/{id}")
	public String mostrarProgramaActividadesApoyoInformatico(@PathVariable("id") int id_departamento, Model model) {
		//Nombre del departamento
		Departamento departamento = serviceDepartamentos.buscarPorId(id_departamento);
		model.addAttribute("departamentos", departamento);

		
		return "programa_actividades/listProgramaActividadesDepartamento";
	}
	
	@GetMapping("/auditoria_interna/{id}")
	public String mostrarProgramaActividadesAuditoriaInterna(@PathVariable("id") int id_departamento, Model model) {
		//Nombre del departamento
		Departamento departamento = serviceDepartamentos.buscarPorId(id_departamento);
		model.addAttribute("departamentos", departamento);

		
		return "programa_actividades/listProgramaAuditoriaInterna";
	}
	
	@GetMapping("/contabilidad/{id}")
	public String mostrarProgramaActividadesContabilidad(@PathVariable("id") int id_departamento, Model model) {
		//Nombre del departamento
		Departamento departamento = serviceDepartamentos.buscarPorId(id_departamento);
		model.addAttribute("departamentos", departamento);

		
		return "programa_actividades/listProgramaActividadesDepartamento";
	}
	
	@GetMapping("/control_patrimonial/{id}")
	public String mostrarProgramaActividadesControlPatrimonial(@PathVariable("id") int id_departamento, Model model) {
		//Nombre del departamento
		Departamento departamento = serviceDepartamentos.buscarPorId(id_departamento);
		model.addAttribute("departamentos", departamento);

		
		return "programa_actividades/listProgramaActividadesDepartamento";
	}
	
	@GetMapping("/finanzas/{id}")
	public String mostrarProgramaActividadesFinanzas(@PathVariable("id") int id_departamento, Model model) {
		//Nombre del departamento
		Departamento departamento = serviceDepartamentos.buscarPorId(id_departamento);
		model.addAttribute("departamentos", departamento);

		
		return "programa_actividades/listProgramaActividadesDepartamento";
	}
	
	@GetMapping("/presupuestos/{id}")
	public String mostrarProgramaActividadesPresupuestos(@PathVariable("id") int id_departamento, Model model) {
		//Nombre del departamento
		Departamento departamento = serviceDepartamentos.buscarPorId(id_departamento);
		model.addAttribute("departamentos", departamento);

		
		return "programa_actividades/listProgramaActividadesDepartamento";
	}
	
	@GetMapping("/tesoreria_campus/{id}")
	public String mostrarProgramaActividadesTesoreriaCampus(@PathVariable("id") int id_departamento, Model model) {
		//Nombre del departamento
		Departamento departamento = serviceDepartamentos.buscarPorId(id_departamento);
		model.addAttribute("departamentos", departamento);

		
		return "programa_actividades/listProgramaActividadesDepartamento";
	}
}
