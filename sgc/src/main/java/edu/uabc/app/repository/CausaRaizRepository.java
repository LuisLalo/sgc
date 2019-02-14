package edu.uabc.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.uabc.app.model.CausaRaiz;

@Repository
public interface CausaRaizRepository extends JpaRepository<CausaRaiz, Integer> {

	// select * from Causa_Raiz
	List<CausaRaiz>findBy();
}
