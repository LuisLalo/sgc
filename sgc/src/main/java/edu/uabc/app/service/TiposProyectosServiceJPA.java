package edu.uabc.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uabc.app.model.TipoProyecto;
import edu.uabc.app.repository.TiposProyectosRepository;

@Service
public class TiposProyectosServiceJPA implements ITiposProyectosService{

	@Autowired
	TiposProyectosRepository tiposProyectosRepo;
	
	@Override
	public List<TipoProyecto> buscarTodas() {
		List<TipoProyecto> lista = tiposProyectosRepo.findAll();
		return lista;
	}

}
