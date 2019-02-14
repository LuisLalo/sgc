package edu.uabc.app.service;

import java.util.List;

import edu.uabc.app.model.TipoAuditoria;

public interface ITiposAuditoriaService {

	void insertar(TipoAuditoria tipoAuditoria);
	List<TipoAuditoria> buscarTodas();
	TipoAuditoria buscarPorId(int id_tipo_auditoria);
	void eliminar(int id_tipo_auditoria);
}
