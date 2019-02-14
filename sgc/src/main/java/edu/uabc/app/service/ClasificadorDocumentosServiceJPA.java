package edu.uabc.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uabc.app.model.ClasificadorDocumento;
import edu.uabc.app.repository.ClasificadorDocumentosRepository;

@Service
public class ClasificadorDocumentosServiceJPA implements IClasificadorDocumentosService{

	@Autowired
	private ClasificadorDocumentosRepository clasificadorDocumentosRepo;
	
	@Override
	public List<ClasificadorDocumento> buscarTodas() {
		List<ClasificadorDocumento> lista = clasificadorDocumentosRepo.findAll();
		return lista;
	}

	@Override
	public ClasificadorDocumento buscarPorId(int id_clasificador_documento) {
		Optional<ClasificadorDocumento> optional = clasificadorDocumentosRepo.findById(id_clasificador_documento);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

}
