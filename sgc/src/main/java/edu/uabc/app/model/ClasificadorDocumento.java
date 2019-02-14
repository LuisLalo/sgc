package edu.uabc.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="clasificador_documento_prov")
public class ClasificadorDocumento {

	@Id
	private int id_clasificador_documento;
	private String nombre;
	private String descripcion;
	
	public ClasificadorDocumento() {
		
	}

	public int getId_clasificador_documento() {
		return id_clasificador_documento;
	}

	public void setId_clasificador_documento(int id_clasificador_documento) {
		this.id_clasificador_documento = id_clasificador_documento;
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
		return "ClasificadorDocumento [id_clasificador_documento=" + id_clasificador_documento + ", nombre=" + nombre
				+ ", descripcion=" + descripcion + "]";
	}
}
