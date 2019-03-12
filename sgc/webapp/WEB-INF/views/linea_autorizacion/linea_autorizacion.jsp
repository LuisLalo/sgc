<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<spring:url value="/linea-autorizacion" var="urlAgregar" />
<link rel="stylesheet" href="${ urlResources }/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/sgc/resources/css/bootstrap-4-navbar.css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

</head>

<body>
	
	<jsp:include page="../includes/header.jsp"></jsp:include>

	<div class="container">
		<h3 class="text-center font-weight-bold">Linea de Autorización del Departamento de ${ departamentos.nombre }</h3>
		<br />
		
		<h4>Datos del Empleado que se desea cambiar</h4>
		<div class="table-responsive">
        <table class="table table-hover table-striped table-bordered">
            <tr>
                <th>No. Empleado</th>
                <th>Nombre(s)</th>
                <th>Apellidos</th>
                <th>Puesto</th>
            </tr>
            <c:forEach var="niveles" items="${ niveles }">
            <tr>
                <td>${ niveles.numEmpleado }</td>
                <td>${ niveles.nombres }</td>
                <td>${ niveles.apellidos }</td>
                <td>${ niveles.puesto }</td>
            </tr>
            </c:forEach>
        </table>
      </div>
      
      <br />
      <h4>Empleados del Departamento</h4>
		<div class="table-responsive">
        <table class="table table-hover table-striped table-bordered" id="tabla">
            <tr>
            	<th>No. Empleado</th>
                <th>Nombre(s)</th>
                <th>Apellidos</th>
                <th>Puesto</th>
                <th>Opciones</th>
            </tr>
            <c:forEach var="usuarios" items="${ usuarios }">
            <tr>
                <td>${ usuarios.num_empleado }</td>
                <td>${ usuarios.nombres }</td>
                <td>${ usuarios.apellidos }</td>
                <td>${ usuarios.puesto.nombre }</td>
                <td>
                    <a href="${ urlAgregar }/cambiar2/${ usuarios.departamento.ruta }/${ usuarios.num_empleado }" class="btn btn-success btn-sm" role="button" title="Agregar" >Cambiar</a>
                </td>
            </tr>
            </c:forEach>
        </table>
      </div>
	</div>
	<jsp:include page="..//includes/footer.jsp"></jsp:include>
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
	<script src="${ urlResources }/bootstrap/js/bootstrap.min.js"></script>
	<script src="/sgc/resources/js/bootstrap-4-navbar.js"></script>

</body>
</html>