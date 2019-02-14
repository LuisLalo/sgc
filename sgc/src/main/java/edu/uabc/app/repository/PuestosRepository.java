package edu.uabc.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.uabc.app.model.Puesto;

@Repository
public interface PuestosRepository extends JpaRepository<Puesto, Integer> {

	// select * from Puesto
	List<Puesto> findBy();
	
	// select * from Puesto where nombre
	Puesto findByNombre(String nombre);
}
