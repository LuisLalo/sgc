package edu.uabc.app.service;

import java.util.List;

import edu.uabc.app.model.Menu;

public interface IMenuService {

	List<Menu> buscarTodas();
	List<Menu> buscarPorEstatus(int idEstatus);
	List<Menu> buscarPorSubmenu(int idSubmenu);
	List<Menu> buscarPorEstatusAndTipoVentana(int idEstatus, int idTipoVentana);
}
