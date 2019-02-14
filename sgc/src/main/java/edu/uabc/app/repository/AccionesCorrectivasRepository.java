package edu.uabc.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.uabc.app.model.AccionCorrectiva;

@Repository
public interface AccionesCorrectivasRepository extends JpaRepository<AccionCorrectiva, Integer> {

	// select * from Accion_Correctiva
	List<AccionCorrectiva>findBy();
	
	// select * from Accion_Correctiva where departamento
	List<AccionCorrectiva>findByDepartamento(int departamento);
	
	// select top from Accion_Correctiva order by id_accion and departamento desc
	Optional<AccionCorrectiva> findTopByDepartamentoOrderByIdAccionDesc(int departamento);
}
