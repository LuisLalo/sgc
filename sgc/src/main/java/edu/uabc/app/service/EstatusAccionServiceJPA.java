package edu.uabc.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uabc.app.model.EstatusAccion;
import edu.uabc.app.repository.EstatusAccionRepository;

@Service
public class EstatusAccionServiceJPA implements IEstatusAccionService{

	@Autowired
	private EstatusAccionRepository estatusAccionRepo;
	
	@Override
	public List<EstatusAccion> buscarTodas() {
		List<EstatusAccion> lista = estatusAccionRepo.findAll();
		return lista;
	}

	@Override
	public EstatusAccion buscarPorId(int id_estatus_accion) {
		Optional<EstatusAccion> optional = estatusAccionRepo.findById(id_estatus_accion);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

}
