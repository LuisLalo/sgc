package edu.uabc.app.service;

import java.util.List;

import edu.uabc.app.model.AccionCorrectiva;

public interface IAccionesCorrectivasService {

	void insertar(AccionCorrectiva accionCorrectiva);
	List<AccionCorrectiva> buscarTodas();
	AccionCorrectiva buscarPorId(int id_accion);
	void eliminar(int id_accion);
	List<AccionCorrectiva> buscarPorDepartamento(int departamento);
	AccionCorrectiva buscarTopDepartamentoOrdenadoPorIdAccionDesc(int departamento);
}
