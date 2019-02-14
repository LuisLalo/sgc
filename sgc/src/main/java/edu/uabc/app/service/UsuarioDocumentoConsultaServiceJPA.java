package edu.uabc.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uabc.app.model.DocumentoConsulta;
import edu.uabc.app.model.UsuarioConsulta;
import edu.uabc.app.model.UsuarioDocumentoConsulta;
import edu.uabc.app.repository.UsuarioDocumentoConsultaRepository;

@Service
public class UsuarioDocumentoConsultaServiceJPA implements IUsuarioDocumentoConsultaService {

	@Autowired
	UsuarioDocumentoConsultaRepository usuarioDocumentoConsultaRepo;
	
	@Override
	public List<UsuarioDocumentoConsulta> buscarPorUsuario(UsuarioConsulta usuario) {
		List<UsuarioDocumentoConsulta> lista = usuarioDocumentoConsultaRepo.findByUsuario(usuario);
		return lista;
	}

	@Override
	public List<UsuarioDocumentoConsulta> buscarPorDocumento(DocumentoConsulta documento) {
		List<UsuarioDocumentoConsulta> lista = usuarioDocumentoConsultaRepo.findByDocumento(documento);
		return lista;
	}

}
