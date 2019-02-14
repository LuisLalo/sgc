package edu.uabc.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.uabc.app.model.TipoAuditoria;

@Repository
public interface TiposAuditoriaRepository extends JpaRepository<TipoAuditoria, Integer> {

	// select * TipoAuditoria
	List<TipoAuditoria> findBy();
}
