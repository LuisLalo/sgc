package edu.uabc.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class UsuarioConsulta {
	@Id
	private int num_empleado;
	private String nombres;
	private String apellidos;
	private String correo;
	private String contrasena;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_Puesto")
	private Puesto puesto;
	@ManyToOne(cascade=CascadeType.ALL, optional=false)
	@JoinColumn(name="id_departamento")
	private Departamento departamento;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_rol")
	private Rol rol;
	@ManyToOne(cascade=CascadeType.ALL, optional=false)
	@JoinColumn(name="id_estatus")
	private EstatusUsuario estatus;
	
	public UsuarioConsulta() {
		
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

	public Puesto getPuesto() {
		return puesto;
	}

	public void setPuesto(Puesto puesto) {
		this.puesto = puesto;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public EstatusUsuario getEstatus() {
		return estatus;
	}

	public void setEstatus(EstatusUsuario estatus) {
		this.estatus = estatus;
	}

	@Override
	public String toString() {
		return "UsuarioConsulta [num_empleado=" + num_empleado + ", nombres=" + nombres + ", apellidos=" + apellidos
				+ ", correo=" + correo + ", contrasena=" + contrasena + ", puesto=" + puesto + ", departamento="
				+ departamento + ", rol=" + rol + ", estatus=" + estatus + "]";
	}	
}
