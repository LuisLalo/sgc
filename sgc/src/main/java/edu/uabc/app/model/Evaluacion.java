package edu.uabc.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="evaluacion")
public class Evaluacion {

	@Id
	@Column(name="id_evaluacion")
	private int idEvaluacion;
	
	private String nombre;
	
	private String descripcion;
	
	public Evaluacion() {
		
	}

	public int getIdEvaluacion() {
		return idEvaluacion;
	}

	public void setIdEvaluacion(int idEvaluacion) {
		this.idEvaluacion = idEvaluacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Evaluacion [idEvaluacion=" + idEvaluacion + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}
}
