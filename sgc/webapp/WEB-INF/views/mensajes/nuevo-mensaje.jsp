<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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
<spring:url value="/mensajes/recibidos" var="urlReci"></spring:url>
<spring:url value="/mensajes/enviados" var="urlEnvi"></spring:url>
<spring:url value="/mensajes/redactar" var="urlReda"></spring:url>


<link rel="stylesheet" href="${ urlResources }/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/sgc/resources/css/bootstrap-4-navbar.css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link href="${urlPublic}/bootstrap/css/theme.css" rel="stylesheet">

<!--  Linea para agregar los íconos de las acciones-->
<link rel="stylesheet" href="${ urlResources }/bootstrap3.7/css/glyphicons.css" rel="stylesheet">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
</head>
<body>

	<script type="text/javascript">
		function verDetalles(evt) {
			var target = evt.srcElement ? evt.srcElement : evt.target;
			if (target.className == 'rec')
				document.location = 'recibidos';
			if (target.className == 'en')
				document.location = 'enviados';
		}
	</script>

	<jsp:include page="../includes/header.jsp"></jsp:include>

	<div class="container theme-showcase" role="main" align="center">
		<!--  <h1 align="center"></h1>-->

		<h3>
			<b>Mensaje</b>
		</h3>

		<div class="table-responsive">

			<table class="table table-striped ">

<%--Area de opciones de mensaje --%>

				<td>
				
					<table  class=" table table-hover table-striped ">
						<tr>
							<td><form method="get" action="${urlReda}"> <button type="submit" class="btn btn-danger">Redactar</button></form></td>
								</td>
						</tr>

						<tr onclick="verDetalles(event)">
						<td class=rec><b><a href="${urlReci}" style='text-decoration:none;color:black;(otros)'>Recibidos</a></b></td>
						</tr>
						
						<tr onclick="verDetalles(event)">
						<td class="en"><b><a href="${urlEnvi}" style='text-decoration:none;color:black;(otros)'>Enviados</a> </b></td>
						</tr>
					</table>
				
				</td>

<%--Formulario de mensaje --%>

				<td>
					<div role="main" align="center"  >


						<form method="post" action="${urlSend}">
							<table  class="table table-hover ">
								<tr>
									<td ><b> Para:</b></td>
									<td><input type="text" name="para" size="65" /></td>
								</tr>
								<tr>
									<td><b> Asunto:</b></td>
									<td><input type="text" name="asunto" size="65" /></td>
								</tr>

								<tr>
									<td><b> Mensaje:</b></td>
									<td><textarea cols="67" rows="10" name="mensaje"></textarea></td>
								</tr>
							</table>

							<br>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button type="submit" class="btn btn-success"
										value="Send E-mail">Enviar Mensaje</button>
								</div>
							</div>
						</form>
					</div></td>
				</tr>
			</table>
		</div>
	</div>

	<hr class="featurette-divider">

	<hr class="featurette-divider">

	<jsp:include page="../includes/footer.jsp"></jsp:include>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
	<script src="${ urlResources }/bootstrap/js/bootstrap.min.js"></script>
	<script src="/sgc/resources/js/bootstrap-4-navbar.js"></script>
</body>
</html>