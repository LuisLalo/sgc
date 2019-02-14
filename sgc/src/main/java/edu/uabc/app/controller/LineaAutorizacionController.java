package edu.uabc.app.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.uabc.app.model.Departamento;
import edu.uabc.app.model.LineaAutorizacion;
import edu.uabc.app.model.Puesto;
import edu.uabc.app.model.Rol;
import edu.uabc.app.model.UsuarioConsulta;
import edu.uabc.app.model.UsuarioLineaAutorizacion;
import edu.uabc.app.service.IDepartamentosService;
import edu.uabc.app.service.ILineaAutorizacionService;
import edu.uabc.app.service.IPuestosService;
import edu.uabc.app.service.IRolesService;
import edu.uabc.app.service.IUsuariosConsultaService;

@Controller
@RequestMapping("/linea-autorizacion")
public class LineaAutorizacionController {

	@Autowired
	private IDepartamentosService serviceDepartamentos;
	
	@Autowired
	private ILineaAutorizacionService serviceLineaAutorizacion;
	
	@Autowired
	private IUsuariosConsultaService serviceUsuariosConsulta;
	
	@Autowired
	private IPuestosService servicePuestos;
	
	@Autowired
	private IRolesService serviceRoles;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model, Authentication authentication) {
		//Se agrega el nombre del empleado
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		List<Departamento> listaDepartamento = serviceDepartamentos.buscarTodas();
		model.addAttribute("departamentos", listaDepartamento);
		return "linea_autorizacion/listAutorizacion";
	}
	
	@GetMapping("/{id}")
	public String mostrarLineaAutorizacion(@PathVariable("id") int id_departamento, Model model, Authentication authentication) {
		//Se agrega el nombre del empleado
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		//Nombre del departamento
		Departamento departamento = serviceDepartamentos.buscarPorId(id_departamento);
		model.addAttribute("departamentos", departamento);
		
		//Listado de las personas de la línea de autorización
		List<LineaAutorizacion> listaLineaAutorizacion = serviceLineaAutorizacion.buscarPorDepartamento(id_departamento);
		System.out.println("Lista de usuarios: " + listaLineaAutorizacion);
		
		if(departamento.getNombre().equals("Seguimiento y Evaluación") && listaLineaAutorizacion.isEmpty()) {
			//Lista de las personas que pertenecen al departamento
			List<UsuarioConsulta> listaUsuarioConsulta = serviceUsuariosConsulta.buscarPorDepartamento(departamento);
			System.out.println("Lista de usuarios: " + listaUsuarioConsulta);
			
			// Se agrega al tesorero para poder completar la línea de autorización
			Puesto puestoTesorero = servicePuestos.buscarPorPuesto("Tesorero");
			UsuarioConsulta usuarioTesorero = serviceUsuariosConsulta.buscarPorPuesto(puestoTesorero);
			usuarioTesorero.setDepartamento(departamento);
			listaUsuarioConsulta.add(usuarioTesorero);
			
			model.addAttribute("usuarios", listaUsuarioConsulta);
			
			return "linea_autorizacion/formLineaAutorizacion";
		}
		else if (departamento.getNombre().equals("Seguimiento y Evaluación") && listaLineaAutorizacion.isEmpty()==false) {
			List<UsuarioLineaAutorizacion> listaUsuarioLineaAutorizacion = new LinkedList<UsuarioLineaAutorizacion>();
			int cont = 0;
			while(cont<listaLineaAutorizacion.size()) {
				int num_empleado = listaLineaAutorizacion.get(cont).getNumEmpleado();
				UsuarioConsulta usuarioConsulta = serviceUsuariosConsulta.buscarPorId(num_empleado);
				UsuarioLineaAutorizacion usuarioLineaAutorizacion = new UsuarioLineaAutorizacion();
				usuarioLineaAutorizacion.setNumEmpleado(num_empleado);
				usuarioLineaAutorizacion.setNombres(usuarioConsulta.getNombres());
				usuarioLineaAutorizacion.setApellidos(usuarioConsulta.getApellidos());
				usuarioLineaAutorizacion.setPuesto(usuarioConsulta.getPuesto().getDescripcion());
				usuarioLineaAutorizacion.setDepartamento(departamento.getRuta());
				usuarioLineaAutorizacion.setNivel(listaLineaAutorizacion.get(cont).getNivel());
				listaUsuarioLineaAutorizacion.add(usuarioLineaAutorizacion);
				System.out.println("Listado: " + cont + ": " + listaLineaAutorizacion);
				cont++;
			}
			
			//Lista de las personas que pertenecen al departamento
			List<UsuarioConsulta> listaUsuarioConsulta = serviceUsuariosConsulta.buscarPorDepartamento(departamento);
			System.out.println("Lista de usuarios: " + listaUsuarioConsulta);
			
			// Se agrega al tesorero para poder completar la línea de autorización
			Puesto puestoTesorero = servicePuestos.buscarPorPuesto("Tesorero");
			UsuarioConsulta usuarioTesorero = serviceUsuariosConsulta.buscarPorPuesto(puestoTesorero);
			usuarioTesorero.setDepartamento(departamento);
			listaUsuarioConsulta.add(usuarioTesorero);
			System.out.println("Niveles: " + listaUsuarioLineaAutorizacion);
			model.addAttribute("niveles", listaUsuarioLineaAutorizacion);
			model.addAttribute("usuarios", listaUsuarioConsulta);
			
			return "linea_autorizacion/formLineaAutorizacion";
		}
		else if(listaLineaAutorizacion.isEmpty()) {
			//Lista de las personas que pertenecen al departamento
			List<UsuarioConsulta> listaUsuarioConsulta = serviceUsuariosConsulta.buscarPorDepartamento(departamento);
			System.out.println("Lista de usuarios: " + listaUsuarioConsulta);
			
			Puesto puestoContOJefePresFin;
			
			//Se identifica si el departamento es parte de la Contaduróa o de la Unidad de Presupuestos y Finanzas
			if((id_departamento==serviceDepartamentos.buscarPorNombre("Apoyo Informático").getId_departamento()) ||
				(id_departamento==serviceDepartamentos.buscarPorNombre("Contabilidad").getId_departamento()) ||
				(id_departamento==serviceDepartamentos.buscarPorNombre("Control Patrimonial").getId_departamento())) {
				//Los departamentos pertenecen a la contaduría
				
				// Se agrega al contador para poder completar la línea de autorización
				puestoContOJefePresFin = servicePuestos.buscarPorPuesto("Contador");
				UsuarioConsulta usuarioContOJefePresFin = serviceUsuariosConsulta.buscarPorPuesto(puestoContOJefePresFin);
				usuarioContOJefePresFin.setDepartamento(departamento);
				listaUsuarioConsulta.add(usuarioContOJefePresFin);
			}
			else if((id_departamento==serviceDepartamentos.buscarPorNombre("Finanzas").getId_departamento()) ||
				(id_departamento==serviceDepartamentos.buscarPorNombre("Presupuestos").getId_departamento())) {
				// Se agrega a la jefa de la unidad de presupuestos y finanzas para poder completar la línea de autorización
				puestoContOJefePresFin = servicePuestos.buscarPorPuesto("Jefa de la Unidad de Presupuestos y Finanzas");
				UsuarioConsulta usuarioContOJefePresFin = serviceUsuariosConsulta.buscarPorPuesto(puestoContOJefePresFin);
				usuarioContOJefePresFin.setDepartamento(departamento);
				listaUsuarioConsulta.add(usuarioContOJefePresFin);
			}
			
			// Se agrega al administrador del SGC
			Rol rolSGC = serviceRoles.buscarPorNombre("SGC");
			UsuarioConsulta usuarioSGC = serviceUsuariosConsulta.buscarPorRol(rolSGC);
			usuarioSGC.setDepartamento(departamento);
			listaUsuarioConsulta.add(usuarioSGC);
			
			// Se agrega al tesorero para poder completar la línea de autorización
			Puesto puestoTesorero = servicePuestos.buscarPorPuesto("Tesorero");
			UsuarioConsulta usuarioTesorero = serviceUsuariosConsulta.buscarPorPuesto(puestoTesorero);
			usuarioTesorero.setDepartamento(departamento);
			listaUsuarioConsulta.add(usuarioTesorero);
			
			model.addAttribute("usuarios", listaUsuarioConsulta);
			
			System.out.println("No hay nada en la lista");
			return "linea_autorizacion/formLineaAutorizacion";
		}
		else {
			int cont = 0;
			List<UsuarioConsulta> listaUsuarioConsulta = serviceUsuariosConsulta.buscarPorDepartamento(departamento);
			List<UsuarioLineaAutorizacion> listaUsuarioLineaAutorizacion = new LinkedList<UsuarioLineaAutorizacion>();
			while(cont<listaLineaAutorizacion.size()) {
				int num_empleado = listaLineaAutorizacion.get(cont).getNumEmpleado();
				UsuarioConsulta usuarioConsulta = serviceUsuariosConsulta.buscarPorId(num_empleado);
				UsuarioLineaAutorizacion usuarioLineaAutorizacion = new UsuarioLineaAutorizacion();
				usuarioLineaAutorizacion.setNumEmpleado(num_empleado);
				usuarioLineaAutorizacion.setNombres(usuarioConsulta.getNombres());
				usuarioLineaAutorizacion.setApellidos(usuarioConsulta.getApellidos());
				usuarioLineaAutorizacion.setPuesto(usuarioConsulta.getPuesto().getDescripcion());
				usuarioLineaAutorizacion.setDepartamento(departamento.getRuta());
				usuarioLineaAutorizacion.setNivel(listaLineaAutorizacion.get(cont).getNivel());
				listaUsuarioLineaAutorizacion.add(usuarioLineaAutorizacion);
				System.out.println("Listado: " + cont + ": " + listaLineaAutorizacion);
				cont++;
			}
			
			Puesto puestoContOJefePresFin;
			
			//Se identifica si el departamento es parte de la Contaduróa o de la Unidad de Presupuestos y Finanzas
			if((id_departamento==serviceDepartamentos.buscarPorNombre("Apoyo Informático").getId_departamento()) ||
				(id_departamento==serviceDepartamentos.buscarPorNombre("Contabilidad").getId_departamento()) ||
				(id_departamento==serviceDepartamentos.buscarPorNombre("Control Patrimonial").getId_departamento())) {
				//Los departamentos pertenecen a la contaduría
				
				// Se agrega al contador para poder completar la línea de autorización
				puestoContOJefePresFin = servicePuestos.buscarPorPuesto("Contador");
				UsuarioConsulta usuarioContOJefePresFin = serviceUsuariosConsulta.buscarPorPuesto(puestoContOJefePresFin);
				usuarioContOJefePresFin.setDepartamento(departamento);
				listaUsuarioConsulta.add(usuarioContOJefePresFin);
			}
			else if((id_departamento==serviceDepartamentos.buscarPorNombre("Finanzas").getId_departamento()) ||
				(id_departamento==serviceDepartamentos.buscarPorNombre("Presupuestos").getId_departamento())) {
				// Se agrega a la jefa de la unidad de presupuestos y finanzas para poder completar la línea de autorización
				puestoContOJefePresFin = servicePuestos.buscarPorPuesto("Jefa de la Unidad de Presupuestos y Finanzas");
				UsuarioConsulta usuarioContOJefePresFin = serviceUsuariosConsulta.buscarPorPuesto(puestoContOJefePresFin);
				usuarioContOJefePresFin.setDepartamento(departamento);
				listaUsuarioConsulta.add(usuarioContOJefePresFin);
			}
			
			// Se agrega al administrador del SGC
			Rol rolSGC = serviceRoles.buscarPorNombre("SGC");
			UsuarioConsulta usuarioSGC = serviceUsuariosConsulta.buscarPorRol(rolSGC);
			usuarioSGC.setDepartamento(departamento);
			listaUsuarioConsulta.add(usuarioSGC);
			
			// Se agrega al tesorero para poder completar la línea de autorización
			Puesto puestoTesorero = servicePuestos.buscarPorPuesto("Tesorero");
			UsuarioConsulta usuarioTesorero = serviceUsuariosConsulta.buscarPorPuesto(puestoTesorero);
			usuarioTesorero.setDepartamento(departamento);
			listaUsuarioConsulta.add(usuarioTesorero);
			
			model.addAttribute("niveles", listaUsuarioLineaAutorizacion);
			model.addAttribute("usuarios", listaUsuarioConsulta);
			System.out.println("Lista de usuarios: " + listaUsuarioLineaAutorizacion);
			System.out.println("Listado: " + listaLineaAutorizacion);
			System.out.println("Listado: " + listaUsuarioConsulta);
			return "linea_autorizacion/formLineaAutorizacion";
		}
	}
	
	@GetMapping("/apoyo_informatico/{id}")
	public String mostrarLineaAutorizacionApoyoInformatico(@PathVariable("id") int id_departamento, Model model, Authentication authentication) {
		//Se agrega el nombre del empleado
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		//Nombre del departamento
		Departamento departamento = serviceDepartamentos.buscarPorId(id_departamento);
		model.addAttribute("departamentos", departamento);
		
		//Listado de las personas de la línea de autorización
		List<LineaAutorizacion> listaLineaAutorizacion = serviceLineaAutorizacion.buscarPorDepartamento(id_departamento);
		System.out.println("Lista de usuarios: " + listaLineaAutorizacion);
		
		if(listaLineaAutorizacion.isEmpty()) {
			//Lista de las personas que pertenecen al departamento
			List<UsuarioConsulta> listaUsuarioConsulta = serviceUsuariosConsulta.buscarPorDepartamento(departamento);
			System.out.println("Lista de usuarios: " + listaUsuarioConsulta);
			model.addAttribute("usuarios", listaUsuarioConsulta);
			
			System.out.println("No hay nada en la lista");
			return "linea_autorizacion/formLineaAutorizacion";
		}
		else {
			int cont = 0;
			List<UsuarioConsulta> listaUsuarioConsulta = serviceUsuariosConsulta.buscarPorDepartamento(departamento);
			List<UsuarioLineaAutorizacion> listaUsuarioLineaAutorizacion = new LinkedList<UsuarioLineaAutorizacion>();
			while(cont<listaLineaAutorizacion.size()) {
				int num_empleado = listaLineaAutorizacion.get(cont).getNumEmpleado();
				UsuarioConsulta usuarioConsulta = serviceUsuariosConsulta.buscarPorId(num_empleado);
				UsuarioLineaAutorizacion usuarioLineaAutorizacion = new UsuarioLineaAutorizacion();
				usuarioLineaAutorizacion.setNumEmpleado(num_empleado);
				usuarioLineaAutorizacion.setNombres(usuarioConsulta.getNombres());
				usuarioLineaAutorizacion.setApellidos(usuarioConsulta.getApellidos());
				usuarioLineaAutorizacion.setPuesto(usuarioConsulta.getPuesto().getDescripcion());
				usuarioLineaAutorizacion.setDepartamento(departamento.getRuta());
				usuarioLineaAutorizacion.setNivel(listaLineaAutorizacion.get(cont).getNivel());
				listaUsuarioLineaAutorizacion.add(usuarioLineaAutorizacion);
				System.out.println("Listado: " + cont + ": " + listaLineaAutorizacion);
				cont++;
			}
			
			// Se agrega al contador para poder completar la línea de autorización
			Puesto puestoContador = servicePuestos.buscarPorPuesto("Contador");
			UsuarioConsulta usuarioContador = serviceUsuariosConsulta.buscarPorPuesto(puestoContador);
			usuarioContador.setDepartamento(departamento);
			listaUsuarioConsulta.add(usuarioContador);
			
			// Se agrega al administrador del SGC
			Rol rolSGC = serviceRoles.buscarPorNombre("SGC");
			UsuarioConsulta usuarioSGC = serviceUsuariosConsulta.buscarPorRol(rolSGC);
			usuarioSGC.setDepartamento(departamento);
			listaUsuarioConsulta.add(usuarioSGC);
			
			// Se agrega al tesorero para poder completar la línea de autorización
			Puesto puestoTesorero = servicePuestos.buscarPorPuesto("Tesorero");
			UsuarioConsulta usuarioTesorero = serviceUsuariosConsulta.buscarPorPuesto(puestoTesorero);
			usuarioTesorero.setDepartamento(departamento);
			listaUsuarioConsulta.add(usuarioTesorero);
			
			model.addAttribute("niveles", listaUsuarioLineaAutorizacion);
			model.addAttribute("usuarios", listaUsuarioConsulta);
			System.out.println("Lista de usuarios: " + listaUsuarioLineaAutorizacion);
			System.out.println("Listado: " + listaLineaAutorizacion);
			System.out.println("Listado: " + listaUsuarioConsulta);
			return "linea_autorizacion/formLineaAutorizacion";
		}
	}
	
	@GetMapping("/auditoria_interna/{id}")
	public String mostrarLineaAutorizacionAuditoriaInterna(@PathVariable("id") int id_departamento, Model model, Authentication authentication) {
		//Se agrega el nombre del empleado
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		//Nombre del departamento
		Departamento departamento = serviceDepartamentos.buscarPorId(id_departamento);
		model.addAttribute("departamentos", departamento);
		
		//Listado de las personas de la línea de autorización
		List<LineaAutorizacion> listaLineaAutorizacion = serviceLineaAutorizacion.buscarPorDepartamento(id_departamento);
		System.out.println("Lista de usuarios: " + listaLineaAutorizacion);
		
		if(listaLineaAutorizacion.isEmpty()) {
			//Lista de las personas que pertenecen al departamento
			List<UsuarioConsulta> listaUsuarioConsulta = serviceUsuariosConsulta.buscarPorDepartamento(departamento);
			System.out.println("Lista de usuarios: " + listaUsuarioConsulta);
			model.addAttribute("usuarios", listaUsuarioConsulta);
			
			System.out.println("No hay nada en la lista");
			return "linea_autorizacion/formLineaAutorizacion";
		}
		else {
			int cont = 0;
			List<UsuarioConsulta> listaUsuarioConsulta = serviceUsuariosConsulta.buscarPorDepartamento(departamento);
			List<UsuarioLineaAutorizacion> listaUsuarioLineaAutorizacion = new LinkedList<UsuarioLineaAutorizacion>();
			while(cont<listaLineaAutorizacion.size()) {
				int num_empleado = listaLineaAutorizacion.get(cont).getNumEmpleado();
				UsuarioConsulta usuarioConsulta = serviceUsuariosConsulta.buscarPorId(num_empleado);
				UsuarioLineaAutorizacion usuarioLineaAutorizacion = new UsuarioLineaAutorizacion();
				usuarioLineaAutorizacion.setNumEmpleado(num_empleado);
				usuarioLineaAutorizacion.setNombres(usuarioConsulta.getNombres());
				usuarioLineaAutorizacion.setApellidos(usuarioConsulta.getApellidos());
				usuarioLineaAutorizacion.setPuesto(usuarioConsulta.getPuesto().getDescripcion());
				usuarioLineaAutorizacion.setDepartamento(departamento.getRuta());
				usuarioLineaAutorizacion.setNivel(listaLineaAutorizacion.get(cont).getNivel());
				listaUsuarioLineaAutorizacion.add(usuarioLineaAutorizacion);
				System.out.println("Listado: " + cont + ": " + listaLineaAutorizacion);
				cont++;
			}
			
			// Se agrega al contador para poder completar la línea de autorización
			Puesto puestoContador = servicePuestos.buscarPorPuesto("Contador");
			UsuarioConsulta usuarioContador = serviceUsuariosConsulta.buscarPorPuesto(puestoContador);
			usuarioContador.setDepartamento(departamento);
			listaUsuarioConsulta.add(usuarioContador);
			
			// Se agrega al administrador del SGC
			Rol rolSGC = serviceRoles.buscarPorNombre("SGC");
			UsuarioConsulta usuarioSGC = serviceUsuariosConsulta.buscarPorRol(rolSGC);
			usuarioSGC.setDepartamento(departamento);
			listaUsuarioConsulta.add(usuarioSGC);
			
			// Se agrega al tesorero para poder completar la línea de autorización
			Puesto puestoTesorero = servicePuestos.buscarPorPuesto("Tesorero");
			UsuarioConsulta usuarioTesorero = serviceUsuariosConsulta.buscarPorPuesto(puestoTesorero);
			usuarioTesorero.setDepartamento(departamento);
			listaUsuarioConsulta.add(usuarioTesorero);
			
			model.addAttribute("niveles", listaUsuarioLineaAutorizacion);
			model.addAttribute("usuarios", listaUsuarioConsulta);
			System.out.println("Lista de usuarios: " + listaUsuarioLineaAutorizacion);
			System.out.println("Listado: " + listaLineaAutorizacion);
			System.out.println("Listado: " + listaUsuarioConsulta);
			return "linea_autorizacion/formLineaAutorizacion";
		}
	}
	
	@GetMapping("/contabilidad/{id}")
	public String mostrarLineaAutorizacionContabilidad(@PathVariable("id") int id_departamento, Model model, Authentication authentication) {
		//Se agrega el nombre del empleado
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		//Nombre del departamento
		Departamento departamento = serviceDepartamentos.buscarPorId(id_departamento);
		model.addAttribute("departamentos", departamento);
		
		//Listado de las personas de la línea de autorización
		List<LineaAutorizacion> listaLineaAutorizacion = serviceLineaAutorizacion.buscarPorDepartamento(id_departamento);
		System.out.println("Lista de usuarios: " + listaLineaAutorizacion);
		
		if(listaLineaAutorizacion.isEmpty()) {
			//Lista de las personas que pertenecen al departamento
			List<UsuarioConsulta> listaUsuarioConsulta = serviceUsuariosConsulta.buscarPorDepartamento(departamento);
			System.out.println("Lista de usuarios: " + listaUsuarioConsulta);
			model.addAttribute("usuarios", listaUsuarioConsulta);
			
			System.out.println("No hay nada en la lista");
			return "linea_autorizacion/formLineaAutorizacion";
		}
		else {
			int cont = 0;
			List<UsuarioConsulta> listaUsuarioConsulta = serviceUsuariosConsulta.buscarPorDepartamento(departamento);
			List<UsuarioLineaAutorizacion> listaUsuarioLineaAutorizacion = new LinkedList<UsuarioLineaAutorizacion>();
			while(cont<listaLineaAutorizacion.size()) {
				int num_empleado = listaLineaAutorizacion.get(cont).getNumEmpleado();
				UsuarioConsulta usuarioConsulta = serviceUsuariosConsulta.buscarPorId(num_empleado);
				UsuarioLineaAutorizacion usuarioLineaAutorizacion = new UsuarioLineaAutorizacion();
				usuarioLineaAutorizacion.setNumEmpleado(num_empleado);
				usuarioLineaAutorizacion.setNombres(usuarioConsulta.getNombres());
				usuarioLineaAutorizacion.setApellidos(usuarioConsulta.getApellidos());
				usuarioLineaAutorizacion.setPuesto(usuarioConsulta.getPuesto().getDescripcion());
				usuarioLineaAutorizacion.setDepartamento(departamento.getRuta());
				usuarioLineaAutorizacion.setNivel(listaLineaAutorizacion.get(cont).getNivel());
				listaUsuarioLineaAutorizacion.add(usuarioLineaAutorizacion);
				System.out.println("Listado: " + cont + ": " + listaLineaAutorizacion);
				cont++;
			}
			
			// Se agrega al contador para poder completar la línea de autorización
			Puesto puestoContador = servicePuestos.buscarPorPuesto("Contador");
			UsuarioConsulta usuarioContador = serviceUsuariosConsulta.buscarPorPuesto(puestoContador);
			usuarioContador.setDepartamento(departamento);
			listaUsuarioConsulta.add(usuarioContador);
			
			// Se agrega al administrador del SGC
			Rol rolSGC = serviceRoles.buscarPorNombre("SGC");
			UsuarioConsulta usuarioSGC = serviceUsuariosConsulta.buscarPorRol(rolSGC);
			usuarioSGC.setDepartamento(departamento);
			listaUsuarioConsulta.add(usuarioSGC);
			
			// Se agrega al tesorero para poder completar la línea de autorización
			Puesto puestoTesorero = servicePuestos.buscarPorPuesto("Tesorero");
			UsuarioConsulta usuarioTesorero = serviceUsuariosConsulta.buscarPorPuesto(puestoTesorero);
			usuarioTesorero.setDepartamento(departamento);
			listaUsuarioConsulta.add(usuarioTesorero);
			
			model.addAttribute("niveles", listaUsuarioLineaAutorizacion);
			model.addAttribute("usuarios", listaUsuarioConsulta);
			System.out.println("Lista de usuarios: " + listaUsuarioLineaAutorizacion);
			System.out.println("Listado: " + listaLineaAutorizacion);
			System.out.println("Listado: " + listaUsuarioConsulta);
			return "linea_autorizacion/formLineaAutorizacion";
		}
	}
	
	@GetMapping("/control_patrimonial/{id}")
	public String mostrarLineaAutorizacionControlPatrimonial(@PathVariable("id") int id_departamento, Model model, Authentication authentication) {
		//Se agrega el nombre del empleado
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		//Nombre del departamento
		Departamento departamento = serviceDepartamentos.buscarPorId(id_departamento);
		model.addAttribute("departamentos", departamento);
		
		//Listado de las personas de la línea de autorización
		List<LineaAutorizacion> listaLineaAutorizacion = serviceLineaAutorizacion.buscarPorDepartamento(id_departamento);
		System.out.println("Lista de usuarios: " + listaLineaAutorizacion);
		
		if(listaLineaAutorizacion.isEmpty()) {
			//Lista de las personas que pertenecen al departamento
			List<UsuarioConsulta> listaUsuarioConsulta = serviceUsuariosConsulta.buscarPorDepartamento(departamento);
			System.out.println("Lista de usuarios: " + listaUsuarioConsulta);
			model.addAttribute("usuarios", listaUsuarioConsulta);
			
			System.out.println("No hay nada en la lista");
			return "linea_autorizacion/formLineaAutorizacion";
		}
		else {
			int cont = 0;
			List<UsuarioConsulta> listaUsuarioConsulta = serviceUsuariosConsulta.buscarPorDepartamento(departamento);
			List<UsuarioLineaAutorizacion> listaUsuarioLineaAutorizacion = new LinkedList<UsuarioLineaAutorizacion>();
			while(cont<listaLineaAutorizacion.size()) {
				int num_empleado = listaLineaAutorizacion.get(cont).getNumEmpleado();
				UsuarioConsulta usuarioConsulta = serviceUsuariosConsulta.buscarPorId(num_empleado);
				UsuarioLineaAutorizacion usuarioLineaAutorizacion = new UsuarioLineaAutorizacion();
				usuarioLineaAutorizacion.setNumEmpleado(num_empleado);
				usuarioLineaAutorizacion.setNombres(usuarioConsulta.getNombres());
				usuarioLineaAutorizacion.setApellidos(usuarioConsulta.getApellidos());
				usuarioLineaAutorizacion.setPuesto(usuarioConsulta.getPuesto().getDescripcion());
				usuarioLineaAutorizacion.setDepartamento(departamento.getRuta());
				usuarioLineaAutorizacion.setNivel(listaLineaAutorizacion.get(cont).getNivel());
				listaUsuarioLineaAutorizacion.add(usuarioLineaAutorizacion);
				System.out.println("Listado: " + cont + ": " + listaLineaAutorizacion);
				cont++;
			}
			
			// Se agrega al contador para poder completar la línea de autorización
			Puesto puestoContador = servicePuestos.buscarPorPuesto("Contador");
			UsuarioConsulta usuarioContador = serviceUsuariosConsulta.buscarPorPuesto(puestoContador);
			usuarioContador.setDepartamento(departamento);
			listaUsuarioConsulta.add(usuarioContador);
			
			// Se agrega al administrador del SGC
			Rol rolSGC = serviceRoles.buscarPorNombre("SGC");
			UsuarioConsulta usuarioSGC = serviceUsuariosConsulta.buscarPorRol(rolSGC);
			usuarioSGC.setDepartamento(departamento);
			listaUsuarioConsulta.add(usuarioSGC);
			
			// Se agrega al tesorero para poder completar la línea de autorización
			Puesto puestoTesorero = servicePuestos.buscarPorPuesto("Tesorero");
			UsuarioConsulta usuarioTesorero = serviceUsuariosConsulta.buscarPorPuesto(puestoTesorero);
			usuarioTesorero.setDepartamento(departamento);
			listaUsuarioConsulta.add(usuarioTesorero);
			
			model.addAttribute("niveles", listaUsuarioLineaAutorizacion);
			model.addAttribute("usuarios", listaUsuarioConsulta);
			System.out.println("Lista de usuarios: " + listaUsuarioLineaAutorizacion);
			System.out.println("Listado: " + listaLineaAutorizacion);
			System.out.println("Listado: " + listaUsuarioConsulta);
			return "linea_autorizacion/formLineaAutorizacion";
		}
	}
	
	@GetMapping("/finanzas/{id}")
	public String mostrarLineaAutorizacionFinanzas(@PathVariable("id") int id_departamento, Model model, Authentication authentication) {
		//Se agrega el nombre del empleado
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		//Nombre del departamento
		Departamento departamento = serviceDepartamentos.buscarPorId(id_departamento);
		model.addAttribute("departamentos", departamento);
		
		//Listado de las personas de la línea de autorización
		List<LineaAutorizacion> listaLineaAutorizacion = serviceLineaAutorizacion.buscarPorDepartamento(id_departamento);
		System.out.println("Lista de usuarios: " + listaLineaAutorizacion);
		
		if(listaLineaAutorizacion.isEmpty()) {
			//Lista de las personas que pertenecen al departamento
			List<UsuarioConsulta> listaUsuarioConsulta = serviceUsuariosConsulta.buscarPorDepartamento(departamento);
			System.out.println("Lista de usuarios: " + listaUsuarioConsulta);
			model.addAttribute("usuarios", listaUsuarioConsulta);
			
			System.out.println("No hay nada en la lista");
			return "linea_autorizacion/formLineaAutorizacion";
		}
		else {
			int cont = 0;
			List<UsuarioConsulta> listaUsuarioConsulta = serviceUsuariosConsulta.buscarPorDepartamento(departamento);
			List<UsuarioLineaAutorizacion> listaUsuarioLineaAutorizacion = new LinkedList<UsuarioLineaAutorizacion>();
			while(cont<listaLineaAutorizacion.size()) {
				int num_empleado = listaLineaAutorizacion.get(cont).getNumEmpleado();
				UsuarioConsulta usuarioConsulta = serviceUsuariosConsulta.buscarPorId(num_empleado);
				UsuarioLineaAutorizacion usuarioLineaAutorizacion = new UsuarioLineaAutorizacion();
				usuarioLineaAutorizacion.setNumEmpleado(num_empleado);
				usuarioLineaAutorizacion.setNombres(usuarioConsulta.getNombres());
				usuarioLineaAutorizacion.setApellidos(usuarioConsulta.getApellidos());
				usuarioLineaAutorizacion.setPuesto(usuarioConsulta.getPuesto().getDescripcion());
				usuarioLineaAutorizacion.setDepartamento(departamento.getRuta());
				usuarioLineaAutorizacion.setNivel(listaLineaAutorizacion.get(cont).getNivel());
				listaUsuarioLineaAutorizacion.add(usuarioLineaAutorizacion);
				System.out.println("Listado: " + cont + ": " + listaLineaAutorizacion);
				cont++;
			}
			
			// Se agrega al contador para poder completar la línea de autorización
			Puesto puestoContador = servicePuestos.buscarPorPuesto("Jefa de la Unidad de Presupuestos y Finanzas");
			UsuarioConsulta usuarioContador = serviceUsuariosConsulta.buscarPorPuesto(puestoContador);
			usuarioContador.setDepartamento(departamento);
			listaUsuarioConsulta.add(usuarioContador);
			
			// Se agrega al administrador del SGC
			Rol rolSGC = serviceRoles.buscarPorNombre("SGC");
			UsuarioConsulta usuarioSGC = serviceUsuariosConsulta.buscarPorRol(rolSGC);
			usuarioSGC.setDepartamento(departamento);
			listaUsuarioConsulta.add(usuarioSGC);
			
			// Se agrega al tesorero para poder completar la línea de autorización
			Puesto puestoTesorero = servicePuestos.buscarPorPuesto("Tesorero");
			UsuarioConsulta usuarioTesorero = serviceUsuariosConsulta.buscarPorPuesto(puestoTesorero);
			usuarioTesorero.setDepartamento(departamento);
			listaUsuarioConsulta.add(usuarioTesorero);
			
			model.addAttribute("niveles", listaUsuarioLineaAutorizacion);
			model.addAttribute("usuarios", listaUsuarioConsulta);
			System.out.println("Lista de usuarios: " + listaUsuarioLineaAutorizacion);
			System.out.println("Listado: " + listaLineaAutorizacion);
			System.out.println("Listado: " + listaUsuarioConsulta);
			return "linea_autorizacion/formLineaAutorizacion";
		}
	}
	
	@GetMapping("/presupuestos/{id}")
	public String mostrarLineaAutorizacionPresupuestos(@PathVariable("id") int id_departamento, Model model, Authentication authentication) {
		//Se agrega el nombre del empleado
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		//Nombre del departamento
		Departamento departamento = serviceDepartamentos.buscarPorId(id_departamento);
		model.addAttribute("departamentos", departamento);
		
		//Listado de las personas de la línea de autorización
		List<LineaAutorizacion> listaLineaAutorizacion = serviceLineaAutorizacion.buscarPorDepartamento(id_departamento);
		System.out.println("Lista de usuarios: " + listaLineaAutorizacion);
		
		if(listaLineaAutorizacion.isEmpty()) {
			//Lista de las personas que pertenecen al departamento
			List<UsuarioConsulta> listaUsuarioConsulta = serviceUsuariosConsulta.buscarPorDepartamento(departamento);
			System.out.println("Lista de usuarios: " + listaUsuarioConsulta);
			model.addAttribute("usuarios", listaUsuarioConsulta);
			
			System.out.println("No hay nada en la lista");
			return "linea_autorizacion/formLineaAutorizacion";
		}
		else {
			int cont = 0;
			List<UsuarioConsulta> listaUsuarioConsulta = serviceUsuariosConsulta.buscarPorDepartamento(departamento);
			List<UsuarioLineaAutorizacion> listaUsuarioLineaAutorizacion = new LinkedList<UsuarioLineaAutorizacion>();
			while(cont<listaLineaAutorizacion.size()) {
				int num_empleado = listaLineaAutorizacion.get(cont).getNumEmpleado();
				UsuarioConsulta usuarioConsulta = serviceUsuariosConsulta.buscarPorId(num_empleado);
				UsuarioLineaAutorizacion usuarioLineaAutorizacion = new UsuarioLineaAutorizacion();
				usuarioLineaAutorizacion.setNumEmpleado(num_empleado);
				usuarioLineaAutorizacion.setNombres(usuarioConsulta.getNombres());
				usuarioLineaAutorizacion.setApellidos(usuarioConsulta.getApellidos());
				usuarioLineaAutorizacion.setPuesto(usuarioConsulta.getPuesto().getDescripcion());
				usuarioLineaAutorizacion.setDepartamento(departamento.getRuta());
				usuarioLineaAutorizacion.setNivel(listaLineaAutorizacion.get(cont).getNivel());
				listaUsuarioLineaAutorizacion.add(usuarioLineaAutorizacion);
				System.out.println("Listado: " + cont + ": " + listaLineaAutorizacion);
				cont++;
			}
			
			// Se agrega al contador para poder completar la línea de autorización
			Puesto puestoContador = servicePuestos.buscarPorPuesto("Jefa de la Unidad de Presupuestos y Finanzas");
			UsuarioConsulta usuarioContador = serviceUsuariosConsulta.buscarPorPuesto(puestoContador);
			usuarioContador.setDepartamento(departamento);
			listaUsuarioConsulta.add(usuarioContador);
			
			// Se agrega al administrador del SGC
			Rol rolSGC = serviceRoles.buscarPorNombre("SGC");
			UsuarioConsulta usuarioSGC = serviceUsuariosConsulta.buscarPorRol(rolSGC);
			usuarioSGC.setDepartamento(departamento);
			listaUsuarioConsulta.add(usuarioSGC);
			
			// Se agrega al tesorero para poder completar la línea de autorización
			Puesto puestoTesorero = servicePuestos.buscarPorPuesto("Tesorero");
			UsuarioConsulta usuarioTesorero = serviceUsuariosConsulta.buscarPorPuesto(puestoTesorero);
			usuarioTesorero.setDepartamento(departamento);
			listaUsuarioConsulta.add(usuarioTesorero);
			
			model.addAttribute("niveles", listaUsuarioLineaAutorizacion);
			model.addAttribute("usuarios", listaUsuarioConsulta);
			System.out.println("Lista de usuarios: " + listaUsuarioLineaAutorizacion);
			System.out.println("Listado: " + listaLineaAutorizacion);
			System.out.println("Listado: " + listaUsuarioConsulta);
			return "linea_autorizacion/formLineaAutorizacion";
		}
	}
	
	@GetMapping("/tesoreria_campus/{id}")
	public String mostrarLineaAutorizacionTesoreriaCampus(@PathVariable("id") int id_departamento, Model model, Authentication authentication) {
		//Se agrega el nombre del empleado
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		//Nombre del departamento
		Departamento departamento = serviceDepartamentos.buscarPorId(id_departamento);
		model.addAttribute("departamentos", departamento);
		
		//Listado de las personas de la línea de autorización
		List<LineaAutorizacion> listaLineaAutorizacion = serviceLineaAutorizacion.buscarPorDepartamento(id_departamento);
		System.out.println("Lista de usuarios: " + listaLineaAutorizacion);
		
		if(listaLineaAutorizacion.isEmpty()) {
			//Lista de las personas que pertenecen al departamento
			List<UsuarioConsulta> listaUsuarioConsulta = serviceUsuariosConsulta.buscarPorDepartamento(departamento);
			System.out.println("Lista de usuarios: " + listaUsuarioConsulta);
			model.addAttribute("usuarios", listaUsuarioConsulta);
			
			System.out.println("No hay nada en la lista");
			return "linea_autorizacion/formLineaAutorizacion";
		}
		else {
			int cont = 0;
			List<UsuarioConsulta> listaUsuarioConsulta = serviceUsuariosConsulta.buscarPorDepartamento(departamento);
			List<UsuarioLineaAutorizacion> listaUsuarioLineaAutorizacion = new LinkedList<UsuarioLineaAutorizacion>();
			while(cont<listaLineaAutorizacion.size()) {
				int num_empleado = listaLineaAutorizacion.get(cont).getNumEmpleado();
				UsuarioConsulta usuarioConsulta = serviceUsuariosConsulta.buscarPorId(num_empleado);
				UsuarioLineaAutorizacion usuarioLineaAutorizacion = new UsuarioLineaAutorizacion();
				usuarioLineaAutorizacion.setNumEmpleado(num_empleado);
				usuarioLineaAutorizacion.setNombres(usuarioConsulta.getNombres());
				usuarioLineaAutorizacion.setApellidos(usuarioConsulta.getApellidos());
				usuarioLineaAutorizacion.setPuesto(usuarioConsulta.getPuesto().getDescripcion());
				usuarioLineaAutorizacion.setDepartamento(departamento.getRuta());
				usuarioLineaAutorizacion.setNivel(listaLineaAutorizacion.get(cont).getNivel());
				listaUsuarioLineaAutorizacion.add(usuarioLineaAutorizacion);
				System.out.println("Listado: " + cont + ": " + listaLineaAutorizacion);
				cont++;
			}
			/*
			// Se agrega al contador para poder completar la línea de autorización
			Puesto puestoContador = servicePuestos.buscarPorPuesto("Contador");
			UsuarioConsulta usuarioContador = serviceUsuariosConsulta.buscarPorPuesto(puestoContador);
			usuarioContador.setDepartamento(departamento);
			listaUsuarioConsulta.add(usuarioContador);
			*/
			// Se agrega al administrador del SGC
			Rol rolSGC = serviceRoles.buscarPorNombre("SGC");
			UsuarioConsulta usuarioSGC = serviceUsuariosConsulta.buscarPorRol(rolSGC);
			usuarioSGC.setDepartamento(departamento);
			listaUsuarioConsulta.add(usuarioSGC);
			
			// Se agrega al tesorero para poder completar la línea de autorización
			Puesto puestoTesorero = servicePuestos.buscarPorPuesto("Tesorero");
			UsuarioConsulta usuarioTesorero = serviceUsuariosConsulta.buscarPorPuesto(puestoTesorero);
			usuarioTesorero.setDepartamento(departamento);
			listaUsuarioConsulta.add(usuarioTesorero);
			
			model.addAttribute("niveles", listaUsuarioLineaAutorizacion);
			model.addAttribute("usuarios", listaUsuarioConsulta);
			System.out.println("Lista de usuarios: " + listaUsuarioLineaAutorizacion);
			System.out.println("Listado: " + listaLineaAutorizacion);
			System.out.println("Listado: " + listaUsuarioConsulta);
			return "linea_autorizacion/formLineaAutorizacion";
		}
	}
	
	@GetMapping("/guardar/gestion_calidad/{id}")
	public String guardarGestionCalidad (@PathVariable("id") int num_empleado, LineaAutorizacion lineaAutorizacion, Model model, BindingResult result, RedirectAttributes attribute) {
		
		if (result.hasErrors()) {
			System.out.println("Existieron errores");
			
			return "linea_autorizacion/formLineaAutorizacion";
		}
		
		Departamento departamento = serviceDepartamentos.buscarPorNombre("Seguimiento y Evaluación");
		System.out.println(departamento.getId_departamento());
		UsuarioConsulta usuarioConsulta = serviceUsuariosConsulta.buscarPorId(num_empleado);
		lineaAutorizacion.setNumEmpleado(num_empleado);
		if(usuarioConsulta.getDepartamento().getId_departamento()!=departamento.getId_departamento()) {
			lineaAutorizacion.setDepartamento(departamento.getId_departamento());
			usuarioConsulta.setDepartamento(departamento);
		}
		else {
			lineaAutorizacion.setDepartamento(usuarioConsulta.getDepartamento().getId_departamento());
		}
		
		//Niveles de la linea de autorización
		List<LineaAutorizacion> listaLineaAutorizacion = serviceLineaAutorizacion.buscarPorDepartamento(usuarioConsulta.getDepartamento().getId_departamento());
		int cont = listaLineaAutorizacion.size()+1;
		
		lineaAutorizacion.setNivel(cont);
		
		System.out.println("Recibiendo objeto linea de autorizacion: " + lineaAutorizacion);
		
		serviceLineaAutorizacion.insertar(lineaAutorizacion);
		
		attribute.addFlashAttribute("mensaje", "El registro fue guardado");
		
		return "redirect:/linea-autorizacion/1";
	}
	
	@GetMapping("/guardar/apoyo_informatico/{id}")
	public String guardarApoyoInformatico (@PathVariable("id") int num_empleado, LineaAutorizacion lineaAutorizacion, Model model, BindingResult result, RedirectAttributes attribute) {
		
		if (result.hasErrors()) {
			System.out.println("Existieron errores");
			
			return "linea_autorizacion/formLineaAutorizacion";
		}
		
		Departamento departamento = serviceDepartamentos.buscarPorNombre("Apoyo Informático");
		System.out.println(departamento.getId_departamento());
		UsuarioConsulta usuarioConsulta = serviceUsuariosConsulta.buscarPorId(num_empleado);
		lineaAutorizacion.setNumEmpleado(num_empleado);
		if(usuarioConsulta.getDepartamento().getId_departamento()!=departamento.getId_departamento()) {
			lineaAutorizacion.setDepartamento(departamento.getId_departamento());
			usuarioConsulta.setDepartamento(departamento);
		}
		else {
			lineaAutorizacion.setDepartamento(usuarioConsulta.getDepartamento().getId_departamento());
		}
		
		//Niveles de la linea de autorización
		List<LineaAutorizacion> listaLineaAutorizacion = serviceLineaAutorizacion.buscarPorDepartamento(usuarioConsulta.getDepartamento().getId_departamento());
		int cont = listaLineaAutorizacion.size()+1;
		
		lineaAutorizacion.setNivel(cont);
		
		System.out.println("Recibiendo objeto linea de autorizacion: " + lineaAutorizacion);
		
		serviceLineaAutorizacion.insertar(lineaAutorizacion);
		
		attribute.addFlashAttribute("mensaje", "El registro fue guardado");
		
		return "redirect:/linea-autorizacion/2";
	}
	
	@GetMapping("/guardar/auditoria_interna/{id}")
	public String guardarAuditoriaInterna(@PathVariable("id") int num_empleado, LineaAutorizacion lineaAutorizacion, Model model, BindingResult result, RedirectAttributes attribute) {
		
		if (result.hasErrors()) {
			System.out.println("Existieron errores");
			
			return "linea_autorizacion/formLineaAutorizacion";
		}
		
		Departamento departamento = serviceDepartamentos.buscarPorNombre("Auditoría Interna");
		System.out.println(departamento.getId_departamento());
		UsuarioConsulta usuarioConsulta = serviceUsuariosConsulta.buscarPorId(num_empleado);
		lineaAutorizacion.setNumEmpleado(num_empleado);
		if(usuarioConsulta.getDepartamento().getId_departamento()!=departamento.getId_departamento()) {
			lineaAutorizacion.setDepartamento(departamento.getId_departamento());
			usuarioConsulta.setDepartamento(departamento);
		}
		else {
			lineaAutorizacion.setDepartamento(usuarioConsulta.getDepartamento().getId_departamento());
		}
		
		//Niveles de la linea de autorización
		List<LineaAutorizacion> listaLineaAutorizacion = serviceLineaAutorizacion.buscarPorDepartamento(usuarioConsulta.getDepartamento().getId_departamento());
		int cont = listaLineaAutorizacion.size()+1;
		
		lineaAutorizacion.setNivel(cont);
		
		System.out.println("Recibiendo objeto linea de autorizacion: " + lineaAutorizacion);
		
		serviceLineaAutorizacion.insertar(lineaAutorizacion);
		
		attribute.addFlashAttribute("mensaje", "El registro fue guardado");
		
		return "redirect:/linea-autorizacion/3";
	}
	
	@GetMapping("/guardar/contabilidad/{id}")
	public String guardarContabilidad(@PathVariable("id") int num_empleado, LineaAutorizacion lineaAutorizacion, Model model, BindingResult result, RedirectAttributes attribute) {
		
		if (result.hasErrors()) {
			System.out.println("Existieron errores");
			
			return "linea_autorizacion/formLineaAutorizacion";
		}
		
		Departamento departamento = serviceDepartamentos.buscarPorNombre("Contabilidad");
		System.out.println(departamento.getId_departamento());
		UsuarioConsulta usuarioConsulta = serviceUsuariosConsulta.buscarPorId(num_empleado);
		lineaAutorizacion.setNumEmpleado(num_empleado);
		if(usuarioConsulta.getDepartamento().getId_departamento()!=departamento.getId_departamento()) {
			lineaAutorizacion.setDepartamento(departamento.getId_departamento());
			usuarioConsulta.setDepartamento(departamento);
		}
		else {
			lineaAutorizacion.setDepartamento(usuarioConsulta.getDepartamento().getId_departamento());
		}
		
		//Niveles de la linea de autorización
		List<LineaAutorizacion> listaLineaAutorizacion = serviceLineaAutorizacion.buscarPorDepartamento(usuarioConsulta.getDepartamento().getId_departamento());
		int cont = listaLineaAutorizacion.size()+1;
		
		lineaAutorizacion.setNivel(cont);
		
		System.out.println("Recibiendo objeto linea de autorizacion: " + lineaAutorizacion);
		
		serviceLineaAutorizacion.insertar(lineaAutorizacion);
		
		attribute.addFlashAttribute("mensaje", "El registro fue guardado");
		
		return "redirect:/linea-autorizacion/4";
	}
	
	@GetMapping("/guardar/control_patrimonial/{id}")
	public String guardarControlPatrimonial(@PathVariable("id") int num_empleado, LineaAutorizacion lineaAutorizacion, Model model, BindingResult result, RedirectAttributes attribute) {
		
		if (result.hasErrors()) {
			System.out.println("Existieron errores");
			
			return "linea_autorizacion/formLineaAutorizacion";
		}
		
		Departamento departamento = serviceDepartamentos.buscarPorNombre("Control Patrimonial");
		System.out.println(departamento.getId_departamento());
		UsuarioConsulta usuarioConsulta = serviceUsuariosConsulta.buscarPorId(num_empleado);
		lineaAutorizacion.setNumEmpleado(num_empleado);
		if(usuarioConsulta.getDepartamento().getId_departamento()!=departamento.getId_departamento()) {
			lineaAutorizacion.setDepartamento(departamento.getId_departamento());
			usuarioConsulta.setDepartamento(departamento);
		}
		else {
			lineaAutorizacion.setDepartamento(usuarioConsulta.getDepartamento().getId_departamento());
		}
		
		//Niveles de la linea de autorización
		List<LineaAutorizacion> listaLineaAutorizacion = serviceLineaAutorizacion.buscarPorDepartamento(usuarioConsulta.getDepartamento().getId_departamento());
		int cont = listaLineaAutorizacion.size()+1;
		
		lineaAutorizacion.setNivel(cont);
		
		System.out.println("Recibiendo objeto linea de autorizacion: " + lineaAutorizacion);
		
		serviceLineaAutorizacion.insertar(lineaAutorizacion);
		
		attribute.addFlashAttribute("mensaje", "El registro fue guardado");
		
		return "redirect:/linea-autorizacion/5";
	}
	
	@GetMapping("/guardar/finanzas/{id}")
	public String guardarFinanzas (@PathVariable("id") int num_empleado, LineaAutorizacion lineaAutorizacion, Model model, BindingResult result, RedirectAttributes attribute) {
		
		if (result.hasErrors()) {
			System.out.println("Existieron errores");
			
			return "linea_autorizacion/formLineaAutorizacion";
		}
		
		Departamento departamento = serviceDepartamentos.buscarPorNombre("Finanzas");
		System.out.println(departamento.getId_departamento());
		UsuarioConsulta usuarioConsulta = serviceUsuariosConsulta.buscarPorId(num_empleado);
		lineaAutorizacion.setNumEmpleado(num_empleado);
		if(usuarioConsulta.getDepartamento().getId_departamento()!=departamento.getId_departamento()) {
			lineaAutorizacion.setDepartamento(departamento.getId_departamento());
			usuarioConsulta.setDepartamento(departamento);
		}
		else {
			lineaAutorizacion.setDepartamento(usuarioConsulta.getDepartamento().getId_departamento());
		}
		
		//Niveles de la linea de autorización
		List<LineaAutorizacion> listaLineaAutorizacion = serviceLineaAutorizacion.buscarPorDepartamento(usuarioConsulta.getDepartamento().getId_departamento());
		int cont = listaLineaAutorizacion.size()+1;
		
		lineaAutorizacion.setNivel(cont);
		
		System.out.println("Recibiendo objeto linea de autorizacion: " + lineaAutorizacion);
		
		serviceLineaAutorizacion.insertar(lineaAutorizacion);
		
		attribute.addFlashAttribute("mensaje", "El registro fue guardado");
		
		return "redirect:/linea-autorizacion/6";
	}
	
	@GetMapping("/guardar/presupuestos/{id}")
	public String guardarPresupuestos(@PathVariable("id") int num_empleado, LineaAutorizacion lineaAutorizacion, Model model, BindingResult result, RedirectAttributes attribute) {
		
		if (result.hasErrors()) {
			System.out.println("Existieron errores");
			
			return "linea_autorizacion/formLineaAutorizacion";
		}
		
		Departamento departamento = serviceDepartamentos.buscarPorNombre("Presupuestos");
		System.out.println(departamento.getId_departamento());
		UsuarioConsulta usuarioConsulta = serviceUsuariosConsulta.buscarPorId(num_empleado);
		lineaAutorizacion.setNumEmpleado(num_empleado);
		if(usuarioConsulta.getDepartamento().getId_departamento()!=departamento.getId_departamento()) {
			lineaAutorizacion.setDepartamento(departamento.getId_departamento());
			usuarioConsulta.setDepartamento(departamento);
		}
		else {
			lineaAutorizacion.setDepartamento(usuarioConsulta.getDepartamento().getId_departamento());
		}
		
		//Niveles de la linea de autorización
		List<LineaAutorizacion> listaLineaAutorizacion = serviceLineaAutorizacion.buscarPorDepartamento(usuarioConsulta.getDepartamento().getId_departamento());
		int cont = listaLineaAutorizacion.size()+1;
		
		lineaAutorizacion.setNivel(cont);
		
		System.out.println("Recibiendo objeto linea de autorizacion: " + lineaAutorizacion);
		
		serviceLineaAutorizacion.insertar(lineaAutorizacion);
		
		attribute.addFlashAttribute("mensaje", "El registro fue guardado");
		
		return "redirect:/linea-autorizacion/7";
	}
	
	@GetMapping("/guardar/tesoreria/{id}")
	public String guardarTesoreriaCampus(@PathVariable("id") int num_empleado, LineaAutorizacion lineaAutorizacion, Model model, BindingResult result, RedirectAttributes attribute) {
		
		if (result.hasErrors()) {
			System.out.println("Existieron errores");
			
			return "linea_autorizacion/formLineaAutorizacion";
		}
		
		Departamento departamento = serviceDepartamentos.buscarPorNombre("Tesoreria - Campus");
		System.out.println(departamento.getId_departamento());
		UsuarioConsulta usuarioConsulta = serviceUsuariosConsulta.buscarPorId(num_empleado);
		lineaAutorizacion.setNumEmpleado(num_empleado);
		if(usuarioConsulta.getDepartamento().getId_departamento()!=departamento.getId_departamento()) {
			lineaAutorizacion.setDepartamento(departamento.getId_departamento());
			usuarioConsulta.setDepartamento(departamento);
		}
		else {
			lineaAutorizacion.setDepartamento(usuarioConsulta.getDepartamento().getId_departamento());
		}
		
		//Niveles de la linea de autorización
		List<LineaAutorizacion> listaLineaAutorizacion = serviceLineaAutorizacion.buscarPorDepartamento(usuarioConsulta.getDepartamento().getId_departamento());
		int cont = listaLineaAutorizacion.size()+1;
		
		lineaAutorizacion.setNivel(cont);
		
		System.out.println("Recibiendo objeto linea de autorizacion: " + lineaAutorizacion);
		
		serviceLineaAutorizacion.insertar(lineaAutorizacion);
		
		attribute.addFlashAttribute("mensaje", "El registro fue guardado");
		
		return "redirect:/linea-autorizacion/8";
	}
	
	@GetMapping("/eliminar/apoyo_informatico/{id}")
	public String eliminarApoyoInformatico(@PathVariable ("id") int numEmpleado, LineaAutorizacion lineaAutorizacion, RedirectAttributes attributes) {
		int departamento = serviceDepartamentos.buscarPorNombre("Apoyo Informático").getId_departamento();
		serviceLineaAutorizacion.eliminarPorNumEmpleadoAndDepartamento(numEmpleado, departamento);
		attributes.addFlashAttribute("mensaje", "El usuario fue eliminado");
		return "redirect:/linea_autorizacion/2";
	}
}
