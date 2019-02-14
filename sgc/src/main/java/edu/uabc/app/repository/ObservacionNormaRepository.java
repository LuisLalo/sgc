package edu.uabc.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.uabc.app.model.ObservacionNorma;

@Repository
public interface ObservacionNormaRepository extends JpaRepository<ObservacionNorma, Integer> {

}
