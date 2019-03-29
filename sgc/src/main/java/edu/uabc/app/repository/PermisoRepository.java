package edu.uabc.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.uabc.app.model.Permiso;

@Repository
public interface PermisoRepository extends JpaRepository<Permiso, Integer>{

	// select * from permiso
	List<Permiso> findBy();
	
	// select * from permiso where num_empleado
	List<Permiso> findByNumEmpleado(int numEmpleado);
	
	// select * from permiso where idPermiso
	Permiso findByIdPermisoAndNumEmpleado(int idPermiso, int numEmpleado);
}
