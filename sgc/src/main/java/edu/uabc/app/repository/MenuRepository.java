package edu.uabc.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.uabc.app.model.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

	// select * from Menu
	List<Menu> findBy();
	
	// select * from Menu where id_estatus
	List<Menu> findByIdEstatus(int idEstatus);
	
	// select * from Menu where id_submenu
	List<Menu> findByIdSubmenu(int idSubmenu);
	
	// select * from Menu where id_estatus and id_tipo_ventana
	List<Menu> findByIdEstatusAndIdTipoVentana(int idEstatus, int idTipoVentana);
}