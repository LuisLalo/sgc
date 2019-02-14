package edu.uabc.app.service;

import edu.uabc.app.model.UsuarioActualizar;

public interface IUsuariosActualizarService {
	void insertar(UsuarioActualizar usuario);
	UsuarioActualizar buscarPorId(int num_empleado);
	void eliminar(int num_empleado);
}
