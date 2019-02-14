package edu.uabc.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uabc.app.model.LineaAutorizacion;
import edu.uabc.app.repository.LineaAutorizacionRepository;

@Service
public class LineaAutorizacionServiceJPA implements ILineaAutorizacionService{
	
	@Autowired
	private LineaAutorizacionRepository lineaAutorizacionRepo;

	@Override
	public void insertar(LineaAutorizacion autorizacion) {
		lineaAutorizacionRepo.save(autorizacion);
	}

	@Override
	public List<LineaAutorizacion> buscarTodas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LineaAutorizacion buscarPorId(int id_autorizacion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(int id_accion) {
		lineaAutorizacionRepo.deleteById(id_accion);
		
	}
	
	public List<LineaAutorizacion> buscarPorDepartamento(int departamento){
		List<LineaAutorizacion> lista = lineaAutorizacionRepo.findByDepartamentoLike(departamento);
		return lista;
	}

	@Override
	public LineaAutorizacion buscarPorNumEmpleado(int num_empleado) {
		LineaAutorizacion lineaAutorizacion = lineaAutorizacionRepo.findByNumEmpleado(num_empleado);
		return lineaAutorizacion;
	}

	@Override
	public LineaAutorizacion buscarPorNumEmpleadoAndDepartamento(int numEmpleado, int departamento) {
		LineaAutorizacion listaLineaAutorizacion = lineaAutorizacionRepo.findByNumEmpleadoAndDepartamento(numEmpleado, departamento);
		return listaLineaAutorizacion;
	}

	@Override
	public void eliminarPorNumEmpleadoAndDepartamento(int numEmpleado, int departamento) {
		lineaAutorizacionRepo.deleteByNumEmpleadoAndDepartamento(numEmpleado, departamento);
	}

}
