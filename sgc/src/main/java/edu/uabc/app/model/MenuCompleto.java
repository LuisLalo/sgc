package edu.uabc.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="menu")
public class MenuCompleto {

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
	
	public MenuCompleto() {
		
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

	@Override
	public String toString() {
		return "MenuCompleto [idMenu=" + idMenu + ", nombre=" + nombre + ", liga=" + liga + ", idTipoVentana="
				+ idTipoVentana + ", idEstatus=" + idEstatus + ", idSubmenu=" + idSubmenu + ", relacion=" + relacion
				+ "]";
	}
}
