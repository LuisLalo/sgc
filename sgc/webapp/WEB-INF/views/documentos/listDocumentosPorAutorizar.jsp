<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">    
    <meta name="description" content="">
    <meta name="author" content="">
	<title>UABC | Tesorer�a</title>

<spring:url value="/resources" var="urlResources"></spring:url>
<spring:url value="/" var="urlRoot"></spring:url>
<spring:url value="/documentos/editar" var="urlEditar" />
<spring:url value="/documentos/autorizar" var="urlAutorizar" />
<spring:url value="/documentos/eliminar" var="urlEliminar" />
<spring:url value="/documentos/devolver" var="urlDevolver" />
<link rel="stylesheet" href="${ urlResources }/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/sgc/resources/css/bootstrap-4-navbar.css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!--  L�nea para agregar los �conos de las acciones-->
<link rel="stylesheet" href="${ urlResources }/bootstrap3.7/css/glyphicons.css" rel="stylesheet">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

</head>

<body>
	
	<jsp:include page="../includes/header.jsp"></jsp:include>

	<div class="container">
		<h3 class="text-center font-weight-bold">Listado de Documentos Pendientes de Autorizar</h3>
		
		<c:if test="${ mensaje!=null }">
      <div class="alert alert-success" role="alert">${ mensaje }</div>
      </c:if>
	
      <div class="table-responsive">
        <table class="table table-hover table-striped table-bordered">
            <tr>
                <th>Nombre</th>
                <th>Descripci�n</th>
                <th>Fecha de Creaci�n</th>
                <th>Clasificaci�n</th>
                <th>Departamento</th>
              <!--    <th>Estatus</th>-->
                <th>Opciones</th>
            </tr>
            <c:forEach var="documentos" items="${ documentos }">
            <tr>
                <td>${ documentos.nombre }</td>
                <td>${ documentos.descripcion }</td>
                <td>${ documentos.fecha_creacion }</td>
                <td>${ documentos.tipoDocumento.nombre }</td>
                <td>${ documentos.departamento.nombre }</td>
           <!--      <c:choose>
                	<c:when test="${ documentos.estatus eq '1'}">
                		<td><span class="badge badge-success">${ documentos.estatus }</span></td>
                	</c:when>
                	<c:otherwise>
                		<td><span class="badge badge-danger">${ documentos.estatus }</span></td>
                	</c:otherwise>
                </c:choose> -->
                <td>
                    <a href="${ urlAutorizar }/editar/${documentos.idDocumento}" class="btn btn-success btn-sm" role="button" title="Editar" ><span class="glyphicon glyphicon-pencil"></span></a>
                    <a href="${ urlAutorizar }/${documentos.idDocumento}" onclick='return confirm("�Est�s seguro que deseas aprobar este documento?")' class="btn btn-success btn-sm" role="button" title="Aprobar" ><span class="glyphicon glyphicon-ok"></span></a>
                    <a href="${ urlResources }${ documentos.ruta }" class="btn btn-success btn-sm" role="button" title="Visualizar Documento" ><span class="glyphicon glyphicon-save-file"></span></a>
                    <a href="${ urlDevolver }/${documentos.idDocumento}" onclick='return confirm("�Est�s seguro que deseas devolver este documento?")' class="btn btn-danger btn-sm" role="button" title="Devolver" ><span class="glyphicon glyphicon-repeat"></span></a>
                    <a href="${ urlEliminar }/${documentos.idDocumento}" onclick='return confirm("�Est�s seguro que deseas eliminar este documento?")' class="btn btn-danger btn-sm" role="button" title="Eliminar" ><span class="glyphicon glyphicon-trash"></span></a>
                </td>
            </tr>
            </c:forEach>
          
           
        </table>
      </div>
	</div>
	
	<jsp:include page="../includes/footer.jsp"></jsp:include>
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
	<script src="${ urlResources }/bootstrap/js/bootstrap.min.js"></script>
	<script src="/sgc/resources/js/bootstrap-4-navbar.js"></script>

</body>
</html>