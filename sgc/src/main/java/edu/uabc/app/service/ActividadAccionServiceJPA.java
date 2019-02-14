package edu.uabc.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uabc.app.model.ActividadAccion;
import edu.uabc.app.repository.ActividadAccionRepository;

@Service
public class ActividadAccionServiceJPA implements IActividadAccionService {

	@Autowired
	private ActividadAccionRepository actividadAccionRepo;
	
	@Override
	public void insertar(ActividadAccion actividadAccion) {
		actividadAccionRepo.save(actividadAccion);
		
	}

	@Override
	public List<ActividadAccion> buscarTodas() {
		List<ActividadAccion> lista = actividadAccionRepo.findAll();
		return lista;
	}

	@Override
	public ActividadAccion buscarPorId(int id_actividad_accion) {
		Optional<ActividadAccion> optional = actividadAccionRepo.findById(id_actividad_accion);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void eliminar(int id_actividad_accion) {
		actividadAccionRepo.deleteById(id_actividad_accion);
	}

}