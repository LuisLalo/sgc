package edu.uabc.app.service;

import java.util.List;

import edu.uabc.app.model.TipoDocumento;

public interface ITiposDocumentosService {

	List<TipoDocumento> buscarTodas();
	TipoDocumento buscarPorId(int id_tipo_documento);
	TipoDocumento buscarPorNombre(String nombre);
}
