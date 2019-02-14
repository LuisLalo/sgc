package edu.uabc.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuario_actividad")
public class UsuarioActividad {

	@Id
	private int num_empleado;
	private int id_actividad;
	
	public UsuarioActividad() {
		
	}

	public int getNum_empleado() {
		return num_empleado;
	}

	public void setNum_empleado(int num_empleado) {
		this.num_empleado = num_empleado;
	}

	public int getId_actividad() {
		return id_actividad;
	}

	public void setId_actividad(int id_actividad) {
		this.id_actividad = id_actividad;
	}

	@Override
	public String toString() {
		return "UsuarioActividad [num_empleado=" + num_empleado + ", id_actividad=" + id_actividad + "]";
	}
}
