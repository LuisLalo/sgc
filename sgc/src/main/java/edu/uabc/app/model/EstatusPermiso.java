package edu.uabc.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="estatus_permiso")
public class EstatusPermiso {

	@Id
	@Column(name="id_estatus")
	private int idEstatus;
	private String nombre;
	private String descripcion;
	
	public EstatusPermiso() {
		
	}

	public int getIdEstatus() {
		return idEstatus;
	}

	public void setIdEstatus(int idEstatus) {
		this.idEstatus = idEstatus;
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
		return "EstatusPermiso [idEstatus=" + idEstatus + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}
}
