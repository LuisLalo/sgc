package edu.uabc.app.service;

import java.util.List;

import edu.uabc.app.model.DocumentoActualizar;

public interface IDocumentosActualizarService {

	void insertar(DocumentoActualizar documento);
	List<DocumentoActualizar>buscarTodas();
	DocumentoActualizar buscarPorId(int id_documento);
	void eliminar(int id_documento);
}
