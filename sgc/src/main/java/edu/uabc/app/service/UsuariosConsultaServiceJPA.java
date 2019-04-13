package edu.uabc.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.uabc.app.model.Departamento;
import edu.uabc.app.model.Puesto;
import edu.uabc.app.model.Rol;
import edu.uabc.app.model.UsuarioConsulta;
import edu.uabc.app.repository.UsuariosConsultaRepository;

@Service
public class UsuariosConsultaServiceJPA implements IUsuariosConsultaService {

	@Autowired
	private UsuariosConsultaRepository usuariosRepo;
	
	@Override
	public Page<UsuarioConsulta> buscarTodas(Pageable page) {
		return usuariosRepo.findAll(page);
	}

	@Override
	public void insertar(UsuarioConsulta usuario) {
		usuariosRepo.save(usuario);
		
	}

	@Override
	public void eliminar(int num_empleado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UsuarioConsulta buscarPorId(int num_empleado) {
		Optional<UsuarioConsulta> optional = usuariosRepo.findById(num_empleado);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public List<UsuarioConsulta> buscarPorDepartamento(Departamento departamento) {
		List<UsuarioConsulta> lista = usuariosRepo.findByDepartamento(departamento);
		return lista;
	}

	@Override
	public UsuarioConsulta buscarPorPuesto(Puesto puesto) {
		UsuarioConsulta usuario = usuariosRepo.findByPuesto(puesto);
		return usuario;
	}

	@Override
	public UsuarioConsulta buscarPorRol(Rol rol) {
		UsuarioConsulta usuario = usuariosRepo.findByRol(rol);
		return usuario;
	}

	@Override
	public UsuarioConsulta buscarPorCorreo(String correo) {
		UsuarioConsulta usuario = usuariosRepo.findByCorreo(correo);
		return usuario;
	}

	@Override
	public List<UsuarioConsulta> buscaTodo() {
		List<UsuarioConsulta> usuario = usuariosRepo.findAll();
		return usuario;
	}
}
