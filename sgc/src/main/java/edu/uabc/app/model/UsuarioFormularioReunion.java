package edu.uabc.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuario_formulario_reunion")
public class UsuarioFormularioReunion {

	@Id
	private int num_empleado;
	private int id_reunion;
	
	private UsuarioFormularioReunion() {
		
	}

	public int getNum_empleado() {
		return num_empleado;
	}

	public void setNum_empleado(int num_empleado) {
		this.num_empleado = num_empleado;
	}

	public int getId_reunion() {
		return id_reunion;
	}

	public void setId_reunion(int id_reunion) {
		this.id_reunion = id_reunion;
	}

	@Override
	public String toString() {
		return "UsuarioFormularioReunion [num_empleado=" + num_empleado + ", id_reunion=" + id_reunion + "]";
	}
}
