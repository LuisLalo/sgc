package edu.uabc.app.service;

import java.util.List;

import edu.uabc.app.model.ActividadAccion;

public interface IActividadAccionService {

	void insertar(ActividadAccion actividadAccion);
	List<ActividadAccion> buscarTodas();
	ActividadAccion buscarPorId(int id_actividad_accion);
	void eliminar(int id_actividad_accion);
}
