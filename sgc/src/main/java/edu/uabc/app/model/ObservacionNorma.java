package edu.uabc.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="observacion_norma")
public class ObservacionNorma {
	
	@Id
	@Column(name="id_observacion_norma")
	private int idObservacionNorma;
	private String nombre;
	private String descripcion;
	
	public ObservacionNorma() {
		
	}

	public int getIdObservacionNorma() {
		return idObservacionNorma;
	}

	public void setIdObservacionNorma(int idObservacionNorma) {
		this.idObservacionNorma = idObservacionNorma;
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
		return "ObservacionNorma [idObservacionNorma=" + idObservacionNorma + ", nombre=" + nombre + ", descripcion="
				+ descripcion + "]";
	}
}
