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
	
	public Menu() {
		
	}

	public int getId_menu() {
		return id_menu;
	}

	public void setId_menu(int id_menu) {
		this.id_menu = id_menu;
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

	public int getId_tipo_ventana() {
		return id_tipo_ventana;
	}

	public void setId_tipo_ventana(int id_tipo_ventana) {
		this.id_tipo_ventana = id_tipo_ventana;
	}

	public int getId_estatus() {
		return id_estatus;
	}

	public void setId_estatus(int id_estatus) {
		this.id_estatus = id_estatus;
	}

	public int getId_submenu() {
		return id_submenu;
	}

	public void setId_submenu(int id_submenu) {
		this.id_submenu = id_submenu;
	}

	@Override
	public String toString() {
		return "Menu [id_menu=" + id_menu + ", nombre=" + nombre + ", liga=" + liga + ", id_tipo_ventana="
				+ id_tipo_ventana + ", id_estatus=" + id_estatus + ", id_submenu=" + id_submenu + "]";
	}
}
