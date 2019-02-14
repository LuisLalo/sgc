package edu.uabc.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.uabc.app.model.EstatusUsuario;

@Repository
public interface EstatusUsuarioRepository extends JpaRepository<EstatusUsuario, Integer>{

	// select * from estatus_usuario
	List<EstatusUsuario> findBy();
}
