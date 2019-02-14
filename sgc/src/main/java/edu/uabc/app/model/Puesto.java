package edu.uabc.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="puesto")
public class Puesto {
	
	@Id
	private int id_puesto;
	private String nombre;
	private String descripcion;
	
	public Puesto() {
		
	}
	
	public int getId_puesto() {
		return id_puesto;
	}
	
	public void setId_puesto(int id_puesto) {
		this.id_puesto = id_puesto;
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
		return "Puesto [id_puesto=" + id_puesto + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}
}
