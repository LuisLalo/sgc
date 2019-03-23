package edu.uabc.app.service;

import java.util.List;
import java.util.Optional;

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

	@Override
	public List<Menu> buscarPorEstatusAndIdTipoVentana(int idEstatus, int idTipoVentana) {
		List<Menu> lista = menuRepo.findByIdEstatusAndIdTipoVentana(idEstatus, idTipoVentana);
		return lista;
	}

	@Override
	public List<Menu> buscarPorEstatusAndIdTipoVentanaAndRelacion(int idEstatus, int idTipoVentana, int relacion) {
		List <Menu> lista = menuRepo.findByIdEstatusAndIdTipoVentanaAndRelacion(idEstatus, idTipoVentana, relacion);
		return lista;
	}

	@Override
	public void insertar(Menu menu) {
		menuRepo.save(menu);
	}

	@Override
	public Menu buscarPorId(int idMenu) {
		Optional<Menu> optional = menuRepo.findById(idMenu);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public List<Menu> buscarPorEstatusAndIdTipoVentanaAndRelacionOrderByOrden(int idEstatus, int idTipoVentana,
			int relacion) {
		List<Menu> lista = menuRepo.findByIdEstatusAndIdTipoVentanaAndRelacionOrderByOrden(idEstatus, idTipoVentana, relacion);
		return lista;
	}

	@Override
	public List<Menu> buscarPorEstatusAndIdTipoVentanaOrderByOrden(int idEstatus, int idTipoVentana) {
		List <Menu> lista = menuRepo.findByIdEstatusAndIdTipoVentanaOrderByOrden(idEstatus, idTipoVentana);
		return lista;
	}
}
