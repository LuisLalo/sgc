package edu.uabc.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uabc.app.model.Evaluacion;
import edu.uabc.app.repository.EvaluacionRepository;

@Service
public class EvaluacionServiceJPA implements IEvaluacionService{

	@Autowired
	EvaluacionRepository evaluacionRepo;
	
	@Override
	public List<Evaluacion> buscarTodas() {
		List<Evaluacion> lista = evaluacionRepo.findAll();
		return lista;
	}

}
