package edu.uabc.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uabc.app.model.DocumentoActualizar;
import edu.uabc.app.repository.DocumentosActualizarRepository;

@Service
public class DocumentosActualizarServiceJPA implements IDocumentosActualizarService{

	@Autowired
	private DocumentosActualizarRepository documentosActualizarRepo;
	
	@Override
	public void insertar(DocumentoActualizar documentoActualizar) {
		documentosActualizarRepo.save(documentoActualizar);
	}

	@Override
	public List<DocumentoActualizar> buscarTodas() {
		List<DocumentoActualizar> lista = documentosActualizarRepo.findAll();
		return lista;
	}

	@Override
	public DocumentoActualizar buscarPorId(int id_documento) {
		Optional<DocumentoActualizar> optional = documentosActualizarRepo.findById(id_documento);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void eliminar(int id_documento) {
		documentosActualizarRepo.deleteById(id_documento);
		
	}

	
}
