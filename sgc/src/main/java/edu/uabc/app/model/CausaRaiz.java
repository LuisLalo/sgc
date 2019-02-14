package edu.uabc.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="causa_raiz")
public class CausaRaiz {

	@Id
	@Column(name="id_causa_raiz")
	private int idCausaRaiz;
	private String nombre;
	private String descripcion;
	
	public CausaRaiz() {
		
	}

	public int getIdCausaRaiz() {
		return idCausaRaiz;
	}

	public void setIdCausaRaiz(int idCausaRaiz) {
		this.idCausaRaiz = idCausaRaiz;
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
		return "CausaRaiz [idCausaRaiz=" + idCausaRaiz + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}
}
