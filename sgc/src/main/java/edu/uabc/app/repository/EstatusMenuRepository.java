package edu.uabc.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.uabc.app.model.EstatusMenu;

@Repository
public interface EstatusMenuRepository extends JpaRepository<EstatusMenu, Integer> {

}
