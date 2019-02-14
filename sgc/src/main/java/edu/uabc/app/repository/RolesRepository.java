package edu.uabc.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.uabc.app.model.Rol;

@Repository
public interface RolesRepository extends JpaRepository<Rol, Integer>{

	// select * from Departamento
	List<Rol> findBy();
	
	// select * from Departamento
	Rol findByNombre(String nombre);
}
