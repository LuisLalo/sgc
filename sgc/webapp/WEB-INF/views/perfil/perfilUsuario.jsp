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
<link rel="stylesheet" href="${ urlResources }/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/sgc/resources/css/bootstrap-4-navbar.css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

</head>

<body>
	
	<jsp:include page="../includes/header.jsp"></jsp:include>

	<div class="container">
		<c:if test="${ mensaje!=null }">
     		<div class="alert alert-success" role="alert">${ mensaje }</div>
      	</c:if>
		<div class="row">
      			
			<div class="col-4">
				<p class="text-center">
					<img alt="Rectoría" src="${ urlResources }/images/person-green.png" width="150" height="150">
				</p>
				<p class="text-center">
				<a href="${ urlRoot }perfil/cambiar_contrasena" class="btn btn-outline-success" role="button" title="Cambiar Contraseña" >Cambiar Contraseña</a><br><br>
				
				<br>
				<!-- 
				<h5 class="text-center">Archivos Recientes:</h5>
				<p class="text-center">GC-N2-001</p>
				<p class="text-center">GC-N4-005</p>
				<p class="text-center">Encuesta Clima Laboral--></p> 
			</div>
			<div class="col-8">
				<br><br>
				<h4 class="text-center">${ usuarioAuth.nombres } ${ usuarioAuth.apellidos }</h4>
				<h6 class="text-center">${ usuarioAuth.correo }</h6>
				<h6 class="text-center">${ usuarioAuth.departamento.nombre }</h6>
				<h6 class="text-center">${ usuarioAuth.puesto.nombre }</h6>
				<br>
				
				<h5 class="text-center">Capacitaciones Recibidas:</h5>
				<h6 class="text-center">Curso Auditor Interno ISO 9001:2015</h6>
				<h6 class="text-center">ATR Introducción al ISO 9001:2015 Análisis y Riesgos, Planeación</h6>
				<h6 class="text-center">Google Drive: Administración y Control de Documentos</h6>
			</div>
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