package edu.uabc.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="clasificador_actividad")
public class ClasificadorActividad {

	@Id
	private int id_clasificador_actividad;
	private String nombre;
	private String color;
	private String descripcion;
	
	public ClasificadorActividad() {
		
	}

	public int getId_clasificador_actividad() {
		return id_clasificador_actividad;
	}

	public void setId_clasificador_actividad(int id_clasificador_actividad) {
		this.id_clasificador_actividad = id_clasificador_actividad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "ClasificadorActividad [id_clasificador_actividad=" + id_clasificador_actividad + ", nombre=" + nombre
				+ ", color=" + color + ", descripcion=" + descripcion + "]";
	}
}
