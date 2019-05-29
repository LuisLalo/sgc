package edu.uabc.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.uabc.app.model.CambiarContrasena;
import edu.uabc.app.model.DocumentoActualizar;
import edu.uabc.app.model.Menu;
import edu.uabc.app.model.Permiso;
import edu.uabc.app.model.UsuarioActualizar;
import edu.uabc.app.model.UsuarioConsulta;
import edu.uabc.app.service.IMenuService;
import edu.uabc.app.service.IPermisoService;
import edu.uabc.app.service.IUsuariosActualizarService;
import edu.uabc.app.service.IUsuariosConsultaService;
import edu.uabc.app.util.Contrasena;
import edu.uabc.app.util.CrearMenu;

@Controller
@RequestMapping("/perfil")
public class PerfilController {

	@Autowired
	private IUsuariosConsultaService serviceUsuariosConsulta;
	
	@Autowired
	private IUsuariosActualizarService serviceUsuariosActualizar;
	
	@Autowired
	private IMenuService serviceMenu;
	
	@Autowired
	private IPermisoService servicePermiso;
	
	@GetMapping("/index")
	public String mostrarIndex(Authentication authentication, Model model) {
		
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
		
		return "perfil/perfilUsuario";
	}
	
	@GetMapping("/cambiar_contrasena")
	public String cambiarContrasena(@ModelAttribute CambiarContrasena cambiarContrasena, Authentication authentication, Model model) {
		
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
		
		System.out.println(cambiarContrasena);
		
		return "perfil/cambiarContrasena";
	}
	
	@PostMapping("/confirmar_contrasena")
	public String confirmarContrasena(@ModelAttribute CambiarContrasena cambiarContrasena, Authentication authentication, Model model, BindingResult result, RedirectAttributes attribute) {
		
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

		System.out.println("Documento: " + cambiarContrasena);
		// Se identifica si hubo un error
		if (result.hasErrors()) {
			System.out.println(cambiarContrasena);
			System.out.println("Existieron errores");
			
			return "perfil/cambiarContrasena";
		}
		
		// Se identifica si las contrase�as son iguales
		if(cambiarContrasena.getContrasenaActual().equals(cambiarContrasena.getConfirmarContrasena())) {
			// Se encripta la contrase�a para compararla con la que est� guardada en la BD
			Contrasena contrasena = new Contrasena();
			String contrasena_hash = contrasena.encriptarContrasena(cambiarContrasena.getContrasenaActual());
			System.out.println("Contrase�a encriptada ingresada: " + contrasena_hash);
			
			// Se busca la contrase�a guardada en la base de datos que corresponde al usuario
			int numEmpleado = usuarioAuth.getNum_empleado();
			UsuarioConsulta usuarioConsulta = new UsuarioConsulta();
			usuarioConsulta = serviceUsuariosConsulta.buscarPorId(numEmpleado);
			System.out.println("Contrase�a guardada en BD: " + usuarioConsulta.getContrasena());
			
			// Se identifica si la contrase�a capturada por el usuario es la misma a la que est� guardada en la base de datos
			if(BCrypt.checkpw(cambiarContrasena.getContrasenaActual(), usuarioConsulta.getContrasena())) {
				// La contrase�a es la misma
				return "redirect:/perfil/nueva_contrasena";
			}
			else {
				// La contrase�a es diferente
				attribute.addFlashAttribute("mensaje", "La contrase�a ingresada no es la misma a la registrada dentro del Sistema. Favor de verificarla.");
				return "redirect:/perfil/cambiar_contrasena";
			}
			
		}
		else {
			// Las contrase�as capturadas no coinciden
			attribute.addFlashAttribute("mensaje", "Las contrase�as no coinciden. Favor de volver a capturarlas.");
			return "redirect:/perfil/cambiar_contrasena";
		}
	}
	
	@GetMapping("/nueva_contrasena")
	public String nuevaContrasena(@ModelAttribute CambiarContrasena cambiarContrasena, Authentication authentication, Model model) {
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
		
		return "perfil/nuevaContrasena";
	}
	
	@PostMapping("/actualizar_contrasena")
	public String actualizarContrasena(@ModelAttribute CambiarContrasena cambiarContrasena, Authentication authentication, BindingResult result, RedirectAttributes attribute, Model model) {
		
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
		
		// Se identifica si hubo un error
		if (result.hasErrors()) {
			System.out.println("Existieron errores");
			System.out.println(cambiarContrasena);
			return "perfil/nueva_contrasena";
		}
		
		// Se busca la informaci�n del usuario
		int numEmpleado = usuarioAuth.getNum_empleado();
		UsuarioConsulta usuarioConsulta = new UsuarioConsulta();
		usuarioConsulta = serviceUsuariosConsulta.buscarPorId(numEmpleado);
		
		// Encriptaci�n del password de los usuarios
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(cambiarContrasena.getContrasenaActual());
		usuarioConsulta.setContrasena(hashedPassword);
		
		
		// Se actualiza la contrase�a dentro de la base de datos 
		serviceUsuariosConsulta.insertar(usuarioConsulta);
		
		attribute.addFlashAttribute("mensaje", "La contrase�a fue acturalizada dentro del Sistema.");
		return "redirect:/perfil/index";
	}
}
