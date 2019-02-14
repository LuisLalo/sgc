package edu.uabc.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.uabc.app.model.DocumentoConsulta;
import edu.uabc.app.model.UsuarioConsulta;
import edu.uabc.app.model.UsuarioDocumentoConsulta;

@Repository
public interface UsuarioDocumentoConsultaRepository extends JpaRepository<UsuarioDocumentoConsulta, Integer>{

	// select * from Usuario_Documento
	List<UsuarioDocumentoConsulta> findBy();
	
	// select * from Documento where usuario
	List<UsuarioDocumentoConsulta> findByUsuario(UsuarioConsulta usuario);
	
	// select * from Documento where documento
	List<UsuarioDocumentoConsulta> findByDocumento(DocumentoConsulta documento);
	
	// select * from UsuarioDocumento where documento
	//List<UsuarioDocumentoConsulta> 
}
