package edu.uabc.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="permiso")
public class PermisoActualizar {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_permiso")
	private int idPermiso;
	@Column(name="num_empleado")
	private int numEmpleado;
	@Column(name="id_menu")
	private int idMenu;
	@Column(name="id_estatus")
	private int idEstatus;
	
	public PermisoActualizar() {
		
	}

	public int getIdPermiso() {
		return idPermiso;
	}

	public void setIdPermiso(int idPermiso) {
		this.idPermiso = idPermiso;
	}

	public int getNumEmpleado() {
		return numEmpleado;
	}

	public void setNumEmpleado(int numEmpleado) {
		this.numEmpleado = numEmpleado;
	}

	public int getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(int idMenu) {
		this.idMenu = idMenu;
	}

	public int getIdEstatus() {
		return idEstatus;
	}

	public void setIdEstatus(int idEstatus) {
		this.idEstatus = idEstatus;
	}

	@Override
	public String toString() {
		return "PermisoActualizar [idPermiso=" + idPermiso + ", numEmpleado=" + numEmpleado + ", idMenu=" + idMenu
				+ ", idEstatus=" + idEstatus + "]";
	}
}
