package edu.uabc.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.uabc.app.model.UsuarioActualizar;

@Repository
public interface UsuariosActualizarRepository extends JpaRepository<UsuarioActualizar, Integer> {
	
	// select * from usuario
	List<UsuarioActualizar> findBy();
}
