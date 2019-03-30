package edu.uabc.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uabc.app.model.PermisoActualizar;
import edu.uabc.app.repository.PermisoActualizarRepository;

@Service
public class PermisoActualizarServiceJPA implements IPermisoActualizarService{

	@Autowired
	private PermisoActualizarRepository permisoActualizarRepo;
	
	@Override
	public void insertar(PermisoActualizar permisoActualizar) {
		permisoActualizarRepo.save(permisoActualizar);
	}

	@Override
	public PermisoActualizar buscarPorIdPermiso(int idPermiso) {
		PermisoActualizar permiso = permisoActualizarRepo.findByIdPermiso(idPermiso);
		return permiso;
	}

}
