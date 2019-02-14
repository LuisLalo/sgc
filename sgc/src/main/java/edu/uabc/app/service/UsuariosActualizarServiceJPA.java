package edu.uabc.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uabc.app.model.UsuarioActualizar;
import edu.uabc.app.repository.UsuariosActualizarRepository;

@Service
public class UsuariosActualizarServiceJPA implements IUsuariosActualizarService{

	@Autowired
	private UsuariosActualizarRepository usuariosRepo;

	@Override
	public void insertar(UsuarioActualizar usuario) {
		usuariosRepo.save(usuario);
		
	}

	@Override
	public void eliminar(int num_empleado) {
		usuariosRepo.deleteById(num_empleado);
	}

	@Override
	public UsuarioActualizar buscarPorId(int num_empleado) {
		Optional<UsuarioActualizar> optional = usuariosRepo.findById(num_empleado);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	

}
