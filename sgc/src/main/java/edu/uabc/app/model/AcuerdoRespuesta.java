package edu.uabc.app.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuario_respuesta")
public class AcuerdoRespuesta {

	@Id
	private int id_respuesta;
	private int id_acuerdo;
	private String descripcion;
	private Date fecha_respuesta;
	private int id_usuario;
	
	public AcuerdoRespuesta() {
		
	}

	public int getId_respuesta() {
		return id_respuesta;
	}

	public void setId_respuesta(int id_respuesta) {
		this.id_respuesta = id_respuesta;
	}

	public int getId_acuerdo() {
		return id_acuerdo;
	}

	public void setId_acuerdo(int id_acuerdo) {
		this.id_acuerdo = id_acuerdo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha_respuesta() {
		return fecha_respuesta;
	}

	public void setFecha_respuesta(Date fecha_respuesta) {
		this.fecha_respuesta = fecha_respuesta;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	@Override
	public String toString() {
		return "AcuerdoRespuesta [id_respuesta=" + id_respuesta + ", id_acuerdo=" + id_acuerdo + ", descripcion="
				+ descripcion + ", fecha_respuesta=" + fecha_respuesta + ", id_usuario=" + id_usuario + "]";
	}
}
