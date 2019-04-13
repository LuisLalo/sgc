package edu.uabc.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.uabc.app.model.Departamento;
import edu.uabc.app.model.DocumentoConsulta;
import edu.uabc.app.model.TipoDocumento;

@Repository
public interface DocumentosConsultaRepository extends JpaRepository<DocumentoConsulta, Integer> {

	// select * from Documento
	List<DocumentoConsulta> findBy();
	
	// select * from Documento where estatus
	List<DocumentoConsulta> findByEstatus(int estatus);
	
	// select * from documento where estatus and departamento
	List<DocumentoConsulta> findByEstatusAndDepartamento(int estatus, Departamento departamento);
	
	// select * from documento order by id_documento desc
	Optional<DocumentoConsulta> findTopByOrderByIdDocumentoDesc();
	//@Query("select u from documento")
	//List<UsuarioConsulta> findTopByIdDocumento();
	
	// select * from documento where estatus and departamento and tipo_documento
	List<DocumentoConsulta> findByEstatusAndDepartamentoAndTipoDocumento(int estatus, Departamento departamento, TipoDocumento tipoDocumento);
	
	// select * from documento where estatus and departamento and tipo_documento order by orden_documento
	List<DocumentoConsulta> findByEstatusAndDepartamentoAndTipoDocumentoOrderByOrdenDocumento(int estatus, Departamento departamento, TipoDocumento tipoDocumento);
	
	// select * from documento where tipo_documento
	List<DocumentoConsulta> findByTipoDocumento(TipoDocumento tipoDocumento);
	
	// select * from documento where departamento and tipo_documento
	List<DocumentoConsulta> findByTipoDocumentoAndDepartamento(TipoDocumento tipoDocumento, Departamento departamento);
	
	// select * from documento where clasificador_documento and estatus
	List<DocumentoConsulta> findByClasificadorDocumentoAndEstatus(int clasificadorDocumento, int estatus);
}
