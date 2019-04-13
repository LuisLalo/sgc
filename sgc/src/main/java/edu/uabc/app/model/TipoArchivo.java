package edu.uabc.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipo_archivo")
public class TipoArchivo {

	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_tipo_archivo;
	private String nombre;
	private String ruta;
	
	TipoArchivo(){
		
	}

	public int getId_tipo_archivo() {
		return id_tipo_archivo;
	}

	public void setId_tipo_archivo(int id_tipo_archivo) {
		this.id_tipo_archivo = id_tipo_archivo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	@Override
	public String toString() {
		return "TipoArchivo [id_tipo_archivo=" + id_tipo_archivo + ", nombre=" + nombre + ", ruta=" + ruta + "]";
	}
}
