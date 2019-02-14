package edu.uabc.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uabc.app.model.Departamento;
import edu.uabc.app.repository.DepartamentosRepository;

@Service
public class DepartamentosServiceJPA implements IDepartamentosService{

	@Autowired
	private DepartamentosRepository departamentosRepo;
	
	@Override
	public List<Departamento> buscarTodas() {
		List<Departamento> lista = departamentosRepo.findBy();
		return lista;
	}

	@Override
	public Departamento buscarPorId(int id_departamento) {
		Optional<Departamento> optional = departamentosRepo.findById(id_departamento);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public Departamento buscarPorNombre(String nombre) {
		Departamento departamento = departamentosRepo.findByNombre(nombre);
		return departamento;
	}

}
