package edu.uabc.app.service;

import java.util.List;

import edu.uabc.app.model.CausaRaiz;

public interface ICausaRaizService {

	List<CausaRaiz> buscarTodas();
	CausaRaiz buscarPorid(int idCausaRaiz);
}
