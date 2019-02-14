<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
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
<spring:url value="/accion_correctiva/editar" var="urlEditar" />
    <spring:url value="/accion_correctiva/eliminar" var="urlEliminar" />
<link rel="stylesheet" href="${ urlResources }/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/sgc/resources/css/bootstrap-4-navbar.css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!--  Línea para agregar los íconos de las acciones-->
<link rel="stylesheet" href="${ urlResources }/bootstrap3.7/css/glyphicons.css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

</head>

<body>
	
	<jsp:include page="../includes/header.jsp"></jsp:include>

	<div class="container">
		<h3 class="text-center font-weight-bold">Listado de Acciones Correctivas</h3>
		
	  <c:if test="${ mensaje!=null }">
        <div class="alert alert-success" role="alert">${ mensaje }</div>
      </c:if>
      
      <sec:authorize access="hasAnyAuthority('SGC')">
      	<a href="${ urlRoot }accion_correctiva/crear" class="btn btn-success" role="button" title="Crear Nueva Accion Correctiva" >Nueva Acción Correctiva</a><br><br>
	  </sec:authorize>
	  <sec:authorize access="hasAnyAuthority('Area')">
      	<a href="${ urlRoot }accion_correctiva/crear" class="btn btn-success" role="button" title="Crear Nueva Accion Correctiva" >Nueva Acción Correctiva</a><br><br>
	  </sec:authorize>
	  
      <div class="table-responsive">
        <table class="table table-hover table-striped table-bordered">
            <tr>
            	<th>No. Acción</th>
                <th>No. Auditoría</th>
                <th>Fecha</th>
                <th>Descripción</th>
                <th>Opciones</th>
            </tr>
            <c:forEach var="accionCorrectiva" items="${ accionCorrectiva }">
            <tr>
            	<td>${ accionCorrectiva.numeroAccion }</td>
                <td>${ accionCorrectiva.numeroAuditoria }</td>
                <td>${ accionCorrectiva.fecha }</td>
                <td>${ accionCorrectiva.descripcion }</td>
				<td>
                    <a href="${ urlEditar }/${accionCorrectiva.idAccion}" class="btn btn-success btn-sm" role="button" title="Editar" ><span class="glyphicon glyphicon-pencil"></span></a>
                    <a href="${ urlEliminar }/${accionCorrectiva.idAccion}" onclick='return confirm("¿Estás seguro?")' class="btn btn-danger btn-sm" role="button" title="Eliminar" ><span class="glyphicon glyphicon-trash"></span></a>
                </td>
            </tr>
            </c:forEach>
        </table>
      </div>
	</div>
	
	<jsp:include page="../includes/footer.jsp"></jsp:include>
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
	<script src="${ urlResources }/bootstrap/js/bootstrap.min.js"></script>
	<script src="/sgc/resources/js/bootstrap-4-navbar.js"></script>

</body>
</html>