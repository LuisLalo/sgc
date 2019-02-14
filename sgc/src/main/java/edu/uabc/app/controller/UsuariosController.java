package edu.uabc.app.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
import edu.uabc.app.model.Puesto;
import edu.uabc.app.model.Rol;
import edu.uabc.app.model.UsuarioActualizar;
import edu.uabc.app.model.UsuarioConsulta;
import edu.uabc.app.service.IDepartamentosService;
import edu.uabc.app.service.IEstatusUsuarioService;
import edu.uabc.app.service.IPuestosService;
import edu.uabc.app.service.IRolesService;
import edu.uabc.app.service.IUsuariosActualizarService;
import edu.uabc.app.service.IUsuariosConsultaService;
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
	
	@GetMapping("/index")
	public String mostrarIndex(Authentication authentication, Model model) throws Exception {
		List<UsuarioConsulta> lista = serviceUsuariosConsulta.buscarTodas();
		System.out.println("Lista de usuarios: " + lista);
		model.addAttribute("usuarios", lista);
		
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
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
		
		serviceUsuariosActualizar.insertar(usuario);
		
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
		
		return "usuarios/formUsuariosEditar";
	}
	
	@GetMapping(value="eliminar/{id}")
	public String eliminar(@PathVariable("id") int num_empleado, RedirectAttributes attributes) {
		
		serviceUsuariosActualizar.eliminar(num_empleado);
		attributes.addFlashAttribute("mensaje", "El usuario fue eliminado");
		return "redirect:/usuarios/index";
	}
}
