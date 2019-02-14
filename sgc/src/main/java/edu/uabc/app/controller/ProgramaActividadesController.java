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
import edu.uabc.app.model.UsuarioConsulta;
import edu.uabc.app.service.IDepartamentosService;
import edu.uabc.app.service.IUsuariosConsultaService;

@Controller
@RequestMapping("/programa_actividades")
public class ProgramaActividadesController {

	@Autowired
	private IDepartamentosService serviceDepartamentos;
	
	@Autowired
	private IUsuariosConsultaService serviceUsuariosConsulta;
	
	@GetMapping("/index")
	public String mostrarIndex(Authentication authentication, Model model) {
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		System.out.println("usuarioAuth: " + usuarioAuth);
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		List<Departamento> listaDepartamento = serviceDepartamentos.buscarTodas();
		model.addAttribute("departamentos", listaDepartamento);
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
