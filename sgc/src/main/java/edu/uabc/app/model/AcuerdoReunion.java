package edu.uabc.app.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="acuerdo_reunion")
public class AcuerdoReunion {

	@Id
	private int id_acuerdo;
	private int id_reunion;
	private String descripcion;
	private Date fecha_vencimiento;
	private int id_usuario_responsable;
	
	public AcuerdoReunion() {
		
	}

	public int getId_acuerdo() {
		return id_acuerdo;
	}

	public void setId_acuerdo(int id_acuerdo) {
		this.id_acuerdo = id_acuerdo;
	}

	public int getId_reunion() {
		return id_reunion;
	}

	public void setId_reunion(int id_reunion) {
		this.id_reunion = id_reunion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha_vencimiento() {
		return fecha_vencimiento;
	}

	public void setFecha_vencimiento(Date fecha_vencimiento) {
		this.fecha_vencimiento = fecha_vencimiento;
	}

	public int getId_usuario_responsable() {
		return id_usuario_responsable;
	}

	public void setId_usuario_responsable(int id_usuario_responsable) {
		this.id_usuario_responsable = id_usuario_responsable;
	}

	@Override
	public String toString() {
		return "AcuerdoReunion [id_acuerdo=" + id_acuerdo + ", id_reunion=" + id_reunion + ", descripcion="
				+ descripcion + ", fecha_vencimiento=" + fecha_vencimiento + ", id_usuario_responsable="
				+ id_usuario_responsable + "]";
	}
}
