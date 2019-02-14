package edu.uabc.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="estatus_usuario")
public class EstatusUsuario {

	@Id
	@Column(name="id_estatus_usuario")
	private int idEstatusUsuario;
	private String nombre;
	private String descripcion;
	
	public EstatusUsuario() {
		
	}

	public int getIdEstatusUsuario() {
		return idEstatusUsuario;
	}
	
	public void setIdEstatusUsuario(int idEstatusUsuario) {
		this.idEstatusUsuario = idEstatusUsuario;
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
		return "EstatusUsuario [idEstatusUsuario=" + idEstatusUsuario + ", nombre=" + nombre + ", descripcion="
				+ descripcion + "]";
	}
}
