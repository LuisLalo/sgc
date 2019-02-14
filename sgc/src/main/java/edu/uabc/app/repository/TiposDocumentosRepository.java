package edu.uabc.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.uabc.app.model.TipoDocumento;

@Repository
public interface TiposDocumentosRepository extends JpaRepository<TipoDocumento, Integer> {

	// select * from Tipo_Documento
	List<TipoDocumento>findBy();
	
	// select * from Tipo_documento where nombre
	Optional<TipoDocumento> findByNombre(String nombre);
}
