package edu.uabc.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import edu.uabc.app.util.Utileria;

@Entity
@Table(name="proyecto_mejora")
public class ProyectoMejora {

	@Id
	@Column(name="id_proyecto")
	private int idProyecto;
	
	@Column(name="numero_proyecto")
	private int numeroProyecto;
	
	private String nombre;
	
	@Column(name="area_aplicacion")
	private int areaAplicacion;
	
	@Column(name="proviene_de")
	private int provieneDe;
	
	@Column(name="tipo_proyecto")
	private int tipoProyecto;
	
	@Column(name="situacion_actual")
	private String situacionActual;
	
	@Column(name="situacion_deseada")
	private String situacionDeseada;
	
	@Column(name="fecha_creacion")
	private Date fechaCreacion = Utileria.generarFecha();
	
	@Column(name="fecha_terminacion")
	private Date fechaTerminacion = Utileria.generarFecha();
	
	private int departamento;
	
	@Column(name="num_empleado")
	private int numEmpleado;
	
	private String tecnica;
	
	private String recurso;
	
	private String resultado;
	
	public ProyectoMejora() {
		
	}

	public int getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(int idProyecto) {
		this.idProyecto = idProyecto;
	}

	public int getNumeroProyecto() {
		return numeroProyecto;
	}

	public void setNumeroProyecto(int numeroProyecto) {
		this.numeroProyecto = numeroProyecto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getAreaAplicacion() {
		return areaAplicacion;
	}

	public void setAreaAplicacion(int areaAplicacion) {
		this.areaAplicacion = areaAplicacion;
	}

	public int getProvieneDe() {
		return provieneDe;
	}

	public void setProvieneDe(int provieneDe) {
		this.provieneDe = provieneDe;
	}

	public int getTipoProyecto() {
		return tipoProyecto;
	}

	public void setTipoProyecto(int tipoProyecto) {
		this.tipoProyecto = tipoProyecto;
	}

	public String getSituacionActual() {
		return situacionActual;
	}

	public void setSituacionActual(String situacionActual) {
		this.situacionActual = situacionActual;
	}

	public String getSituacionDeseada() {
		return situacionDeseada;
	}

	public void setSituacionDeseada(String situacionDeseada) {
		this.situacionDeseada = situacionDeseada;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaTerminacion() {
		return fechaTerminacion;
	}

	public void setFechaTerminacion(Date fechaTerminacion) {
		this.fechaTerminacion = fechaTerminacion;
	}

	public int getDepartamento() {
		return departamento;
	}

	public void setDepartamento(int departamento) {
		this.departamento = departamento;
	}

	public int getNumEmpleado() {
		return numEmpleado;
	}

	public void setNumEmpleado(int numEmpleado) {
		this.numEmpleado = numEmpleado;
	}

	public String getTecnica() {
		return tecnica;
	}

	public void setTecnica(String tecnica) {
		this.tecnica = tecnica;
	}

	public String getRecurso() {
		return recurso;
	}

	public void setRecurso(String recurso) {
		this.recurso = recurso;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	@Override
	public String toString() {
		return "ProyectoMejora [idProyecto=" + idProyecto + ", numeroProyecto=" + numeroProyecto + ", nombre=" + nombre
				+ ", areaAplicacion=" + areaAplicacion + ", provieneDe=" + provieneDe + ", tipoProyecto=" + tipoProyecto
				+ ", situacionActual=" + situacionActual + ", situacionDeseada=" + situacionDeseada + ", fechaCreacion="
				+ fechaCreacion + ", fechaTerminacion=" + fechaTerminacion + ", departamento=" + departamento
				+ ", numEmpleado=" + numEmpleado + ", tecnica=" + tecnica + ", recurso=" + recurso + ", resultado="
				+ resultado + "]";
	}
}
