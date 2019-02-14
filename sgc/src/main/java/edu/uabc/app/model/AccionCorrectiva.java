package edu.uabc.app.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="accion_correctiva")
public class AccionCorrectiva {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_accion")
	private int idAccion;
	
	@Column(name="proviene_de")
	private int provieneDe;
	
	private Date fecha;
	
	@Column(name="numero_auditoria")
	private int numeroAuditoria;
	
	@Column(name="tipo_auditoria")
	private int tipoAuditoria;
	
	@Column(name="observacion_norma")
	private String observacionNorma;
	
	private String elemento;
	
	private String descripcion;
	
	private int departamento;
	
	@Column(name="responsable_solventar")
	private int responsableSolventar;
	
	@Column(name="responsable_verificar")
	private int responsableVerificar;
	
	@Column(name="causa_mano")
	private String causaMano;
	
	@Column(name="causa_medio")
	private String causaMedio;
	
	@Column(name="causa_material")
	private String causaMaterial;
	
	@Column(name="causa_metodo")
	private String causaMetodo;
	
	@Column(name="causa_maquinaria")
	private String causaMaquinaria;
	
	@Column(name="causa_analisis")
	private String causaAnalisis;
	
	@Column(name="accion_correctiva")
	private String accionCorrectiva;
	
	@Column(name="fecha_aplicacion")
	private Date fechaAplicacion;
	
	@Column(name="id_estatus_accion")
	private int idEstatusAccion;
	
	private int evaluacion;
	
	private String resultado;
	
	@Column(name="numero_accion")
	private int numeroAccion;
	
	//@OneToMany(mappedBy="accionActividad",fetch=FetchType.EAGER)
	//List<ActividadAccion> actividadAccion;
	
	public AccionCorrectiva() {
		
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

	public String getCausaMano() {
		return causaMano;
	}

	public void setCausaMano(String causaMano) {
		this.causaMano = causaMano;
	}

	public String getCausaMedio() {
		return causaMedio;
	}

	public void setCausaMedio(String causaMedio) {
		this.causaMedio = causaMedio;
	}

	public String getCausaMaterial() {
		return causaMaterial;
	}

	public void setCausaMaterial(String causaMaterial) {
		this.causaMaterial = causaMaterial;
	}

	public String getCausaMetodo() {
		return causaMetodo;
	}

	public void setCausaMetodo(String causaMetodo) {
		this.causaMetodo = causaMetodo;
	}

	public String getCausaMaquinaria() {
		return causaMaquinaria;
	}

	public void setCausaMaquinaria(String causaMaquinaria) {
		this.causaMaquinaria = causaMaquinaria;
	}

	public String getCausaAnalisis() {
		return causaAnalisis;
	}

	public void setCausaAnalisis(String causaAnalisis) {
		this.causaAnalisis = causaAnalisis;
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
		return "AccionCorrectiva [idAccion=" + idAccion + ", provieneDe=" + provieneDe + ", fecha=" + fecha
				+ ", numeroAuditoria=" + numeroAuditoria + ", tipoAuditoria=" + tipoAuditoria + ", observacionNorma="
				+ observacionNorma + ", elemento=" + elemento + ", descripcion=" + descripcion + ", departamento="
				+ departamento + ", responsableSolventar=" + responsableSolventar + ", responsableVerificar="
				+ responsableVerificar + ", causaMano=" + causaMano + ", causaMedio=" + causaMedio + ", causaMaterial="
				+ causaMaterial + ", causaMetodo=" + causaMetodo + ", causaMaquinaria=" + causaMaquinaria
				+ ", causaAnalisis=" + causaAnalisis + ", accionCorrectiva=" + accionCorrectiva + ", fechaAplicacion="
				+ fechaAplicacion + ", idEstatusAccion=" + idEstatusAccion + ", evaluacion=" + evaluacion
				+ ", resultado=" + resultado + ", numeroAccion=" + numeroAccion + "]";
	}
}