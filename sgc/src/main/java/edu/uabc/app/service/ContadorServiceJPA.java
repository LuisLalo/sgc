package edu.uabc.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uabc.app.model.Contador;
import edu.uabc.app.repository.ContadorRepository;

@Service
public class ContadorServiceJPA implements IContadorService{

	@Autowired
	private ContadorRepository contadorRepo;
	
	@Override
	public void insertar(Contador contador) {
		contadorRepo.save(contador);
		
	}

	@Override
	public List<Contador> buscarTodas() {
		List<Contador> lista = contadorRepo.findAll();
		return lista;
	}

	@Override
	public Contador buscarPorId(int id_contador) {
		Optional<Contador> optional = contadorRepo.findById(id_contador);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void eliminar(int id_contador) {
		contadorRepo.deleteById(id_contador);
		
	}

	@Override
	public List<Contador> buscarporNombre(String nombre) {
		List<Contador> lista = contadorRepo.findByNombre(nombre);
		return lista;
	}

}
