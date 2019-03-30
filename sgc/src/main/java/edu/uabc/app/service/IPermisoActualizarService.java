package edu.uabc.app.service;

import edu.uabc.app.model.PermisoActualizar;

public interface IPermisoActualizarService {

	void insertar(PermisoActualizar permisoActualizar);
	PermisoActualizar buscarPorIdPermiso(int idPermiso);
}
