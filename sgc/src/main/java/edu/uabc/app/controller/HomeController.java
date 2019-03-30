package edu.uabc.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.uabc.app.model.Menu;
import edu.uabc.app.model.Permiso;
import edu.uabc.app.model.UsuarioConsulta;
import edu.uabc.app.service.IMenuService;
import edu.uabc.app.service.IPermisoService;
import edu.uabc.app.service.IUsuariosConsultaService;
import edu.uabc.app.util.CrearMenu;

@Controller
public class HomeController {
	
	@Autowired
	private IUsuariosConsultaService serviceUsuariosConsulta;
	
	@Autowired
	private IMenuService serviceMenu;
	
	@Autowired
	private IPermisoService servicePermiso;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String mostrarLogin() {
		return "login";
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String mostrarPrincipal(Authentication authentication, Model model) {
		System.out.println("Username: " + authentication.getName());
		System.out.println("Username: " + authentication.getDetails());
		System.out.println("Username: " + authentication.toString());
		System.out.println("Username: " + authentication.getCredentials());
		System.out.println("Username: " + authentication.getClass());
		System.out.println("Username: " + authentication.getPrincipal());
		
		// Se agrega el nombre del usuario
		UsuarioConsulta usuarioAuth = serviceUsuariosConsulta.buscarPorCorreo(authentication.getName());
		model.addAttribute("usuarioAuth", usuarioAuth);
		
		// Se buscan los permisos a los que puede acceder el usuario
		List<Permiso> permiso = servicePermiso.buscarPorNumEmpleado(usuarioAuth.getNum_empleado());
		System.out.println("Permiso: " + permiso);
		
		// Se buscan las opciones y secciones del menu generado por base de datos
		List<Menu> listaM = serviceMenu.buscarPorEstatusAndIdTipoVentanaOrderByOrden(1, 0);
		List<Menu> listaSM = serviceMenu.buscarPorEstatusAndIdTipoVentanaOrderByOrden(1, 1);
		List<Menu> listaSSM = serviceMenu.buscarPorEstatusAndIdTipoVentanaOrderByOrden(1, 2);
		
		// Se identifica que el usuario tenga permisos para ver las opciones del menu
		List<Menu> listaMenu = new ArrayList<Menu>();
		int conta = 0;
		for(int contador=0;contador<listaM.size();contador++) {
			for(int cont=0;cont<permiso.size();cont++) {
				if((permiso.get(cont).getMenu().getIdMenu()==listaM.get(contador).getIdMenu()) && permiso.get(cont).getEstatusPermiso().getIdEstatus()==1){
					listaMenu.add(conta, listaM.get(contador));
					conta++;
				}
			}
		}
		
		// Se identifica que el usuario tenga permisos para ver las opciones del submenu
		List<Menu> listaSubMenu = new ArrayList<Menu>();
		conta = 0;
		for(int contador=0;contador<listaSM.size();contador++) {
			for(int cont=0;cont<permiso.size();cont++) {
				if((permiso.get(cont).getMenu().getIdMenu()==listaSM.get(contador).getIdMenu()) && permiso.get(cont).getEstatusPermiso().getIdEstatus()==1){
					listaSubMenu.add(conta, listaSM.get(contador));
					conta++;
				}
			}
		}
		
		// Se identifica que el usuario tenga permisos para ver las opciones del subsubmenu
		List<Menu> listaSubSubMenu = new ArrayList<Menu>();
		conta = 0;
		for(int contador=0;contador<listaSSM.size();contador++) {
			for(int cont=0;cont<permiso.size();cont++) {
				if((permiso.get(cont).getMenu().getIdMenu()==listaSSM.get(contador).getIdMenu()) && permiso.get(cont).getEstatusPermiso().getIdEstatus()==1){
					listaSubSubMenu.add(conta, listaSSM.get(contador));
					conta++;
				}
			}
		}
		
		String menuCompleto = CrearMenu.menu(listaMenu, listaSubMenu, listaSubSubMenu);
		model.addAttribute("menuCompleto", menuCompleto);
		
		for(GrantedAuthority rol: authentication.getAuthorities()) {
			System.out.println("Rol: " + rol.getAuthority());
			//System.out.println("Menu: " + listaMenu);
		}
		
		
		
		return "home";
	}
	
	@RequestMapping(value="/documentos-autorizar", method=RequestMethod.GET)
	public String mostrarDocumentosAutorizar() {
		return "documentos-autorizar";
	}
	
	@RequestMapping(value="/menu", method=RequestMethod.GET)
	public String mostrarMenu() {
		return "menu";
	}
	
	@RequestMapping(value="/alta-capacitacion", method=RequestMethod.GET)
	public String mostrarAltaCapacitacion() {
		return "alta-capacitacion";
	}
	
	@RequestMapping(value="/registro-capacitacion", method=RequestMethod.GET)
	public String mostrarRegistroCapacitacion() {
		return "registro-capacitacion";
	}
	
	@RequestMapping(value="/consultar-capacitacion", method=RequestMethod.GET)
	public String mostrarConsultarCapacitacion() {
		return "consultar-capacitacion";
	}
	
	@RequestMapping(value="/convocatoria", method=RequestMethod.GET)
	public String mostrarConvocatoria() {
		return "convocatoria";
	}
}
