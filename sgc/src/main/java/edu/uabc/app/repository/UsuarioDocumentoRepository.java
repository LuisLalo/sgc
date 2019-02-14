package edu.uabc.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.uabc.app.model.UsuarioDocumento;

@Repository
public interface UsuarioDocumentoRepository extends JpaRepository<UsuarioDocumento, Integer>{

	// select * from usuario_documento
	List<UsuarioDocumento> findBy();
	
	// select * from usuario_documento
	List<UsuarioDocumento> findByNumEmpleado(int numEmpleado);
	
	// select * from usuario_documento
	List<UsuarioDocumento> findByIdDocumento(int idDocumento);
	
	// select * from usuario_documento order by id_documento desc
	Optional<UsuarioDocumento> findTopByOrderByIdUsuarioDocumentoDesc();
}
