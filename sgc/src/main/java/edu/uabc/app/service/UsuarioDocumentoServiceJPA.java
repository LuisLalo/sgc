package edu.uabc.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uabc.app.model.UsuarioDocumento;
import edu.uabc.app.repository.UsuarioDocumentoRepository;

@Service
public class UsuarioDocumentoServiceJPA implements IUsuarioDocumentoService{

	@Autowired
	UsuarioDocumentoRepository usuarioDocumentoRepo;
	
	@Override
	public List<UsuarioDocumento> buscarTodas() {
		List<UsuarioDocumento> lista = usuarioDocumentoRepo.findAll();  
		return lista;
	}

	@Override
	public List<UsuarioDocumento> buscarPorNumEpleado(int numEmpleado) {
		List<UsuarioDocumento> lista = usuarioDocumentoRepo.findByNumEmpleado(numEmpleado);
		return lista;
	}

	@Override
	public List<UsuarioDocumento> buscarPorIdDocumento(int idDocumento) {
		List<UsuarioDocumento> lista = usuarioDocumentoRepo.findByIdDocumento(idDocumento);
		return lista;
	}

	@Override
	public void insertar(UsuarioDocumento usuarioDocumento) {
		usuarioDocumentoRepo.save(usuarioDocumento);
	}

	@Override
	public UsuarioDocumento buscarPrimeroPorIdUsuarioDocumentoOrdenadosDesc() {
		Optional<UsuarioDocumento> optional = usuarioDocumentoRepo.findTopByOrderByIdUsuarioDocumentoDesc();
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

}