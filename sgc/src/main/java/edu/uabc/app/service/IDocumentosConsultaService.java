package edu.uabc.app.service;

import java.util.List;

import edu.uabc.app.model.Departamento;
import edu.uabc.app.model.DocumentoConsulta;
import edu.uabc.app.model.TipoDocumento;

public interface IDocumentosConsultaService {

	void insertar(DocumentoConsulta documentoConsulta);
	List<DocumentoConsulta> buscarTodas();
	DocumentoConsulta buscarPorId(int idDocumento);
	void eliminar(int idDocumento);
	List<DocumentoConsulta> buscarPorEstatus(int estatus);
	List<DocumentoConsulta> buscarPorEstatusAndDepartamento(int estatus, Departamento departamento);
	DocumentoConsulta buscarPrimeroPorIdDocumentoOrdenadoDesc();
	List<DocumentoConsulta> buscarPorEstatusAndDepartamentoAndTipoDocumento(int estatus, Departamento departamento, TipoDocumento tipoDocumento);
	List<DocumentoConsulta> buscarPorEstatusAndDepartamentoAndTipoDocumentoOrdenadoPorOrdenDocumento(int estatus, Departamento departamento, TipoDocumento tipoDocumento);
	List<DocumentoConsulta> buscarPorTipoDocumento(TipoDocumento tipoDocumento);
	List<DocumentoConsulta> buscarPorTipoDocumentoAndDepartamento(TipoDocumento tipoDocumento, Departamento departamento);
	List<DocumentoConsulta> buscarPorClasificadorDocumentoAndEstatus(int clasificadorDocumento, int estatus);
}
