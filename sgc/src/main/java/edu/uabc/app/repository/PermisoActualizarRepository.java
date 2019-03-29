package edu.uabc.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.uabc.app.model.PermisoActualizar;

@Repository
public interface PermisoActualizarRepository extends JpaRepository<PermisoActualizar, Integer> {

	
}
