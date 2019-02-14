package edu.uabc.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.uabc.app.model.ActividadAccionConsulta;

@Repository
public interface ActividadAccionConsultaRepository extends JpaRepository<ActividadAccionConsulta, Integer> {

	// select * from actividad_accion where id_proyecto
	List<ActividadAccionConsulta> findByIdProyecto(int idProyecto);
	
	// select * from actividad_accion where accion_proyecto
	List<ActividadAccionConsulta> findByIdAccion(int idAccion);
}
