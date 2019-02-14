package edu.uabc.app.service;

import java.util.List;

import edu.uabc.app.model.Puesto;

public interface IPuestosService {

	List<Puesto> buscarTodas();
	Puesto buscarPorId(int id_puesto);
	Puesto buscarPorPuesto(String nombre);
}
