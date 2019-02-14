<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<spring:url value="/perfil/confirmar_contrasena" var="urlForm"></spring:url>
<link rel="stylesheet" href="${ urlResources }/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/sgc/resources/css/bootstrap-4-navbar.css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

</head>

<body>
	
	<jsp:include page="../includes/header.jsp"></jsp:include>

	<div class="container">
		<br>
			<h5 class="text-center">Nombre del Usuario: ${ usuarioAuth.nombres } ${ usuarioAuth.apellidos }</h5>
			<h6 class="text-center">Departamento: ${ usuarioAuth.departamento.nombre }</h6>
			<h6 class="text-center">Puesto: ${ usuarioAuth.puesto.nombre }</h6>
		<br>
				
		<form:form action="${ urlForm }" method="post" enctype="multipart/form-data" modelAttribute="usuarioActualizar">
            <div class="form-group">
              <form:hidden class="form-control" path="num_empleado" id="num_empleado" required="required" />
              <form:hidden class="form-control" path="nombres" id="nombres" required="required" />
              <form:hidden class="form-control" path="apellidos" id="apellidos" required="required" />
              <form:hidden class="form-control" path="correo" id="correo" required="required" />
              <form:hidden class="form-control" path="id_puesto" id="puesto" required="required" />
              <form:hidden class="form-control" path="id_departamento" id="departamento" required="required" />
              <form:hidden class="form-control" path="id_rol" id="rol" required="required" />
              <form:hidden class="form-control" path="estatus" id="estatus" required="required" />  
			  
              <label for="contrasena">Ingrese su nueva contraseña:</label>
              <form:input type="password" class="form-control" path="contrasena" id="contrasena" required="required" />
              
              <label for="contrasena_dos">Confirme su nueva contraseña:</label>
              
              
            </div>
        <button type="submit" class="btn btn-success" >Continuar</button>
        <a href="${ urlRoot }perfil/index" class="btn btn-danger" role="button" title="Cancelar" >Cancelar</a><br><br>
      </form:form> 
		
	</div>
	<jsp:include page="../includes/footer.jsp"></jsp:include>
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
	<script src="${ urlResources }/bootstrap/js/bootstrap.min.js"></script>
	<script src="/sgc/resources/js/bootstrap-4-navbar.js"></script>

</body>
</html>