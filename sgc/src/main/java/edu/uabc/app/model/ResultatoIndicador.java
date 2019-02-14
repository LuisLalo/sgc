package edu.uabc.app.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="resultado_indicador")
public class ResultatoIndicador {

	@Id
	private int id_resultado;
	private int id_indicador;
	private String frecuencia;
	private int valor;
	private Date fecha_creacion;
	private Date anio;
	
	public ResultatoIndicador() {
		
	}

	public int getId_resultado() {
		return id_resultado;
	}

	public void setId_resultado(int id_resultado) {
		this.id_resultado = id_resultado;
	}

	public int getId_indicador() {
		return id_indicador;
	}

	public void setId_indicador(int id_indicador) {
		this.id_indicador = id_indicador;
	}

	public String getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public Date getAnio() {
		return anio;
	}

	public void setAnio(Date anio) {
		this.anio = anio;
	}

	@Override
	public String toString() {
		return "ResultatoIndicador [id_resultado=" + id_resultado + ", id_indicador=" + id_indicador + ", frecuencia="
				+ frecuencia + ", valor=" + valor + ", fecha_creacion=" + fecha_creacion + ", anio=" + anio + "]";
	}
}
