package edu.uabc.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="contador")
public class Contador {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_contador;
	private String nombre;
	private int contador;
	
	public Contador() {
		
	}

	public int getId_contador() {
		return id_contador;
	}

	public void setId_contador(int id_contador) {
		this.id_contador = id_contador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

	@Override
	public String toString() {
		return "Contador [id_contador=" + id_contador + ", nombre=" + nombre + ", contador=" + contador + "]";
	}
}
