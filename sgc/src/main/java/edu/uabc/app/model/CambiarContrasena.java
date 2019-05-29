package edu.uabc.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CambiarContrasena {

	@Id
	private String contrasenaActual;
	private String confirmarContrasena;
	
	public CambiarContrasena() {
		
	}

	public String getContrasenaActual() {
		return contrasenaActual;
	}

	public void setContrasenaActual(String contrasenaActual) {
		this.contrasenaActual = contrasenaActual;
	}

	public String getConfirmarContrasena() {
		return confirmarContrasena;
	}

	public void setConfirmarContrasena(String confirmarContrasena) {
		this.confirmarContrasena = confirmarContrasena;
	}

	@Override
	public String toString() {
		return "CambiarContrasena [contrasenaActual=" + contrasenaActual + ", confirmarContrasena="
				+ confirmarContrasena + "]";
	}
}
