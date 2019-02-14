package edu.uabc.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uabc.app.model.Rol;
import edu.uabc.app.repository.RolesRepository;

@Service
public class RolesServiceJPA implements IRolesService{

	@Autowired
	private RolesRepository rolesRepository;
	
	@Override
	public List<Rol> buscarTodas() {
		List<Rol> lista = rolesRepository.findBy();
		return lista;
	}

	@Override
	public Rol buscarPorId(int id_rol) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rol buscarPorNombre(String nombre) {
		Rol rol = rolesRepository.findByNombre(nombre);
		return rol;
	}

}
