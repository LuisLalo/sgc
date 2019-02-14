package edu.uabc.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uabc.app.model.TipoArchivo;
import edu.uabc.app.repository.TiposArchivosRepository;

@Service
public class TiposArchivosServiceJPA implements ITiposArchivosService{

	@Autowired
	private TiposArchivosRepository tiposArchivosRepo;
	
	@Override
	public List<TipoArchivo> buscarTodas() {
		List<TipoArchivo> lista = tiposArchivosRepo.findBy();
		return lista;
	}

	@Override
	public TipoArchivo buscarPorId(int id_tipo_archivo) {
		Optional<TipoArchivo> optional = tiposArchivosRepo.findById(id_tipo_archivo);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

}
