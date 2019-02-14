package edu.uabc.app.service;

import java.util.List;

import edu.uabc.app.model.Rol;

public interface IRolesService {

	List<Rol> buscarTodas();
	Rol buscarPorId(int id_rol);
	Rol buscarPorNombre(String nombre);
}
