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
<spring:url value="/documentos/guardar" var="urlForm"></spring:url>
<link rel="stylesheet" href="${ urlResources }/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/sgc/resources/css/bootstrap-4-navbar.css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

</head>

<body>
	
	<jsp:include page="../includes/header.jsp"></jsp:include>
	
	<div class="container">
		
		<br>
		<h3 class="text-center font-weight-bold">Datos del Documento</h3>
		<br>
			
	<spring:hasBindErrors name="documento">
		<div class="alert alert-danger" role="alert">
			Por favor corrija los siguientes errores:
			<ul>
				<c:forEach var="error" items="${ errors.allErrors }">
					<li><spring:message message="${ error }" /></li>
				</c:forEach>
			</ul>
		</div>
	</spring:hasBindErrors>

      <form:form action="${ urlForm }" method="post" enctype="multipart/form-data" modelAttribute="documentoActualizar">
        <div class="row">
          <div class="col-sm-3">
            <div class="form-group">
              <label for="nombre">Nombre</label>
              <form:hidden path="idDocumento"/>
              <form:hidden class="form-control" path="fechaCreacion" id="fecha_creacion" />
              <form:hidden path="idTipoArchivo" class="form-control" />
              <form:hidden path="ordenDocumento" id="orden_documento" />
              <form:hidden id="clasificadorDocumento" path="idClasificadorDocumento" class="form-control" />
              <form:input type="text" class="form-control" path="nombre" id="nombre" required="required" />
            </div>  
          </div>
          <div class="col-sm-3">
            <div class="form-group">
              <label for="descripcion">Descripcion</label>
              <form:input type="text" class="form-control" path="descripcion" id="descripcion" required="required" />
            </div>  
          </div>
          
          
          <div class="col-sm-3">
            <div class="form-group">
              <label for="id_tipo_documento" class="control-label">Tipo Documento</label>              
              <form:select id="id_tipo_documento" path="idTipoDocumento" class="form-control">
                <c:forEach var="tipo_documento" items="${ tipo_documento }">
                	<form:option value="${ tipo_documento.idTipoDocumento }">${ tipo_documento.nombre }</form:option>
                </c:forEach>
              </form:select>
            </div>
          </div>
          <div class="col-sm-3">
            <div class="form-group">
              <label for="estatus" class="control-label">Estatus</label>              
              <form:select id="genero" path="estatus" class="form-control">
                <form:option value="100">Activo</form:option>
                <form:option value="1">Pendiente de autorización</form:option>               
              </form:select>             
            </div> 
          </div>
        </div>
        <div class="row">
          <!--
          <div class="col-sm-3">
            <div class="form-group">
              <label for="id_clasificador_documento" class="control-label">Clasificador de Documento</label>              
              <form:select id="id_clasificador_documento" path="idClasificadorDocumento" class="form-control">
                <c:forEach var="clasificador_documento" items="${ clasificador_documento }">
                	<form:option value="${ clasificador_documento.id_clasificador_documento }">${ clasificador_documento.nombre }</form:option>
                </c:forEach>
              </form:select>
            </div>
          </div>
          -->
          <div class="col-sm-3">
            <div class="form-group">
              <label for="departamento" class="control-label">Departamento</label>              
              <form:select id="departamento" path="idDepartamento" class="form-control">
                <c:forEach var="departamentos" items="${ departamentos }">
                	<form:option value="${ departamentos.id_departamento }">${ departamentos.nombre }</form:option>
                </c:forEach>
              </form:select>
            </div>
          </div>
          <div class="col-sm-3">
            <div class="form-group">
              <label for="ruta">Archivo</label>
              <form:hidden path="ruta"/>
              <input type="file" id="ruta" name="ruta" required="required"/>
              <p class="help-block">Archivo del Documento</p>
            </div> 
          </div>
        </div>

        
        <button type="submit" class="btn btn-success" >Guardar</button>
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