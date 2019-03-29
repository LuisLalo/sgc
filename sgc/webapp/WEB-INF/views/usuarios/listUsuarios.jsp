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
	<title>UABC | Tesorería</title>

<spring:url value="/resources" var="urlResources"></spring:url>
<spring:url value="/" var="urlRoot"></spring:url>
<spring:url value="/usuarios/editar" var="urlEditar" />
<spring:url value="/usuarios/permisos" var="urlPermisos" />
<spring:url value="/usuarios/restablecer" var="urlRestablecer" />
<spring:url value="/usuarios/eliminar" var="urlEliminar" />
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
		<h3 class="text-center font-weight-bold">Listado de Usuarios</h3>
		
		<c:if test="${ mensaje!=null }">
      <div class="alert alert-success" role="alert">${ mensaje }</div>
      </c:if>
      
      <div id="contenido">
        
    </div>
      <a href="${ urlRoot }usuarios/crear" class="btn btn-success" role="button" title="Crear Nuevo Usuario" >Nuevo Usuario</a><br><br>
	
      <div class="table-responsive">
        <table class="table table-hover table-striped table-bordered">
            <tr>
                <th>No. Empl.</th>
                <th>Nombres</th>
                <th>Apellidos</th>
                <th>Correo</th>
                <th>Puesto</th>
                <th>Departamento</th>
                <th>Rol</th>
                <th>Estatus</th>
                <th>Opciones</th>
            </tr>
            <c:forEach var="usuarios" items="${ usuarios }">
            <tr>
                <td>${ usuarios.num_empleado }</td>
                <td>${ usuarios.nombres }</td>
                <td>${ usuarios.apellidos }</td>
                <td>${ usuarios.correo }</td>
                <td>${ usuarios.puesto.nombre }</td>
                <td>${ usuarios.departamento.nombre }</td>
                <td>${ usuarios.rol.descripcion }</td>
                <c:choose>
                	<c:when test="${ usuarios.estatus.nombre eq 'Activo'}">
                		<td><span class="badge badge-success">${ usuarios.estatus.nombre }</span></td>
                	</c:when>
                	<c:otherwise>
                		<td><span class="badge badge-danger">${ usuarios.estatus.nombre }</span></td>
                	</c:otherwise>
                </c:choose>
                <td>
                    <a href="${ urlEditar }/${usuarios.num_empleado}" class="btn btn-success btn-sm" role="button" title="Editar" ><span class="glyphicon glyphicon-pencil"></span></a>
                    <a href="${ urlPermisos }/${usuarios.num_empleado}" class="btn btn-success btn-sm" role="button" title="Permisos" ><span class="glyphicon glyphicon-th-list"></span></a>
                    <a href="${ urlRestablecer }/${usuarios.num_empleado}" class="btn btn-success btn-sm" role="button" title="Restablecer Contraseña" ><span class=" glyphicon glyphicon-repeat"></span></a>
                    <a href="${ urlEliminar }/${usuarios.num_empleado}" onclick='return confirm("¿Estás seguro?")' class="btn btn-danger btn-sm" role="button" title="Eliminar" ><span class="glyphicon glyphicon-trash"></span></a>
                </td>
            </tr>
            </c:forEach>
           
        </table>
      </div>
	</div>
	
	<jsp:include page="../includes/footer.jsp"></jsp:include>
	<script src="${ urlResources }/js/prueba.js" type="text/javascript"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
	<script src="${ urlResources }/bootstrap/js/bootstrap.min.js"></script>
	<script src="/sgc/resources/js/bootstrap-4-navbar.js"></script>

</body>
</html>