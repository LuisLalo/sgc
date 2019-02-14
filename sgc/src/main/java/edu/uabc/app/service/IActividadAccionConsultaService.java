package edu.uabc.app.service;

import java.util.List;

import edu.uabc.app.model.ActividadAccionConsulta;

public interface IActividadAccionConsultaService {

	List<ActividadAccionConsulta> buscarTodas();
	ActividadAccionConsulta buscarPorId(int idActividadAccion);
	List<ActividadAccionConsulta> buscarPorIdProyecto(int idProyecto);
	List<ActividadAccionConsulta> buscarPorIdAccion(int idAccion);
}
