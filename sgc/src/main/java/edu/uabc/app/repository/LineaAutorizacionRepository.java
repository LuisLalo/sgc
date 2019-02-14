package edu.uabc.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.uabc.app.model.LineaAutorizacion;

@Repository
public interface LineaAutorizacionRepository extends JpaRepository<LineaAutorizacion, Integer> {

	// select * from linea_autorizacion where id_departamento like
	List<LineaAutorizacion>	findByDepartamentoLike(int departamento);
	
	// select * from linea_autorizacion where num_empleado
	LineaAutorizacion findByNumEmpleado(int num_empleado);
	
	// select * from linea_autorizacion where numEmpleado and departamento
	LineaAutorizacion findByNumEmpleadoAndDepartamento(int numEmpleado, int departamento);
	
	// delete from linea_autorizacion where numEmpleado and idDepartamento
	void deleteByNumEmpleadoAndDepartamento(int numEmpleado, int departamento);
	
	// delete from linea_autorizacion where numEmpleado and idDepartamento
	void deleteByNumEmpleadoAndDepartamentoAndNivel(int numEmpleado, int departamento, int nivel);
}
