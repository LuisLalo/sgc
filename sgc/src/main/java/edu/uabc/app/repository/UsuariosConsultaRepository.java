package edu.uabc.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.uabc.app.model.Departamento;
import edu.uabc.app.model.Puesto;
import edu.uabc.app.model.Rol;
import edu.uabc.app.model.UsuarioConsulta;

@Repository
public interface UsuariosConsultaRepository extends JpaRepository<UsuarioConsulta, Integer> {

	// select * from usuario
	List<UsuarioConsulta> findBy();
	
	// select * from usuario where id_departamento
	List<UsuarioConsulta> findByDepartamento(Departamento departamento);
	//@Query("select * from usuario where departamento = ?1")
	//List<UsuarioConsulta> buscarPorDepartamento(Departamento departamento);
	
	// select * from usuario where id_puesto
	UsuarioConsulta findByPuesto(Puesto puesto);
	
	// select * from usuario where id_rol
	UsuarioConsulta findByRol(Rol rol);
	
	// select * from usuario where correo
	UsuarioConsulta findByCorreo(String correo);
	
	//Page<UsuarioConsulta> findAll(Pageable page);
}
