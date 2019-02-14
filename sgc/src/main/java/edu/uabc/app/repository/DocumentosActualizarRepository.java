package edu.uabc.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.uabc.app.model.DocumentoActualizar;

@Repository
public interface DocumentosActualizarRepository extends JpaRepository<DocumentoActualizar, Integer> {
	// select * from DocumentoActualizar
		List<DocumentoActualizar> findBy();
}
