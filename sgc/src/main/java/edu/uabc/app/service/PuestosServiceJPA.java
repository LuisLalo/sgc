package edu.uabc.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uabc.app.model.Puesto;
import edu.uabc.app.repository.PuestosRepository;

@Service
public class PuestosServiceJPA implements IPuestosService{

	@Autowired
	private PuestosRepository puestosRepo;
	
	@Override
	public List<Puesto> buscarTodas() {
		List<Puesto> lista = puestosRepo.findBy();
		return lista;
	}

	@Override
	public Puesto buscarPorId(int id_puesto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Puesto buscarPorPuesto(String nombre) {
		Puesto puesto = puestosRepo.findByNombre(nombre);
		return puesto;
	}

}
