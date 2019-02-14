package edu.uabc.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uabc.app.model.Departamento;
import edu.uabc.app.model.DocumentoConsulta;
import edu.uabc.app.model.TipoDocumento;
import edu.uabc.app.repository.DocumentosConsultaRepository;

@Service
public class DocumentosConsultaServiceJPA implements IDocumentosConsultaService {

	@Autowired
	private DocumentosConsultaRepository documentosConsultaRepo;
	
	@Override
	public void insertar(DocumentoConsulta documentoConsulta) {
		documentosConsultaRepo.save(documentoConsulta);
	}

	@Override
	public List<DocumentoConsulta> buscarTodas() {
		List<DocumentoConsulta> lista = documentosConsultaRepo.findAll();
		return lista;
	}

	@Override
	public DocumentoConsulta buscarPorId(int idDocumento) {
		Optional<DocumentoConsulta> optional = documentosConsultaRepo.findById(idDocumento);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void eliminar(int idDocumento) {
		documentosConsultaRepo.deleteById(idDocumento);
		
	}

	@Override
	public List<DocumentoConsulta> buscarPorEstatus(int estatus) {
		List<DocumentoConsulta> lista = documentosConsultaRepo.findByEstatus(estatus);
		return lista;
	}

	@Override
	public List<DocumentoConsulta> buscarPorEstatusAndDepartamento(int estatus, Departamento departamento) {
		List<DocumentoConsulta> lista = documentosConsultaRepo.findByEstatusAndDepartamento(estatus, departamento);
		return lista;
	}

	@Override
	public DocumentoConsulta buscarPrimeroPorIdDocumentoOrdenadoDesc() {
		Optional<DocumentoConsulta> optional = documentosConsultaRepo.findTopByOrderByIdDocumentoDesc();
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public List<DocumentoConsulta> buscarPorEstatusAndDepartamentoAndTipoDocumento(int estatus,
			Departamento departamento, TipoDocumento tipoDocumento) {
		List<DocumentoConsulta> lista = documentosConsultaRepo.findByEstatusAndDepartamentoAndTipoDocumento(estatus, departamento, tipoDocumento);
		return lista;
	}

	@Override
	public List<DocumentoConsulta> buscarPorEstatusAndDepartamentoAndTipoDocumentoOrdenadoPorOrdenDocumento(int estatus,
			Departamento departamento, TipoDocumento tipoDocumento) {
		List<DocumentoConsulta> lista = documentosConsultaRepo.findByEstatusAndDepartamentoAndTipoDocumentoOrderByOrdenDocumento(estatus, departamento, tipoDocumento);
		return lista;
	}

	@Override
	public List<DocumentoConsulta> buscarPorTipoDocumento(TipoDocumento tipoDocumento) {
		List<DocumentoConsulta> lista = documentosConsultaRepo.findByTipoDocumento(tipoDocumento);
		return lista;
	}

	@Override
	public List<DocumentoConsulta> buscarPorTipoDocumentoAndDepartamento(TipoDocumento tipoDocumento,
			Departamento departamento) {
		List<DocumentoConsulta> lista = documentosConsultaRepo.findByTipoDocumentoAndDepartamento(tipoDocumento, departamento);
		return lista;
	}
}
