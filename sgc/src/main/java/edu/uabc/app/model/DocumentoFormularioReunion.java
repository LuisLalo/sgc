package edu.uabc.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="documento_formulario_reunion")
public class DocumentoFormularioReunion {

	@Id
	private int id_documento;
	private int id_reunion;
	
	public DocumentoFormularioReunion() {
		
	}

	public int getId_documento() {
		return id_documento;
	}

	public void setId_documento(int id_documento) {
		this.id_documento = id_documento;
	}

	public int getId_reunion() {
		return id_reunion;
	}

	public void setId_reunion(int id_reunion) {
		this.id_reunion = id_reunion;
	}

	@Override
	public String toString() {
		return "DocumentoFormularioReunion [id_documento=" + id_documento + ", id_reunion=" + id_reunion + "]";
	}
}
