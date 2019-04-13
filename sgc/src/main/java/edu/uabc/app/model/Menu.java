package edu.uabc.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="menu")
public class Menu {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_menu")
	private int idMenu;
	private String nombre;
	private String liga;
	@Column(name="id_tipo_ventana")
	private int idTipoVentana;
	@Column(name="id_estatus")
	private int idEstatus;
	@Column(name="id_submenu")
	private int idSubmenu;
	private int relacion;
	private int orden;
	@Column(name="id_clasificador_documento")
	private int idClasificadorDocumento;
	
	public Menu() {
		
	}

	public int getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(int idMenu) {
		this.idMenu = idMenu;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLiga() {
		return liga;
	}

	public void setLiga(String liga) {
		this.liga = liga;
	}

	public int getIdTipoVentana() {
		return idTipoVentana;
	}

	public void setIdTipoVentana(int idTipoVentana) {
		this.idTipoVentana = idTipoVentana;
	}

	public int getIdEstatus() {
		return idEstatus;
	}

	public void setIdEstatus(int idEstatus) {
		this.idEstatus = idEstatus;
	}

	public int getIdSubmenu() {
		return idSubmenu;
	}

	public void setIdSubmenu(int idSubmenu) {
		this.idSubmenu = idSubmenu;
	}

	public int getRelacion() {
		return relacion;
	}

	public void setRelacion(int relacion) {
		this.relacion = relacion;
	}
	
	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public int getIdClasificadorDocumento() {
		return idClasificadorDocumento;
	}

	public void setIdClasificadorDocumento(int idClasificadorDocumento) {
		this.idClasificadorDocumento = idClasificadorDocumento;
	}

	@Override
	public String toString() {
		return "Menu [idMenu=" + idMenu + ", nombre=" + nombre + ", liga=" + liga + ", idTipoVentana=" + idTipoVentana
				+ ", idEstatus=" + idEstatus + ", idSubmenu=" + idSubmenu + ", relacion=" + relacion + ", orden="
				+ orden + ", idClasificadorDocumento=" + idClasificadorDocumento + "]";
	}
}
