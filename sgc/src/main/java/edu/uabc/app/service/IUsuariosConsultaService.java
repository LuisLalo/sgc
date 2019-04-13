package edu.uabc.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import edu.uabc.app.model.Departamento;
import edu.uabc.app.model.Puesto;
import edu.uabc.app.model.Rol;
import edu.uabc.app.model.UsuarioConsulta;

public interface IUsuariosConsultaService {
	void insertar(UsuarioConsulta usuario);
	Page<UsuarioConsulta> buscarTodas(Pageable page);
	UsuarioConsulta buscarPorId(int num_empleado);
	void eliminar(int num_empleado);
	List<UsuarioConsulta> buscarPorDepartamento(Departamento departamento);
	UsuarioConsulta buscarPorPuesto(Puesto puesto);
	UsuarioConsulta buscarPorRol(Rol rol);
	UsuarioConsulta buscarPorCorreo(String correo);
	List<UsuarioConsulta> buscaTodo();
}
