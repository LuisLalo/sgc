package edu.uabc.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uabc.app.model.EstatusUsuario;
import edu.uabc.app.repository.EstatusUsuarioRepository;

@Service
public class EstatusUsuarioServiceJPA implements IEstatusUsuarioService{

	@Autowired
	private EstatusUsuarioRepository estatusUsuarioRepo;
	
	@Override
	public void insertar(EstatusUsuario estatusUsuario) {
		// 
		
	}

	@Override
	public List<EstatusUsuario> buscarTodas() {
		List<EstatusUsuario> lista = estatusUsuarioRepo.findAll();
		return lista;
	}

}
