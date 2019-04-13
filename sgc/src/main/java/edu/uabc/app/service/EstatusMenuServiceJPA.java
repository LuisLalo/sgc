package edu.uabc.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uabc.app.model.EstatusMenu;
import edu.uabc.app.repository.EstatusMenuRepository;

@Service
public class EstatusMenuServiceJPA implements IEstatusMenuService {

	@Autowired
	private EstatusMenuRepository estatusMenuRepo;
	
	@Override
	public List<EstatusMenu> buscarTodas() {
		List<EstatusMenu> lista = estatusMenuRepo.findAll();
		return lista;
	}

}
