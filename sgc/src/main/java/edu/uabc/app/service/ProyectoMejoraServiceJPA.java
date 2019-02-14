package edu.uabc.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uabc.app.model.ProyectoMejora;
import edu.uabc.app.repository.ProyectoMejoraRepository;

@Service
public class ProyectoMejoraServiceJPA implements IProyectoMejoraService{

	@Autowired
	private ProyectoMejoraRepository proyectoMejoraRepo;
	
	@Override
	public List<ProyectoMejora> buscarTodas() {
		List<ProyectoMejora> lista = proyectoMejoraRepo.findAll();
		return lista;
	}

	@Override
	public void insertar(ProyectoMejora proyectoMejora) {
		proyectoMejoraRepo.save(proyectoMejora);
		
	}

	@Override
	public ProyectoMejora buscarPorId(int idProyectoMejora) {
		Optional<ProyectoMejora> optional = proyectoMejoraRepo.findById(idProyectoMejora);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void eliminar(int idProyectoMejora) {
		proyectoMejoraRepo.deleteById(idProyectoMejora);
	}

	@Override
	public ProyectoMejora buscarTopDepartamentoOrdenadoPorIdProyectoDesc(int departamento) {
		Optional<ProyectoMejora> optional = proyectoMejoraRepo.findTopByDepartamentoOrderByIdProyectoDesc(departamento);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
}