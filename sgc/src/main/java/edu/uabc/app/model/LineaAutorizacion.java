package edu.uabc.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="linea_autorizacion")
public class LineaAutorizacion {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_linea_autorizacion;
	@Column(name="num_empleado")
	private int numEmpleado;
	@Column(name="id_departamento")
	private int departamento;
	private int nivel;
	
	public LineaAutorizacion() {
		
	}

	public int getId_linea_autorizacion() {
		return id_linea_autorizacion;
	}

	public void setId_linea_autorizacion(int id_linea_autorizacion) {
		this.id_linea_autorizacion = id_linea_autorizacion;
	}

	public int getNumEmpleado() {
		return numEmpleado;
	}

	public void setNumEmpleado(int numEmpleado) {
		this.numEmpleado = numEmpleado;
	}

	public int getDepartamento() {
		return departamento;
	}

	public void setDepartamento(int departamento) {
		this.departamento = departamento;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	@Override
	public String toString() {
		return "LineaAutorizacion [id_linea_autorizacion=" + id_linea_autorizacion + ", numEmpleado=" + numEmpleado
				+ ", departamento=" + departamento + ", nivel=" + nivel + "]";
	}
}
