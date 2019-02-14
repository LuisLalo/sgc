package edu.uabc.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uabc.app.model.TipoAuditoria;
import edu.uabc.app.repository.TiposAuditoriaRepository;

@Service
public class TiposAuditoriaServiceJPA implements ITiposAuditoriaService{

	@Autowired
	private TiposAuditoriaRepository tiposAuditoriaRepo;
	
	@Override
	public void insertar(TipoAuditoria tipoAuditoria) {
		tiposAuditoriaRepo.save(tipoAuditoria);
		
	}

	@Override
	public List<TipoAuditoria> buscarTodas() {
		List<TipoAuditoria> lista = tiposAuditoriaRepo.findAll();
		return lista;
	}

	@Override
	public TipoAuditoria buscarPorId(int id_tipo_auditoria) {
		Optional<TipoAuditoria> optional = tiposAuditoriaRepo.findById(id_tipo_auditoria);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void eliminar(int id_tipo_auditoria) {
		tiposAuditoriaRepo.deleteById(id_tipo_auditoria);
		
	}

}
