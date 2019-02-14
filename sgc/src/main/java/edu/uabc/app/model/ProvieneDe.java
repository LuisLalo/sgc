package edu.uabc.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="proviene_de")
public class ProvieneDe {

	@Id
	@Column(name="id_proviene_de")
	private int idProvieneDe;
	private String nombre;
	private String descripcion;
	
	public ProvieneDe(){
		
	}

	public int getIdProvieneDe() {
		return idProvieneDe;
	}

	public void setIdProvieneDe(int idProvieneDe) {
		this.idProvieneDe = idProvieneDe;
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
		return "ProvieneDe [idProvieneDe=" + idProvieneDe + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}
}
