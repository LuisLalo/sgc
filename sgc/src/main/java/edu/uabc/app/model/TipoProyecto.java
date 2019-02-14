package edu.uabc.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipo_proyecto")
public class TipoProyecto {

	@Id
	@Column(name="id_tipo_proyecto")
	private int idTipoProyecto;
	private String nombre;
	private String descripcion;
	
	public TipoProyecto() {
		
	}

	public int getIdTipoProyecto() {
		return idTipoProyecto;
	}

	public void setIdTipoProyecto(int idTipoProyecto) {
		this.idTipoProyecto = idTipoProyecto;
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
		return "TipoProyecto [idTipoProyecto=" + idTipoProyecto + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ "]";
	}
}
