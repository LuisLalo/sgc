package edu.uabc.app.service;

import java.util.List;

import edu.uabc.app.model.ClasificadorDocumento;

public interface IClasificadorDocumentosService {

	List<ClasificadorDocumento> buscarTodas();
	ClasificadorDocumento buscarPorId(int id_clasificador_documento);
}
