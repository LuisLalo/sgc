package edu.uabc.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="permiso")
public class Permiso {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_permiso")
	private int idPermiso;
	@Column(name="num_empleado")
	private int numEmpleado;
	@OneToOne
	@JoinColumn(name="id_menu")
	private Menu menu;
	@OneToOne
	@JoinColumn(name="id_estatus")
	private EstatusPermiso estatusPermiso;
	
	public Permiso() {
		
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

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public EstatusPermiso getEstatusPermiso() {
		return estatusPermiso;
	}

	public void setEstatusPermiso(EstatusPermiso estatusPermiso) {
		this.estatusPermiso = estatusPermiso;
	}

	@Override
	public String toString() {
		return "Permiso [idPermiso=" + idPermiso + ", numEmpleado=" + numEmpleado + ", menu=" + menu
				+ ", estatusPermiso=" + estatusPermiso + "]";
	}
}
