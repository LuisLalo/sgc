package edu.uabc.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="actividad_accion")
public class ActividadAccion {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_actividad")
	private int idActividad;
	
	@Column(name="fecha_estimada")
	private Date fechaEstimada;
	
	@Column(name="fecha_real")
	private Date fechaReal;
	
	@Column(name="porcentaje_avance")
	private int porcentajeAvance;
	
	private String descripcion;
	
	//@ManyToOne
	//@JoinColumn(name="id_proyecto")
	//private AccionCorrectiva accionActividad;
	@Column(name="id_proyecto")
	private int idProyecto;
	
	@Column(name="num_empleado")
	private int responsable;
	
	@Column(name="id_accion")
	private int idAccion;
	
	public ActividadAccion() {
		
	}

	public int getIdActividad() {
		return idActividad;
	}

	public void setIdActividad(int idActividad) {
		this.idActividad = idActividad;
	}

	public Date getFechaEstimada() {
		return fechaEstimada;
	}

	public void setFechaEstimada(Date fechaEstimada) {
		this.fechaEstimada = fechaEstimada;
	}

	public Date getFechaReal() {
		return fechaReal;
	}

	public void setFechaReal(Date fechaReal) {
		this.fechaReal = fechaReal;
	}

	public int getPorcentajeAvance() {
		return porcentajeAvance;
	}

	public void setPorcentajeAvance(int porcentajeAvance) {
		this.porcentajeAvance = porcentajeAvance;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(int idProyecto) {
		this.idProyecto = idProyecto;
	}

	public int getResponsable() {
		return responsable;
	}

	public void setResponsable(int responsable) {
		this.responsable = responsable;
	}

	public int getIdAccion() {
		return idAccion;
	}

	public void setIdAccion(int idAccion) {
		this.idAccion = idAccion;
	}

	@Override
	public String toString() {
		return "ActividadAccion [idActividad=" + idActividad + ", fechaEstimada=" + fechaEstimada + ", fechaReal="
				+ fechaReal + ", porcentajeAvance=" + porcentajeAvance + ", descripcion=" + descripcion
				+ ", idProyecto=" + idProyecto + ", responsable=" + responsable + ", idAccion=" + idAccion + "]";
	}
}
