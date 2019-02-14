package edu.uabc.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="frecuencia")
public class Frecuencia {

	@Id
	private int id_tipo_auditoria;
	private String nombre;
	private String descripcion;
	
	public Frecuencia() {
		
	}

	public int getId_tipo_auditoria() {
		return id_tipo_auditoria;
	}

	public void setId_tipo_auditoria(int id_tipo_auditoria) {
		this.id_tipo_auditoria = id_tipo_auditoria;
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
		return "Frecuencia [id_tipo_auditoria=" + id_tipo_auditoria + ", nombre=" + nombre + ", descripcion="
				+ descripcion + "]";
	}
}
