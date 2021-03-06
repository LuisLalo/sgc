package edu.uabc.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.uabc.app.model.Evaluacion;

@Repository
public interface EvaluacionRepository extends JpaRepository<Evaluacion, Integer>{

}
