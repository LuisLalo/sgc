package edu.uabc.app.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="formulario_reunion")
public class FormularioReunion {

	@Id
	private int id_reunion;
	private String asunto;
	private Date fecha_reunion;
	private String objetivo;
	
	public FormularioReunion() {
		
	}

	public int getId_reunion() {
		return id_reunion;
	}

	public void setId_reunion(int id_reunion) {
		this.id_reunion = id_reunion;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public Date getFecha_reunion() {
		return fecha_reunion;
	}

	public void setFecha_reunion(Date fecha_reunion) {
		this.fecha_reunion = fecha_reunion;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	@Override
	public String toString() {
		return "FormularioReunion [id_reunion=" + id_reunion + ", asunto=" + asunto + ", fecha_reunion=" + fecha_reunion
				+ ", objetivo=" + objetivo + "]";
	}
}
