package edu.uabc.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uabc.app.model.CausaRaiz;
import edu.uabc.app.repository.CausaRaizRepository;

@Service
public class CausaRaizServiceJPA implements ICausaRaizService{

	@Autowired
	private CausaRaizRepository causaRaizRepo;
	
	@Override
	public List<CausaRaiz> buscarTodas() {
		List<CausaRaiz> lista = causaRaizRepo.findAll();
		return lista;
	}

	@Override
	public CausaRaiz buscarPorid(int idCausaRaiz) {
		Optional<CausaRaiz> optional = causaRaizRepo.findById(idCausaRaiz);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

}
