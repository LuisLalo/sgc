package edu.uabc.app.model;

import java.util.Date;


public class AccionCorrectivaAnalisis {

	private int idAccion;
	
	private int provieneDe;
	
	private Date fecha;
	
	private int numeroAuditoria;
	
	private int tipoAuditoria;
	
	private String observacionNorma;
	
	private String elemento;
	
	private String descripcion;
	
	private int departamento;
	
	private int responsableSolventar;
	
	private int responsableVerificar;
	
	private String causaRaizManoObra;
	
	private String causaRaizMedioAmbiente;
	
	private String causaRaizMaterial;
	
	private String causaRaizMetodo;
	
	private String causaRaizMaquinaria;
	
	private String causaRaizAnalisisNoConformidad;
	
	private String accionCorrectiva;
	
	private Date fechaAplicacion;
	
	private int idEstatusAccion;
	
	private int evaluacion;
	
	private String resultado;
	
	private int numeroAccion;
	
	public AccionCorrectivaAnalisis() {
		
	}

	public int getIdAccion() {
		return idAccion;
	}

	public void setIdAccion(int idAccion) {
		this.idAccion = idAccion;
	}

	public int getProvieneDe() {
		return provieneDe;
	}

	public void setProvieneDe(int provieneDe) {
		this.provieneDe = provieneDe;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getNumeroAuditoria() {
		return numeroAuditoria;
	}

	public void setNumeroAuditoria(int numeroAuditoria) {
		this.numeroAuditoria = numeroAuditoria;
	}

	public int getTipoAuditoria() {
		return tipoAuditoria;
	}

	public void setTipoAuditoria(int tipoAuditoria) {
		this.tipoAuditoria = tipoAuditoria;
	}

	public String getObservacionNorma() {
		return observacionNorma;
	}

	public void setObservacionNorma(String observacionNorma) {
		this.observacionNorma = observacionNorma;
	}

	public String getElemento() {
		return elemento;
	}

	public void setElemento(String elemento) {
		this.elemento = elemento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getDepartamento() {
		return departamento;
	}

	public void setDepartamento(int departamento) {
		this.departamento = departamento;
	}

	public int getResponsableSolventar() {
		return responsableSolventar;
	}

	public void setResponsableSolventar(int responsableSolventar) {
		this.responsableSolventar = responsableSolventar;
	}

	public int getResponsableVerificar() {
		return responsableVerificar;
	}

	public void setResponsableVerificar(int responsableVerificar) {
		this.responsableVerificar = responsableVerificar;
	}

	public String getCausaRaizManoObra() {
		return causaRaizManoObra;
	}

	public void setCausaRaizManoObra(String causaRaizManoObra) {
		this.causaRaizManoObra = causaRaizManoObra;
	}

	public String getCausaRaizMedioAmbiente() {
		return causaRaizMedioAmbiente;
	}

	public void setCausaRaizMedioAmbiente(String causaRaizMedioAmbiente) {
		this.causaRaizMedioAmbiente = causaRaizMedioAmbiente;
	}

	public String getCausaRaizMaterial() {
		return causaRaizMaterial;
	}

	public void setCausaRaizMaterial(String causaRaizMaterial) {
		this.causaRaizMaterial = causaRaizMaterial;
	}

	public String getCausaRaizMetodo() {
		return causaRaizMetodo;
	}

	public void setCausaRaizMetodo(String causaRaizMetodo) {
		this.causaRaizMetodo = causaRaizMetodo;
	}

	public String getCausaRaizMaquinaria() {
		return causaRaizMaquinaria;
	}

	public void setCausaRaizMaquinaria(String causaRaizMaquinaria) {
		this.causaRaizMaquinaria = causaRaizMaquinaria;
	}

	public String getCausaRaizAnalisisNoConformidad() {
		return causaRaizAnalisisNoConformidad;
	}

	public void setCausaRaizAnalisisNoConformidad(String causaRaizAnalisisNoConformidad) {
		this.causaRaizAnalisisNoConformidad = causaRaizAnalisisNoConformidad;
	}

	public String getAccionCorrectiva() {
		return accionCorrectiva;
	}

	public void setAccionCorrectiva(String accionCorrectiva) {
		this.accionCorrectiva = accionCorrectiva;
	}

	public Date getFechaAplicacion() {
		return fechaAplicacion;
	}

	public void setFechaAplicacion(Date fechaAplicacion) {
		this.fechaAplicacion = fechaAplicacion;
	}

	public int getIdEstatusAccion() {
		return idEstatusAccion;
	}

	public void setIdEstatusAccion(int idEstatusAccion) {
		this.idEstatusAccion = idEstatusAccion;
	}

	public int getEvaluacion() {
		return evaluacion;
	}

	public void setEvaluacion(int evaluacion) {
		this.evaluacion = evaluacion;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public int getNumeroAccion() {
		return numeroAccion;
	}

	public void setNumeroAccion(int numeroAccion) {
		this.numeroAccion = numeroAccion;
	}

	@Override
	public String toString() {
		return "AccionCorrectivaAnalisis [idAccion=" + idAccion + ", provieneDe=" + provieneDe + ", fecha=" + fecha
				+ ", numeroAuditoria=" + numeroAuditoria + ", tipoAuditoria=" + tipoAuditoria + ", observacionNorma="
				+ observacionNorma + ", elemento=" + elemento + ", descripcion=" + descripcion + ", departamento="
				+ departamento + ", responsableSolventar=" + responsableSolventar + ", responsableVerificar="
				+ responsableVerificar + ", causaRaizManoObra=" + causaRaizManoObra + ", causaRaizMedioAmbiente="
				+ causaRaizMedioAmbiente + ", causaRaizMaterial=" + causaRaizMaterial + ", causaRaizMetodo="
				+ causaRaizMetodo + ", causaRaizMaquinaria=" + causaRaizMaquinaria + ", causaRaizAnalisisNoConformidad="
				+ causaRaizAnalisisNoConformidad + ", accionCorrectiva=" + accionCorrectiva + ", fechaAplicacion="
				+ fechaAplicacion + ", idEstatusAccion=" + idEstatusAccion + ", evaluacion=" + evaluacion
				+ ", resultado=" + resultado + ", numeroAccion=" + numeroAccion + "]";
	}
}