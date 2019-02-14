package edu.uabc.app.service;

import java.util.List;

import edu.uabc.app.model.ProvieneDe;

public interface IProvieneDeService {

	List<ProvieneDe>buscarTodas();
	ProvieneDe buscarPorId(int idProvieneDe);
}
