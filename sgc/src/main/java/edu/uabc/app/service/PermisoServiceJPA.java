package edu.uabc.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uabc.app.model.Menu;
import edu.uabc.app.model.Permiso;
import edu.uabc.app.repository.PermisoRepository;

@Service
public class PermisoServiceJPA implements IPermisoService{

	@Autowired
	private PermisoRepository permisoRepo;
	
	@Override
	public List<Permiso> buscarTodas() {
		List<Permiso> lista = permisoRepo.findAll();
		return lista;
	}

	@Override
	public List<Permiso> buscarPorNumEmpleado(int numEmpleado) {
		List<Permiso> lista = permisoRepo.findByNumEmpleado(numEmpleado);
		return lista;
	}

	@Override
	public Permiso buscarPorIdPermisoAndNumEmpleado(int idPermiso, int numEmpleado) {
		Permiso permiso = permisoRepo.findByIdPermisoAndNumEmpleado(idPermiso, numEmpleado);
		
		return permiso;
	}

	@Override
	public Permiso buscarPorNumEmpleadoAndMenu(int numEmpleado, Menu menu) {
		Permiso permiso = permisoRepo.findByNumEmpleadoAndMenu(numEmpleado, menu);
		return permiso;
	}

}
