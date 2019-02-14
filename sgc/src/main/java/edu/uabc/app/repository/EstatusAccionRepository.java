package edu.uabc.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.uabc.app.model.EstatusAccion;

@Repository
public interface EstatusAccionRepository extends JpaRepository<EstatusAccion, Integer> {

	// select * from Estatus_Accion
	List<EstatusAccion>findBy();
}
