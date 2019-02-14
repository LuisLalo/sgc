package edu.uabc.app.service;

import java.util.List;

import edu.uabc.app.model.EstatusUsuario;

public interface IEstatusUsuarioService {

	void insertar(EstatusUsuario estatusUsuario);
	List<EstatusUsuario> buscarTodas();
}
