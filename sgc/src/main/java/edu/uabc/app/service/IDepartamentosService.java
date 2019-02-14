package edu.uabc.app.service;

import java.util.List;

import edu.uabc.app.model.Departamento;

public interface IDepartamentosService {

	List<Departamento> buscarTodas();
	Departamento buscarPorId(int id_departamento);
	Departamento buscarPorNombre(String nombre);
}
