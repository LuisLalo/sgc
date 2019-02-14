package edu.uabc.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.uabc.app.model.Departamento;

@Repository
public interface DepartamentosRepository extends JpaRepository<Departamento, Integer> {

	// select * from Departamento
	List<Departamento> findBy();
	
	// select * from Departamento where nombre
	Departamento findByNombre(String nombre);
}
