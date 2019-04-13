package edu.uabc.app.service;

import java.util.List;

import edu.uabc.app.model.Menu;
import edu.uabc.app.model.Permiso;

public interface IPermisoService {

	List<Permiso> buscarTodas();
	List<Permiso> buscarPorNumEmpleado(int numEmpleado);
	Permiso buscarPorIdPermisoAndNumEmpleado(int idPermiso, int numEmpleado);
	Permiso buscarPorNumEmpleadoAndMenu(int numEmpleado, Menu menu);
 }
