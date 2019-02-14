package edu.uabc.app.service;

import java.util.List;

import edu.uabc.app.model.ProyectoMejora;

public interface IProyectoMejoraService {

	void insertar(ProyectoMejora proyectoMejora);
	List<ProyectoMejora> buscarTodas();
	ProyectoMejora buscarPorId(int idProyectoMejora);
	void eliminar(int idProyectoMejora);
	ProyectoMejora buscarTopDepartamentoOrdenadoPorIdProyectoDesc(int departamento);
}
