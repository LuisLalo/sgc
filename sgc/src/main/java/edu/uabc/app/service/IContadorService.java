package edu.uabc.app.service;

import java.util.List;

import edu.uabc.app.model.Contador;

public interface IContadorService {

	void insertar(Contador contador);
	List<Contador>buscarTodas();
	Contador buscarPorId(int id_contador);
	void eliminar(int id_contador);
	List<Contador>buscarporNombre(String nombre);
}
