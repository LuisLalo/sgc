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
<link href="${ urlResources }/bootstrap/css/mdb.min.css" rel="stylesheet">
<link rel="stylesheet" href="${ urlResources }/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/sgc/resources/css/bootstrap-4-navbar.css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link href="${ urlResources }/bootstrap/css/theme.css" rel="stylesheet">

<style>
.contenedor {
	position: relative;
	display: inline-block;
	text-align: center;
}

.texto-encima {
	position: absolute;
	top: 10px;
	left: 10px;
}

.centrado {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
}
</style>
</head>

<body>
	
	<jsp:include page="../includes/header.jsp"></jsp:include>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$(document).on('mouseenter', '.contenedor', function() {
				$(this).find(":button").show();

			}).on('mouseleave', '.contenedor', function() {
				$(this).find(":button").hide();

			});
		});
	</script>
	
	<div class="container">
		<h2 class="text-center font-weight-bold">Formatos</h2>
		<br>
		
		<div class="row">
			<c:forEach var="departamentos" items="${departamentos}">
				<div class="col-xs-12 col-sm-6 col-md-3">
				<div class="contenedor">
					<div class="view overlay">
					  <a href="${ urlRoot }formatos/${departamentos.id_departamento}">
						<img src="${ urlResources }/images/${departamentos.imagen}" alt="..."  class="img-fluid img-rounded" onmouseover="enfoque(this)"onmouseout="noenfoque(this)">
						<div class="mask flex-center waves-effect waves-light rgba-white-strong">
							<form method="get" action="${ urlRoot }formatos/${ departamentos.ruta}/${departamentos.id_departamento}">
								
							</form>
						</div>
					  </a>
					</div>
				</div>
			</div>
			</c:forEach>
		</div>
		
	</div>
	<jsp:include page="..//includes/footer.jsp"></jsp:include>
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
	<script src="${ urlResources }/bootstrap/js/bootstrap.min.js"></script>
	<script src="/sgc/resources/js/bootstrap-4-navbar.js"></script>

</body>
</html>