package edu.uabc.app.service;

import java.util.List;

import edu.uabc.app.model.LineaAutorizacion;

public interface ILineaAutorizacionService {

	void insertar(LineaAutorizacion autorizacion);//Se tendr� que modificar por un query que busque el n�mero de empleado y el id_departamento
	List<LineaAutorizacion> buscarTodas();
	LineaAutorizacion buscarPorId(int id_autorizacion);//Se tendr� que modificar por un query que busque el n�mero de empleado y el id_departamento
	void eliminar (int id_accion);//Se tendr� que modificar por un query que busque el n�mero de empleado y el id_departamento
	List<LineaAutorizacion> buscarPorDepartamento(int departamento);
	LineaAutorizacion buscarPorNumEmpleado(int num_empleado);
	LineaAutorizacion buscarPorNumEmpleadoAndDepartamento(int numEmpleado, int departamento);
	void eliminarPorNumEmpleadoAndDepartamento(int numEmpleado, int departamento);
}
