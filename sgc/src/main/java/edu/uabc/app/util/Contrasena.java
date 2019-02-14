package edu.uabc.app.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Contrasena {
	
	// M�todo para encriptar la contrase�a con Bcrypt
	public String encriptarContrasena(String contrasena) {
		// Se hace el encriptado de la contrasea con BCrypt 
		String contrasena_hash = BCrypt.hashpw(contrasena, BCrypt.gensalt());
		//System.out.println("Contrase�a encriptada: " + contrasena_hash);
		return contrasena_hash;
	}
	
	// M�todo para comparar si la contrase�a corresponde al valor que est� almacenado en la base de datos
	public int comparaContrasena(String contrasena, String contrasena_almacenada) {
		int resultado = 0;
		
		System.out.println("Contrase�a ingresada:  " + contrasena);
		System.out.println("Contrase�a almacenada: " + contrasena_almacenada);
		
		// Se hace la comparaci�n de la contrase�a del usuario para verificar que coincida con la almacenada
		if(BCrypt.checkpw(contrasena, contrasena_almacenada)) {
			// Las contrase�as coinciden
			return resultado = 1;
		}
		else {
			// Las contrase�as no coinciden
			return resultado = 0;
		}
	}
}
