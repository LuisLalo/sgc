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
<spring:url value="/proyecto_mejora/guardarProyectoMejora" var="urlForm"></spring:url>
<link rel="stylesheet" href="${ urlResources }/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/sgc/resources/css/bootstrap-4-navbar.css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

</head>

<body>
	
	<jsp:include page="../includes/header.jsp"></jsp:include>
	
	<div class="container">
		
		<br>
		<h3 class="text-center font-weight-bold">Nuevo Proyecto de Mejora</h3>
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

      <form:form action="${ urlForm }" method="post" enctype="multipart/form-data" modelAttribute="proyectoMejora">
      <div class="row">
        <div class="col-sm-3">
            <div class="form-group">
              <label for="areaAplicacion">Número de proyecto</label>
              <form:input type="text" class="form-control" path="numeroProyecto" id="numeroProyecto" required="required" />
            </div>  
          </div>
        </div>
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
        <div class="p-3 mb-2 bg-light text-dark">Tipo de Proyecto</div>
        <div class="row">
          <c:forEach var="tipoProyecto" items="${ tipoProyecto }">
            <div class="col-sm-6">
              <div class="form-group text-center">
                <label for="tipoProyecto"><form:radiobutton path="tipoProyecto" id="tipoProyecto" class="form-control" value="${ tipoProyecto.idTipoProyecto }"/>${ tipoProyecto.nombre }</label>
              </div>  
            </div>
          </c:forEach>
        </div>
        <div class="row">
          <div class="col-sm-6">
            <div class="form-group">
              <label for="areaAplicacion">Nombre del proyecto</label>
              <form:input type="text" class="form-control" path="nombre" id="nombre" required="required" />
            </div>  
          </div>
          <div class="col-sm-6">
            <div class="form-group">
              <label for="areaAplicacion" class="control-label">Area de aplicación</label>
                <form:select id="areaAplicacion" path="areaAplicacion" class="form-control">
                <c:forEach var="areaAplicacion" items="${ areaAplicacion }">
                  <form:option value="${ areaAplicacion.id_departamento }">${ areaAplicacion.nombre }</form:option>
                </c:forEach>
                </form:select>
            </div>
          </div>
		</div>
        <div class="row">
          <div class="col-sm-6">
            <div class="form-group">
              <label for="fechaCreacion">Fecha de inicio</label>
              <form:input type="date" class="form-control" path="fechaCreacion" id="fechaCreacion" required="required" />
            </div>  
          </div>
          <div class="col-sm-6">
            <div class="form-group">
              <label for="fechaTerminacion">Fecha de terminación</label>
              <form:input type="date" class="form-control" path="fechaTerminacion" id="fechaTerminacion" required="required" />
            </div>  
          </div>
        </div>
        <div class="p-3 mb-2 bg-light text-dark">Situación deseada</div>
        <div class="row">
          <div class="col-sm-12">
            <div class="form-group">
              <form:textarea class="form-control" path="situacionDeseada" id="situacionDeseada" rows="5" required="required" ></form:textarea>
            </div>  
          </div>
        </div>
        <div class="p-3 mb-2 bg-light text-dark">Situación actual</div>
        <div class="row">
          <div class="col-sm-12">
            <div class="form-group">
              <form:textarea class="form-control" path="situacionActual" id="situacionActual" rows="5" required="required" ></form:textarea>
            </div>  
          </div>
        </div>
        <div class="row">  
          <div class="col-sm-6">
            <div class="form-group">
              <label for="areaResponsable">Departamento o área de trabajo responsable del proyecto</label>
              <form:select id="departamento" path="departamento" class="form-control">
                <c:forEach var="areaResponsable" items="${ areaResponsable }">
                  <form:option value="${ areaResponsable.id_departamento }">${ areaResponsable.nombre }</form:option>
                </c:forEach>
              </form:select>
            </div>
          </div>
          <div class="col-sm-6">
            <div class="form-group">
              <label for="responsable">Responsable verificación</label>
                <form:select id="numEmpleado" path="numEmpleado" class="form-control">
                <c:forEach var="responsable" items="${ responsable }">
                  <form:option value="${ responsable.num_empleado }">${ responsable.nombres } ${ responsable.apellidos }</form:option>
                </c:forEach>
              </form:select>
            </div>  
          </div>
        </div>
        <div class="row">
          <div class="col-sm-12">
            <div class="form-group">
              <div class="p-3 mb-2 bg-light text-dark">Recursos requeridos para la implementación de Proyecto</div>
              <form:textarea class="form-control" path="recurso" id="recurso" rows="5" required="required" ></form:textarea>
            </div>  
          </div>
        </div>
        <div class="row">
          <div class="col-sm-12">
            <div class="form-group">
              <div class="p-3 mb-2 bg-light text-dark">Enuncie las Técnicas empleadas en el Proyecto de Mejora Continua: (Gráficos de control, Equipamiento,
Sistemas, Análisis de Valor, Benchmarking, Capacitación, Tiempos y Movimientos, otras.)</div>
              <form:textarea class="form-control" path="tecnica" id="tecnica" rows="5" required="required" ></form:textarea>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-sm-12">
            <div class="form-group">
              <div class="p-3 mb-2 bg-light text-dark">Resultados</div>
              <form:textarea class="form-control" path="resultado" id="resultado" rows="5" required="required" ></form:textarea>
            </div>
          </div>
        </div>
         
        <button type="submit" class="btn btn-success" >Siguiente</button>
      </form:form> 
		
	</div>
	
	<jsp:include page="../includes/footer.jsp"></jsp:include>
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
	<script src="${ urlResources }/bootstrap/js/bootstrap.min.js"></script>
	<script src="/sgc/resources/js/bootstrap-4-navbar.js"></script>

</body>
</html>