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
<spring:url value="/accion_correctiva/guardarActividad" var="urlForm"></spring:url>
<link rel="stylesheet" href="${ urlResources }/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/sgc/resources/css/bootstrap-4-navbar.css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

</head>

<body>
	
	<jsp:include page="../includes/header.jsp"></jsp:include>
	
	<div class="container">
		
		<br>
		<h3 class="text-center font-weight-bold">Agregar Nueva Actividad de la Acción Correctiva</h3>
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

      <form:form action="${ urlForm }" method="post" enctype="multipart/form-data" modelAttribute="actividadAccion">
        <div class="p-3 mb-2 bg-light text-dark">Actividades</div>
        <div class="row">
          <div class="col-sm-12">
            <div class="form-group">
              <form:hidden id="idProyecto" path="idProyecto" class="form-control" />
              <form:hidden id="idAccion" path="idAccion" class="form-control" />
              <form:input type="text" class="form-control" path="descripcion" id="descripcion" required="required" />
            </div>  
          </div>
        </div>
        <div class="row">
          <div class="col-sm-3">
            <div class="form-group">
              <label for="fechaEstimada">Fecha Programada</label>             
              <form:input type="date" class="form-control" path="fechaEstimada" id="fechaEstimada" required="required" />
            </div>  
          </div>
          <div class="col-sm-3">
            <div class="form-group">
              <label for="fechaReal">Fecha de Realización</label>             
              <form:input type="date" class="form-control" path="fechaReal" id="fechaReal" required="required" />
            </div>  
          </div>
          <div class="col-sm-3">
            <div class="form-group">
              <label for="porcentajeAvance">Porcentaje de avance (Acumulado)</label>             
              <form:input type="text" class="form-control" path="porcentajeAvance" id="porcentajeAvance" required="required" />
            </div>  
          </div>
        <div class="col-sm-3">
            <div class="form-group">
              <label for="responsable" class="control-label">Responsable</label>   
                <form:select id="responsable" path="responsable" class="form-control">
                  <c:forEach var="usuario" items="${ usuario }">
                  	<form:option value="${ usuario.num_empleado }">${ usuario.nombres } ${ usuario.apellidos }</form:option>
                  </c:forEach>
                </form:select>
            </div>  
          </div>
        </div>
        
        <button type="submit" class="btn btn-success" >Agregar Actividad</button>
      </form:form> 
		
	</div>
	
	<div class="container">
		<h3 class="text-center font-weight-bold">Actividades de la Acción Correctiva</h3>
		<br>
	<div class="table-responsive">
        <table class="table table-hover table-striped table-bordered">
            <tr>
                <th>Actividad</th>
                <th>Fecha Programada</th>
                <th>Fecha de Realización</th>
                <th>Responable</th>
                <th>% Avance (Acumulado)</th>
            </tr>
            <c:forEach var="actividadAccionConsulta" items="${ actividadAccionConsulta }">
            <tr>
                <td>${ actividadAccionConsulta.descripcion }</td>
                <td>${ actividadAccionConsulta.fechaEstimada }</td>
                <td>${ actividadAccionConsulta.fechaReal }</td>
                <td>${ actividadAccionConsulta.usuarioConsulta.nombres } ${ actividadAccionConsulta.usuarioConsulta.apellidos }</td>
                <td>${ actividadAccionConsulta.porcentajeAvance }</td>
			</tr>                  
            </c:forEach>
        </table>
      </div>
	
	<a href="${ urlRoot }accion_correctiva/index" class="btn btn-success" role="button" title="Terminar" >Terminar</a><br><br>
	</div>
	
	<jsp:include page="../includes/footer.jsp"></jsp:include>
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
	<script src="${ urlResources }/bootstrap/js/bootstrap.min.js"></script>
	<script src="/sgc/resources/js/bootstrap-4-navbar.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</body>
</html>