package edu.uabc.app.util;

import java.util.ArrayList;
import java.util.List;

import edu.uabc.app.model.PermisoActualizar;
import edu.uabc.app.model.Menu;
import edu.uabc.app.model.UsuarioActualizar;

public class AsignarPermisos {

	public static List<PermisoActualizar> asignarPermiso(UsuarioActualizar usuario, List<Menu> menu) {
		
		List<PermisoActualizar> lista = new ArrayList<PermisoActualizar>();
		PermisoActualizar permiso = new PermisoActualizar();
		switch(usuario.getId_rol()) {
		case 1: 
			// Permisos del Usuario General
			
			
			break;
		case 2: 
			// Permisos del Administrador de Area
			
			break;
		case 3: 
			// Permisos del Administrador del SGC
			for(int cont=0;cont<menu.size();cont++) {
				permiso.setIdPermiso(cont);
				permiso.setNumEmpleado(usuario.getNum_empleado());
				permiso.setIdMenu(menu.get(cont).getIdMenu());
				permiso.setIdPermiso(1);
				lista.add(cont, permiso);
			}
			break;
		case 4: 
			// Permisos del Auditor Interno
			
			break;
		case 5: 
			// Permisos del Auditor Externo
			
			break;
		case 6: 
			// Permisos del Invitado
			
			break;
		}
		
		return null;
	}
}
