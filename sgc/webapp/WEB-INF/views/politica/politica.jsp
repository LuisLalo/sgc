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
	
		<h3 class="text-center font-weight-bold">Política de Calidad</h3>
		<br>
		<div class="container text-justify">
		<ul class="list-unstyled">
			<h4>
				<li>Son compromisos del Patronato:</li>
				<ul>
					<li>Administrar, gestionar y vigilar objetivamente los recursos para apoyar las necesidades de la Universidad de manera económica, eficaz, eficiente y oportuna, buscando <span class="text-danger">en todo momento</span> la innovación de los procesos, <span class="text-danger">cumpliendo con la</span> normatividad aplicable <span class="text-danger">y el cuidado al medio ambiente</span>.</li>

					<li>Rendir cuentas con calidad y transparencia a la comunidad universitaria y a la sociedad en general sobre la gestión y administración del Patrimonio Universitario.</li>
				</ul>
			</h4>		
		</ul>
		</div>
		<br>
		<br>
		<h3 class="text-center font-weight-bold">Objetivos de Calidad</h3>
		<br>
		<div class="container text-justify">
		<h4>
			<ol>
				<li>Administrar y gestionar los recursos, vigilando objetivamente que su uso sea de manera económica, eficaz, eficiente y oportuna.</li>
				<li>Apoyar las necesidades de recursos de la comunidad universitaria.</li>
				<li>Rendir cuentas con calidad y transparencia a la comunidad en general.</li>
				<li>Mantener un Sistema de Gestión de Calidad basado en la mejora continua de nuestros sistemas, procesos y servicios.</li>
				<li>Cumplir con la normatividad aplicable.</li>
				<li>Capacitar constantemente a nuestro personal.</li>
				<li>Coadyuvar con el cuidado al medio ambiente.</li>
			</ol>
		</h4>
		</div>
		
	<blockquote class="blockquote text-right">Rev. 11 Jul 17</blockquote>	
	</div>
	<jsp:include page="../includes/footer.jsp"></jsp:include>
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
	<script src="${ urlResources }/bootstrap/js/bootstrap.min.js"></script>
	<script src="/sgc/resources/js/bootstrap-4-navbar.js"></script>

</body>
</html>