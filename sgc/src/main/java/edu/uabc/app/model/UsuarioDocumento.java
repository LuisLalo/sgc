package edu.uabc.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuario_documento")
public class UsuarioDocumento {

	@Id
	@Column(name="id_usuario_documento")
	private int idUsuarioDocumento;
	@Column(name="num_empleado")
	private int numEmpleado;
	@Column(name="id_documento")
	private int idDocumento;
	
	public UsuarioDocumento() {
		
	}

	public int getIdUsuarioDocumento() {
		return idUsuarioDocumento;
	}

	public void setIdUsuarioDocumento(int idUsuarioDocumento) {
		this.idUsuarioDocumento = idUsuarioDocumento;
	}

	public int getNumEmpleado() {
		return numEmpleado;
	}

	public void setNumEmpleado(int numEmpleado) {
		this.numEmpleado = numEmpleado;
	}

	public int getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(int idDocumento) {
		this.idDocumento = idDocumento;
	}

	@Override
	public String toString() {
		return "UsuarioDocumento [idUsuarioDocumento=" + idUsuarioDocumento + ", numEmpleado=" + numEmpleado
				+ ", idDocumento=" + idDocumento + "]";
	}
}
