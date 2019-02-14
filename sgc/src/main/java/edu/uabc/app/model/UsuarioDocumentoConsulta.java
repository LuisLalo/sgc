package edu.uabc.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="usuario_documento")
public class UsuarioDocumentoConsulta {
	@Id
	@Column(name="id_usuario_documento")
	private int idUsuarioDocumento;
	@ManyToOne(cascade=CascadeType.ALL, optional=false)
	@JoinColumn(name="num_empleado")
	private UsuarioConsulta usuario;
	@ManyToOne(cascade=CascadeType.ALL, optional=false)
	@JoinColumn(name="id_documento")
	private DocumentoConsulta documento;
	
	public UsuarioDocumentoConsulta() {
		
	}

	public int getIdUsuarioDocumento() {
		return idUsuarioDocumento;
	}

	public void setIdUsuarioDocumento(int idUsuarioDocumento) {
		this.idUsuarioDocumento = idUsuarioDocumento;
	}

	public UsuarioConsulta getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioConsulta usuario) {
		this.usuario = usuario;
	}

	public DocumentoConsulta getDocumento() {
		return documento;
	}

	public void setDocumento(DocumentoConsulta documento) {
		this.documento = documento;
	}

	@Override
	public String toString() {
		return "UsuarioDocumentoConsulta [idUsuarioDocumento=" + idUsuarioDocumento + ", usuario=" + usuario
				+ ", documento=" + documento + "]";
	}
}
