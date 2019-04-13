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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String cambiarContrasena(@ModelAttribute UsuarioActualizar usuarioActualizar, Authentication authentication, Model model) {
		
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		System.out.println("usuarioAuth: " + usuarioAuth);
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		System.out.println(usuarioActualizar);
		
		return "perfil/cambiarContrasena";
	}
	
	@PostMapping("/confirmar_contrasena")
	public String confirmarContrasena(@ModelAttribute UsuarioActualizar usuarioActualizar, Authentication authentication, Model model, BindingResult result) {
		
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		System.out.println("usuarioAuth: " + usuarioAuth);
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		System.out.println("Documento: " + usuarioActualizar);
		// Se identifica si hubo un error
		if (result.hasErrors()) {
			System.out.println(usuarioActualizar);
			System.out.println("Existieron errores");
			
			return "perfil/cambiarContrasena";
		}
		
		// Se encripta la contrase�a para compararla con la que est� guardada en la BD
		Contrasena contrasena = new Contrasena();
	//	String contrasena_hash = contrasena.encriptarContrasena(usuarioActualizar.getContrasena());
	//	System.out.println("Contrase�a encriptada ingresada: " + contrasena_hash);
		
		//Se busca la contrase�a guardada en la base de datos que corresponde al usuario
		int numEmpleado = usuarioAuth.getNum_empleado();
		UsuarioConsulta usuarioConsulta = new UsuarioConsulta();
		usuarioConsulta = serviceUsuariosConsulta.buscarPorId(numEmpleado);
		System.out.println("Contrase�a guardada en BD: " + usuarioConsulta.getContrasena());
		
		int resultado = contrasena.comparaContrasena(usuarioActualizar.getContrasena(), usuarioConsulta.getContrasena());
		System.out.println(resultado);
		
		if(resultado == 1) {
			// Es la misma contrase�a
			return "perfil/confirmarContrasena";
		}
		else {
			// Las contrase�as son diferentes
			return "perfil/cambiarContrasena";
		}
	}
}
