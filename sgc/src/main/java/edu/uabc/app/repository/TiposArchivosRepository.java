package edu.uabc.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.uabc.app.model.TipoArchivo;

@Repository
public interface TiposArchivosRepository extends JpaRepository<TipoArchivo, Integer> {

	// select * from Tipo_Archivo
	List<TipoArchivo>findBy();
}
