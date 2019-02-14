package edu.uabc.app.service;

import java.util.List;

import edu.uabc.app.model.DocumentoConsulta;
import edu.uabc.app.model.UsuarioConsulta;
import edu.uabc.app.model.UsuarioDocumentoConsulta;

public interface IUsuarioDocumentoConsultaService {

	List<UsuarioDocumentoConsulta> buscarPorUsuario(UsuarioConsulta usuario);
	List<UsuarioDocumentoConsulta> buscarPorDocumento(DocumentoConsulta documento);
}
