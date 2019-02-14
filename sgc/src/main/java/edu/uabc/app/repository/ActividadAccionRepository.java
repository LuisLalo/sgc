package edu.uabc.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.uabc.app.model.ActividadAccion;

@Repository
public interface ActividadAccionRepository extends JpaRepository<ActividadAccion, Integer> {

	// select * from Actividad_Accion
	List<ActividadAccion>findBy();
}
