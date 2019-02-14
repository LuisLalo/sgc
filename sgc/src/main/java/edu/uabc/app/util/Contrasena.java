package edu.uabc.app.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Contrasena {
	
	// Método para encriptar la contraseña con Bcrypt
	public String encriptarContrasena(String contrasena) {
		// Se hace el encriptado de la contrasea con BCrypt 
		String contrasena_hash = BCrypt.hashpw(contrasena, BCrypt.gensalt());
		//System.out.println("Contraseña encriptada: " + contrasena_hash);
		return contrasena_hash;
	}
	
	// Método para comparar si la contraseña corresponde al valor que está almacenado en la base de datos
	public int comparaContrasena(String contrasena, String contrasena_almacenada) {
		int resultado = 0;
		
		System.out.println("Contraseña ingresada:  " + contrasena);
		System.out.println("Contraseña almacenada: " + contrasena_almacenada);
		
		// Se hace la comparación de la contraseña del usuario para verificar que coincida con la almacenada
		if(BCrypt.checkpw(contrasena, contrasena_almacenada)) {
			// Las contraseñas coinciden
			return resultado = 1;
		}
		else {
			// Las contraseñas no coinciden
			return resultado = 0;
		}
	}
}
