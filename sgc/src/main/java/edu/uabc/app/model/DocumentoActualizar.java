package edu.uabc.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import edu.uabc.app.util.Utileria;

@Entity
@Table(name="documento_prov")
public class DocumentoActualizar {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_documento")
	private int idDocumento;
	private String nombre;
	private String ruta;
	@Column(name="id_tipo_archivo")
	private int idTipoArchivo;
	@Column(name="id_tipo_documento")
	private int idTipoDocumento;
	@Column(name="fecha_creacion")
	private Date fechaCreacion = Utileria.generarFecha();
	@Column(name="id_estatus")
	private int estatus;
	private String descripcion;
	@Column(name="id_clasificador_documento")
	private int idClasificadorDocumento;
	@Column(name="orden_documento")
	private int ordenDocumento;
	@Column(name="id_departamento")
	private int idDepartamento;
	
	public DocumentoActualizar() {
		
	}

	public int getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(int idDocumento) {
		this.idDocumento = idDocumento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public int getIdTipoArchivo() {
		return idTipoArchivo;
	}

	public void setIdTipoArchivo(int idTipoArchivo) {
		this.idTipoArchivo = idTipoArchivo;
	}

	public int getIdTipoDocumento() {
		return idTipoDocumento;
	}

	public void setIdTipoDocumento(int idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getIdClasificadorDocumento() {
		return idClasificadorDocumento;
	}

	public void setIdClasificadorDocumento(int idClasificadorDocumento) {
		this.idClasificadorDocumento = idClasificadorDocumento;
	}

	public int getOrdenDocumento() {
		return ordenDocumento;
	}

	public void setOrdenDocumento(int ordenDocumento) {
		this.ordenDocumento = ordenDocumento;
	}

	public int getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(int idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	@Override
	public String toString() {
		return "DocumentoActualizar [idDocumento=" + idDocumento + ", nombre=" + nombre + ", ruta=" + ruta
				+ ", idTipoArchivo=" + idTipoArchivo + ", idTipoDocumento=" + idTipoDocumento + ", fechaCreacion="
				+ fechaCreacion + ", estatus=" + estatus + ", descripcion=" + descripcion + ", idClasificadorDocumento="
				+ idClasificadorDocumento + ", ordenDocumento=" + ordenDocumento + ", idDepartamento=" + idDepartamento
				+ "]";
	}
}
