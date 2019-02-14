package edu.uabc.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="recurso")
public class Recurso {

	@Id
	private int id_recurso;
	private String descripcion;
	private float costo;
	
	public Recurso() {
		
	}

	public int getId_recurso() {
		return id_recurso;
	}

	public void setId_recurso(int id_recurso) {
		this.id_recurso = id_recurso;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	@Override
	public String toString() {
		return "Recurso [id_recurso=" + id_recurso + ", descripcion=" + descripcion + ", costo=" + costo + "]";
	}
}
