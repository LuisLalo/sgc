package edu.uabc.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.uabc.app.model.ProyectoMejora;

@Repository
public interface ProyectoMejoraRepository extends JpaRepository<ProyectoMejora, Integer> {

	// select * from proyecto_mejora where departamento
	List<ProyectoMejora> findByDepartamento(int departamento);
	
	// select * from proyecto_mejora order by id_proyecto and departamento desc
	Optional<ProyectoMejora> findTopByDepartamentoOrderByIdProyectoDesc(int departamento);
}
