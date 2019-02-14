package edu.uabc.app.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import edu.uabc.app.util.Utileria;
import edu.uabc.app.model.TipoDocumento;
import edu.uabc.app.model.Departamento;

@Entity
@Table(name="documento_prov")
public class DocumentoConsulta {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_documento")
	private int idDocumento;
	private String nombre;
	private String ruta;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_tipo_archivo")
	private TipoArchivo tipoArchivo;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_tipo_documento")
	private TipoDocumento tipoDocumento;
	private Date fecha_creacion = Utileria.generarFecha();
	@Column(name="id_estatus")
	private int estatus;
	private String descripcion;
	@Column(name="id_clasificador_documento")
	private int clasificadorDocumento;
	@Column(name="orden_documento")
	private int ordenDocumento;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_departamento")
	private Departamento departamento;
	
	public DocumentoConsulta() {
		
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

	public TipoArchivo getTipoArchivo() {
		return tipoArchivo;
	}

	public void setTipoArchivo(TipoArchivo tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getClasificadorDocumento() {
		return clasificadorDocumento;
	}

	public void setClasificadorDocumento(int clasificadorDocumento) {
		this.clasificadorDocumento = clasificadorDocumento;
	}

	public int getOrdenDocumento() {
		return ordenDocumento;
	}

	public void setOrdenDocumento(int ordenDocumento) {
		this.ordenDocumento = ordenDocumento;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	@Override
	public String toString() {
		return "DocumentoConsulta [idDocumento=" + idDocumento + ", nombre=" + nombre + ", ruta=" + ruta
				+ ", tipoArchivo=" + tipoArchivo + ", tipoDocumento=" + tipoDocumento + ", fecha_creacion="
				+ fecha_creacion + ", estatus=" + estatus + ", descripcion=" + descripcion + ", clasificadorDocumento="
				+ clasificadorDocumento + ", ordenDocumento=" + ordenDocumento + ", departamento=" + departamento + "]";
	}
}