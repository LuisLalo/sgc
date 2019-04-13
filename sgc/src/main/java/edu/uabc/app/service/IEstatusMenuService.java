package edu.uabc.app.service;

import java.util.List;

import edu.uabc.app.model.EstatusMenu;

public interface IEstatusMenuService {

	// select * from estatus_menu
	List<EstatusMenu> buscarTodas();
}
