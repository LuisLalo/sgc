package edu.uabc.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipo_auditoria")
public class TipoAuditoria {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_tipo_auditoria")
	private int idTipoAuditoria;
	private String nombre;
	private String descripcion;
	
	public TipoAuditoria() {
		
	}

	public int getIdTipoAuditoria() {
		return idTipoAuditoria;
	}

	public void setIdTipoAuditoria(int idTipoAuditoria) {
		this.idTipoAuditoria = idTipoAuditoria;
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
		return "TipoAuditoria [idTipoAuditoria=" + idTipoAuditoria + ", nombre=" + nombre + ", descripcion="
				+ descripcion + "]";
	}
}
