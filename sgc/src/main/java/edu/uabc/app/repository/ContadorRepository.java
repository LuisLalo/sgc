package edu.uabc.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.uabc.app.model.Contador;

@Repository
public interface ContadorRepository extends JpaRepository<Contador, Integer> {

	// select * from Contador
	List<Contador> findBy();
	// select from Contador where nombre = nombre
	List<Contador> findByNombre(String nombre);
}
