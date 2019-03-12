<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">    
    <meta name="description" content="">
    <meta name="author" content="">
	<title>UABC | Tesorería</title>

<spring:url value="/resources" var="urlResources"></spring:url>
<spring:url value="/" var="urlRoot"></spring:url>
<spring:url value="/accion_correctiva/guardarAccionCorrectiva" var="urlForm"></spring:url>
<link rel="stylesheet" href="${ urlResources }/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/sgc/resources/css/bootstrap-4-navbar.css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

</head>

<body>
	
	<jsp:include page="../includes/header.jsp"></jsp:include>
	
	<div class="container">
		
		<br>
		<h3 class="text-center font-weight-bold">Nueva Acción Correctiva</h3>
		<br>
			
	<spring:hasBindErrors name="usuario">
		<div class="alert alert-danger" role="alert">
			Por favor corrija los siguientes errores:
			<ul>
				<c:forEach var="error" items="${ errors.allErrors }">
					<li><spring:message message="${ error }" /></li>
				</c:forEach>
			</ul>
		</div>
	</spring:hasBindErrors>

      <form:form action="${ urlForm }" method="post" enctype="multipart/form-data" modelAttribute="accionCorrectiva">
        <div class="p-3 mb-2 bg-light text-dark">Proviene de</div>
        <div class="row">
          <c:forEach var="provieneDe" items="${ provieneDe }">
            <div class="col-sm-2">
              <div class="form-group text-center">
                <label for="provieneDe"><form:radiobutton path="provieneDe" id="provieneDe" class="form-control" value="${ provieneDe.idProvieneDe }"/>${ provieneDe.nombre }</label>
              </div>
            </div>
          </c:forEach>
        </div>
        <div class="row">
          <div class="col-sm-4">
            <div class="form-group">
              <label for="fecha">Fecha</label>             
              <form:input type="date" class="form-control" path="fecha" id="fecha" required="required" />
            </div>  
          </div>
          <div class="col-sm-4">
            <div class="form-group">
              <label for="numeroAuditoria">Número de Auditoría</label>
              <form:input type="text" class="form-control" path="numeroAuditoria" id="numeroAuditoria" required="required" />
            </div>  
          </div>
          <div class="col-sm-4">
            <div class="form-group">
              <label for="numeroAccion">Clave</label>
              <form:input type="text" class="form-control" path="numeroAccion" id="numeroAcicon" required="required" />
            </div>  
          </div>
        </div>
        <div class="p-3 mb-2 bg-light text-dark">Tipo Auditoría</div>
        <div class="row">
          <c:forEach var="tipoAuditoria" items="${ tipoAuditoria }">
          <div class="col-sm-4">
            <div class="form-group text-center">
              <label for="tipoAuditoria"><form:radiobutton path="tipoAuditoria" id="tipoAuditoria" class="form-control" value="${ tipoAuditoria.idTipoAuditoria }"/>${ tipoAuditoria.nombre }</label>
            </div>  
          </div>
          </c:forEach>
		</div>
		<div class="p-3 mb-2 bg-light text-dark">Elemento de la Norma</div>
        <div class="row">
          <c:forEach var="observacionNorma" items="${ observacionNorma }">
            <div class="col-sm-4">
              <div class="form-group text-center">
                <label for="observacionNorma"><form:radiobutton path="observacionNorma" id="observacionNorma" class="form-control" value="${ observacionNorma.idObservacionNorma }"/>${ observacionNorma.nombre }</label>
              </div>
            </div>
          </c:forEach>   
          <div class="col-sm-4">
            <div class="form-group">
              <label for="elemento">Número de Elemento</label>
              <form:input type="text" class="form-control" path="elemento" id="elemento" required="required" />
            </div>  
          </div>
        </div> 
        <div class="p-3 mb-2 bg-light text-dark">Descripción de la No Conformidad / Observación</div>
        <div class="row">
          <div class="col-sm-12">
            <div class="form-group">
              <form:textarea class="form-control" rows="5" path="descripcion" id="descripcion" required="required" ></form:textarea>
            </div>  
          </div>
        </div>
        <div class="row">  
          <div class="col-sm-4">
            <div class="form-group">
              <label for="departamento" class="control-label">Departamento/Área</label>              
              <form:select id="departamento" path="departamento" class="form-control">
                <c:forEach var="departamento" items="${ departamento }">
                	<form:option value="${ departamento.id_departamento }">${ departamento.nombre }</form:option>
                </c:forEach>
              </form:select>
            </div>
          </div>
          <div class="col-sm-4">
            <div class="form-group">
              <label for="responsableSolventar">Responsable Solventar</label>            
              <form:select id="responsableSolventar" path="responsableSolventar" class="form-control">
                <c:forEach var="usuario" items="${ usuario }">
                	<form:option value="${ usuario.num_empleado }">${ usuario.nombres } ${ usuario.apellidos }</form:option>
                </c:forEach>
              </form:select>
            </div>
          </div>
          <div class="col-sm-4">
            <div class="form-group">
              <label for="responsableVerificar">Responsable Verificar</label>            
              <form:select id="responsableVerificar" path="responsableVerificar" class="form-control">
                <c:forEach var="usuario" items="${ usuario }">
                	<form:option value="${ usuario.num_empleado }">${ usuario.nombres } ${ usuario.apellidos }</form:option>
                </c:forEach>
              </form:select>
            </div>
          </div>
        </div>
        <div class="p-3 mb-2 bg-light text-dark">Análisis de Causa Raíz</div>
        <div class="row">
          <div class="col-sm-4">
            <div class="form-group">
              <label for="manoObra">Mano de Obra</label>
              <form:textarea class="form-control" path="causaMano" id="causaMano" rows="5" required="required" ></form:textarea>
            </div>  
          </div>
          <div class="col-sm-4">
            <div class="form-group">
              <label for="medioAmbiente">Medio Ambiente</label>
              <form:textarea class="form-control" path="causaMedio" id="causaMedio" rows="5" required="required" ></form:textarea>
            </div>
          </div>
          <div class="col-sm-4">
            <div class="form-group">
              <label for="material">Material</label>
              <form:textarea class="form-control" path="causaMaterial" id="causaMaterial" rows="5" required="required" ></form:textarea>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-sm-4">
            <div class="form-group">
              <label for="metodo">Método</label>
              <form:textarea class="form-control" path="causaMetodo" id="causaMetodo" rows="5" required="required" ></form:textarea>
            </div>
          </div>
          <div class="col-sm-4">
            <div class="form-group">
              <label for="maquinaria">Maquinaria</label>
              <form:textarea class="form-control" path="causaMaquinaria" id="causaMaquinaria" rows="5" required="required" ></form:textarea>
            </div>  
          </div>
          <div class="col-sm-4">
            <div class="form-group">
              <label for="analisisNoConformidad">Análisis No Conformidad</label>
              <form:textarea class="form-control" path="causaAnalisis" id="causaAnalisis" rows="5" required="required" ></form:textarea>
            </div>  
          </div>
        </div>
        <div class="p-3 mb-2 bg-light text-dark">Acción Correctiva</div>
        <div class="row">
          <div class="col-sm-12">
            <div class="form-group">
              <form:textarea class="form-control" path="accionCorrectiva" id="accionCorrectiva" rows="5" required="required" ></form:textarea>
            </div>  
          </div>
        </div>
        
        <div class="p-3 mb-2 bg-light text-dark">Feha de implementación Acción</div>
        <div class="row">
          <div class="col-sm-3">
            <div class="form-group">
              <form:input type="date" class="form-control" path="fechaAplicacion" id="fechaAplicacion" required="required" />
            </div>  
          </div>
        </div>
        <div class="p-3 mb-2 bg-light text-dark">ANÁLISIS POR EL ADMINISTRADOR DEL SGC</div>
        <div class="row">
          <div class="col-sm-3">
            <div class="form-group">           
              <form:select id="idEstatusAccion" path="idEstatusAccion" class="form-control">
                <c:forEach var="estatusAccion" items="${ estatusAccion }">
                	<form:option value="${ estatusAccion.idEstatus }">${ estatusAccion.nombre }</form:option>
                </c:forEach>
              </form:select>
            </div>
          </div>
        </div>
        <div class="p-3 mb-2 bg-light text-dark">¿La acción correctiva es efectiva?</div>
        <div class="row">
          <c:forEach var="evaluacion" items="${ evaluacion }">
            <div class="col-sm-2">
              <div class="form-group text-center">
                <label for="evaluacion"><form:radiobutton path="evaluacion" id="evaluacion" class="form-control" value="${ evaluacion.idEvaluacion }"/>${ evaluacion.nombre }</label>
              </div>
            </div>
          </c:forEach>
        </div>
        <div class="row">
          <div class="col-sm-12">
            <div class="form-group">
              <label for="resultado">Resultado</label>
              <form:textarea class="form-control" path="resultado" id="resultado" rows="5" required="required" ></form:textarea>
            </div>  
          </div>
        </div>

        <button type="submit" class="btn btn-success" >Continuar</button>
      </form:form> 
		
	</div>
	
	<jsp:include page="../includes/footer.jsp"></jsp:include>
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
	<script src="${ urlResources }/bootstrap/js/bootstrap.min.js"></script>
	<script src="/sgc/resources/js/bootstrap-4-navbar.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	
</body>
</html>