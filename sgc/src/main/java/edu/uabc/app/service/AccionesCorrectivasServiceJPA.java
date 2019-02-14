package edu.uabc.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uabc.app.model.AccionCorrectiva;
import edu.uabc.app.repository.AccionesCorrectivasRepository;

@Service
public class AccionesCorrectivasServiceJPA implements IAccionesCorrectivasService{

	@Autowired
	private AccionesCorrectivasRepository accionesCorrectivasRepo;
	
	@Override
	public void insertar(AccionCorrectiva accionCorrectiva) {
		accionesCorrectivasRepo.save(accionCorrectiva);
		
	}

	@Override
	public List<AccionCorrectiva> buscarTodas() {
		List<AccionCorrectiva> lista = accionesCorrectivasRepo.findAll();
		return lista;
	}

	@Override
	public AccionCorrectiva buscarPorId(int id_accion) {
		Optional<AccionCorrectiva>optional = accionesCorrectivasRepo.findById(id_accion);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void eliminar(int id_accion) {
		accionesCorrectivasRepo.deleteById(id_accion);
		
	}

	@Override
	public List<AccionCorrectiva> buscarPorDepartamento(int departamento) {
		List<AccionCorrectiva> lista = accionesCorrectivasRepo.findByDepartamento(departamento);
		return lista;
	}

	@Override
	public AccionCorrectiva buscarTopDepartamentoOrdenadoPorIdAccionDesc(int departamento) {
		Optional<AccionCorrectiva> optional = accionesCorrectivasRepo.findTopByDepartamentoOrderByIdAccionDesc(departamento);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

}
