package edu.uabc.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="expresion")
public class Expresion {

	@Id
	private int id_expresion;
	private int id_indicador;
	private String expresion;
	private String valor_minimo;
	private String valor_maximo;
	private String color;
	private String simbolo_expresion;
	
	public Expresion() {
		
	}

	public int getId_expresion() {
		return id_expresion;
	}

	public void setId_expresion(int id_expresion) {
		this.id_expresion = id_expresion;
	}

	public int getId_indicador() {
		return id_indicador;
	}

	public void setId_indicador(int id_indicador) {
		this.id_indicador = id_indicador;
	}

	public String getExpresion() {
		return expresion;
	}

	public void setExpresion(String expresion) {
		this.expresion = expresion;
	}

	public String getValor_minimo() {
		return valor_minimo;
	}

	public void setValor_minimo(String valor_minimo) {
		this.valor_minimo = valor_minimo;
	}

	public String getValor_maximo() {
		return valor_maximo;
	}

	public void setValor_maximo(String valor_maximo) {
		this.valor_maximo = valor_maximo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSimbolo_expresion() {
		return simbolo_expresion;
	}

	public void setSimbolo_expresion(String simbolo_expresion) {
		this.simbolo_expresion = simbolo_expresion;
	}

	@Override
	public String toString() {
		return "Expresion [id_expresion=" + id_expresion + ", id_indicador=" + id_indicador + ", expresion=" + expresion
				+ ", valor_minimo=" + valor_minimo + ", valor_maximo=" + valor_maximo + ", color=" + color
				+ ", simbolo_expresion=" + simbolo_expresion + "]";
	}
}
