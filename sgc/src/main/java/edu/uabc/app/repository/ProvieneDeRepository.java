package edu.uabc.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.uabc.app.model.ProvieneDe;

@Repository
public interface ProvieneDeRepository extends JpaRepository<ProvieneDe, Integer> {

	// select * from Proviene_De
	List<ProvieneDe>findBy();
}
