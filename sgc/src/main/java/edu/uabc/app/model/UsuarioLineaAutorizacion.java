package edu.uabc.app.model;

public class UsuarioLineaAutorizacion {

	private int numEmpleado;
	private String nombres;
	private String apellidos;
	private String puesto;
	private String departamento;
	private int nivel;
	
	public UsuarioLineaAutorizacion() {
		
	}

	public int getNumEmpleado() {
		return numEmpleado;
	}

	public void setNumEmpleado(int numEmpleado) {
		this.numEmpleado = numEmpleado;
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

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	@Override
	public String toString() {
		return "UsuarioLineaAutorizacion [numEmpleado=" + numEmpleado + ", nombres=" + nombres + ", apellidos="
				+ apellidos + ", puesto=" + puesto + ", departamento=" + departamento + ", nivel=" + nivel + "]";
	}
}
