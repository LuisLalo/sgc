package edu.uabc.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uabc.app.model.Menu;
import edu.uabc.app.repository.MenuRepository;

@Service
public class MenuServiceJPA implements IMenuService{

	@Autowired
	private MenuRepository menuRepo;
	
	@Override
	public List<Menu> buscarTodas() {
		List<Menu> lista = menuRepo.findAll();
		return lista;
	}

	@Override
	public List<Menu> buscarPorEstatus(int idEstatus) {
		List<Menu> lista = menuRepo.findByIdEstatus(idEstatus);
		return lista;
	}

	@Override
	public List<Menu> buscarPorSubmenu(int idSubmenu) {
		List<Menu> lista = menuRepo.findByIdSubmenu(idSubmenu);
		return lista;
	}

}
