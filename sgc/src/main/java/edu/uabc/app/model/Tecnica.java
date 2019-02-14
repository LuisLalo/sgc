package edu.uabc.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tecnica")
public class Tecnica {

	@Id
	private int id_tecnica;
	private String descripcion;
	private String nombre;
	
	public Tecnica() {
		
	}

	public int getId_tecnica() {
		return id_tecnica;
	}

	public void setId_tecnica(int id_tecnica) {
		this.id_tecnica = id_tecnica;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Tecnica [id_tecnica=" + id_tecnica + ", descripcion=" + descripcion + ", nombre=" + nombre + "]";
	}
}
