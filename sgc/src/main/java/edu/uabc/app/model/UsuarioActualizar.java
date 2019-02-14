package edu.uabc.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class UsuarioActualizar {

	@Id
	private int num_empleado;
	private String nombres;
	private String apellidos;
	private String correo;
	private String contrasena;
	private int id_puesto;
	private int id_departamento;
	private int id_rol;
	@Column(name="id_estatus")
	private int estatus;
	
	public UsuarioActualizar() {
		
	}

	public int getNum_empleado() {
		return num_empleado;
	}

	public void setNum_empleado(int num_empleado) {
		this.num_empleado = num_empleado;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public int getId_puesto() {
		return id_puesto;
	}

	public void setId_puesto(int id_puesto) {
		this.id_puesto = id_puesto;
	}

	public int getId_departamento() {
		return id_departamento;
	}

	public void setId_departamento(int id_departamento) {
		this.id_departamento = id_departamento;
	}

	public int getId_rol() {
		return id_rol;
	}

	public void setId_rol(int id_rol) {
		this.id_rol = id_rol;
	}

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	@Override
	public String toString() {
		return "UsuarioActualizar [num_empleado=" + num_empleado + ", nombres=" + nombres + ", apellidos=" + apellidos
				+ ", correo=" + correo + ", contrasena=" + contrasena + ", id_puesto=" + id_puesto
				+ ", id_departamento=" + id_departamento + ", id_rol=" + id_rol + ", estatus=" + estatus + "]";
	}
}
