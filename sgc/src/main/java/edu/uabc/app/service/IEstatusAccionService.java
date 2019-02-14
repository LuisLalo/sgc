package edu.uabc.app.service;

import java.util.List;

import edu.uabc.app.model.EstatusAccion;

public interface IEstatusAccionService {

	List<EstatusAccion> buscarTodas();
	EstatusAccion buscarPorId(int id_estatus_accion);
}
