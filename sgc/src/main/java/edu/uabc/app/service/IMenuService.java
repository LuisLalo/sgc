package edu.uabc.app.service;

import java.util.List;
import java.util.Optional;

import edu.uabc.app.model.Menu;

public interface IMenuService {

	List<Menu> buscarTodas();
	List<Menu> buscarPorEstatus(int idEstatus);
	List<Menu> buscarPorSubmenu(int idSubmenu);
	List<Menu> buscarPorEstatusAndIdTipoVentana(int idEstatus, int idTipoVentana);
	List<Menu> buscarPorEstatusAndIdTipoVentanaAndRelacion(int idEstatus, int idTipoVentana, int relacion);
	void insertar(Menu menu);
	Menu buscarPorId(int idMenu);
	List<Menu> buscarPorEstatusAndIdTipoVentanaOrderByOrden(int idEstatus, int idTipoVentana);
	List<Menu> buscarPorEstatusAndIdTipoVentanaAndRelacionOrderByOrden(int idEstatus, int idTipoVentana, int relacion);
	Menu buscarPorLiga(String liga);
	List<Menu> buscarPorIdTipoVentana(int idTipoVentana);
	
}
