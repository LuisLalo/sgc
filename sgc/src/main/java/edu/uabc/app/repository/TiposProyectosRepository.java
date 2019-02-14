package edu.uabc.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.uabc.app.model.TipoProyecto;

@Repository
public interface TiposProyectosRepository extends JpaRepository<TipoProyecto, Integer> {

}
