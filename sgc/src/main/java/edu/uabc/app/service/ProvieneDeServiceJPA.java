package edu.uabc.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uabc.app.model.ProvieneDe;
import edu.uabc.app.repository.ProvieneDeRepository;

@Service
public class ProvieneDeServiceJPA implements IProvieneDeService{

	@Autowired
	private ProvieneDeRepository provieneDeRepo;
	
	@Override
	public List<ProvieneDe> buscarTodas() {
		List<ProvieneDe> lista = provieneDeRepo.findAll();
		return lista;
	}

	@Override
	public ProvieneDe buscarPorId(int idProvieneDe) {
		Optional<ProvieneDe>optional = provieneDeRepo.findById(idProvieneDe);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

}
