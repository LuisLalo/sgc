package edu.uabc.app.service;

import java.util.List;

import edu.uabc.app.model.UsuarioDocumento;

public interface IUsuarioDocumentoService {

	void insertar(UsuarioDocumento usuarioDocumento);
	List<UsuarioDocumento> buscarTodas();
	List<UsuarioDocumento> buscarPorNumEpleado(int numEmpleado);
	List<UsuarioDocumento> buscarPorIdDocumento(int idDocumento);
	UsuarioDocumento buscarPrimeroPorIdUsuarioDocumentoOrdenadosDesc();
}
