package edu.uabc.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uabc.app.model.TipoDocumento;
import edu.uabc.app.repository.TiposDocumentosRepository;

@Service
public class TiposDocumentosServiceJPA implements ITiposDocumentosService{

	@Autowired
	private TiposDocumentosRepository tiposDocumentosRepo;

	@Override
	public List<TipoDocumento> buscarTodas() {
		List<TipoDocumento> lista = tiposDocumentosRepo.findAll();
		return lista;
	}

	@Override
	public TipoDocumento buscarPorId(int id_tipo_documento) {
		Optional<TipoDocumento> optional = tiposDocumentosRepo.findById(id_tipo_documento);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public TipoDocumento buscarPorNombre(String nombre) {
		Optional<TipoDocumento> optional = tiposDocumentosRepo.findByNombre(nombre);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
}
