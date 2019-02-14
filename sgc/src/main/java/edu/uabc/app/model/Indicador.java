package edu.uabc.app.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="indicador")
public class Indicador {

	@Id
	private int id_indicador;
	private String nombre;
	private String descripcion;
	private String objetivo;
	private String meta;
	private int id_frecuencia;
	private String tipo_grafica;
	private String informacion_adicional;
	private int id_departamento;
	private Date fecha_creacion;
	private int estatus;
	
	public Indicador() {
		
	}

	public int getId_indicador() {
		return id_indicador;
	}

	public void setId_indicador(int id_indicador) {
		this.id_indicador = id_indicador;
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

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public String getMeta() {
		return meta;
	}

	public void setMeta(String meta) {
		this.meta = meta;
	}

	public int getId_frecuencia() {
		return id_frecuencia;
	}

	public void setId_frecuencia(int id_frecuencia) {
		this.id_frecuencia = id_frecuencia;
	}

	public String getTipo_grafica() {
		return tipo_grafica;
	}

	public void setTipo_grafica(String tipo_grafica) {
		this.tipo_grafica = tipo_grafica;
	}

	public String getInformacion_adicional() {
		return informacion_adicional;
	}

	public void setInformacion_adicional(String informacion_adicional) {
		this.informacion_adicional = informacion_adicional;
	}

	public int getId_departamento() {
		return id_departamento;
	}

	public void setId_departamento(int id_departamento) {
		this.id_departamento = id_departamento;
	}

	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	@Override
	public String toString() {
		return "Indicador [id_indicador=" + id_indicador + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", objetivo=" + objetivo + ", meta=" + meta + ", id_frecuencia=" + id_frecuencia + ", tipo_grafica="
				+ tipo_grafica + ", informacion_adicional=" + informacion_adicional + ", id_departamento="
				+ id_departamento + ", fecha_creacion=" + fecha_creacion + ", estatus=" + estatus + "]";
	}
}
