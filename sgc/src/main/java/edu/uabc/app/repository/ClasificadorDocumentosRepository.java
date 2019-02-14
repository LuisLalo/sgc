package edu.uabc.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.uabc.app.model.ClasificadorDocumento;

@Repository
public interface ClasificadorDocumentosRepository extends JpaRepository<ClasificadorDocumento, Integer> {

	// select * from Clasificador_Documento
	List<ClasificadorDocumento> findBy();
}
