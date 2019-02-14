package edu.uabc.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uabc.app.model.ActividadAccionConsulta;
import edu.uabc.app.repository.ActividadAccionConsultaRepository;

@Service
public class ActividadAccionConsultaServiceJPA implements IActividadAccionConsultaService{

	@Autowired
	ActividadAccionConsultaRepository actividadAccionConsultaRepo;
	
	@Override
	public List<ActividadAccionConsulta> buscarTodas() {
		List<ActividadAccionConsulta> lista = actividadAccionConsultaRepo.findAll();
		return lista;
	}

	@Override
	public ActividadAccionConsulta buscarPorId(int idActividadAccion) {
		Optional<ActividadAccionConsulta> optional = actividadAccionConsultaRepo.findById(idActividadAccion);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public List<ActividadAccionConsulta> buscarPorIdProyecto(int idProyecto) {
		List<ActividadAccionConsulta> lista = actividadAccionConsultaRepo.findByIdProyecto(idProyecto);
		return lista;
	}

	@Override
	public List<ActividadAccionConsulta> buscarPorIdAccion(int idAccion) {
		List<ActividadAccionConsulta> lista = actividadAccionConsultaRepo.findByIdAccion(idAccion);
		return lista;
	}
}
