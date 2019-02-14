package edu.uabc.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uabc.app.model.ObservacionNorma;
import edu.uabc.app.repository.ObservacionNormaRepository;

@Service
public class ObservacionNormaServiceJPA implements IObservacionNormaService{

	@Autowired
	private ObservacionNormaRepository observacionNormaRepo;
	
	@Override
	public List<ObservacionNorma> buscarTodas() {
		List<ObservacionNorma> lista = observacionNormaRepo.findAll();
		return lista;
	}

}
