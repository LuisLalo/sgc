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
<spring:url value="/mensajes/nuevo-mensaje" var="urlReda"></spring:url>


<link rel="stylesheet" href="${ urlResources }/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/sgc/resources/css/bootstrap-4-navbar.css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link href="${urlResources}/bootstrap/css/theme.css" rel="stylesheet">

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
			<b></b>
		</h3>

		<div class="table-responsive">

			<table class="table table-striped table-hover" >
				<tr>
					<td>
						<table width="100" style="float: left; position: sticky "
							class=" table table-hover">
							<tr>
								<td><form method="get" action="${urlReda}">
										<button type="submit" class="btn btn-danger">Redactar</button>
									</form></td>
								</td>

							</tr>
							<tr onclick="verDetalles(event)">
								<td class=rec><b><a href="${urlReci}"
										style='text-decoration: none; color: black;(otros)'>Recibidos</a></b></td>

							</tr>
							<tr onclick="verDetalles(event)">
								<td class="en"><b><a href="${urlEnvi}"
										style='text-decoration: none; color: black;(otros)'>Enviados</a>
								</b></td>

							</tr>
						</table>
					</td>
					<td><table
							class=" table table-hover table-striped table-bordered"
							style="float: right; position: sticky">
							<div>
								<h3>
									<b>Problemas con procedimientos</b>
								</h3>
							</div>
							<table width=1000">
								<tr>
									<td>
										<div>
											<h4>
												<i>Andrés Barraza</i>
											</h4>
										</div>
									</td>
									<td>
										<div align="right">Martes, 19 Junio 2018 02:34:56</div>
									</td>
							</table>
							<br>
							<div align="center" style="background-color: #ffff99">
								<table width="600" align="left">
									<tr>
										<td>
											<p>
											<h4>"Lorem ipsum dolor sit amet, consectetur adipiscing
												elit, sed do eiusmod tempor incididunt ut labore et dolore
												magna aliqua. Ut enim ad minim veniam, quis nostrud
												exercitation ullamco laboris nisi ut aliquip ex ea commodo
												consequat. Duis aute irure dolor in reprehenderit in
												voluptate velit esse cillum dolore eu fugiat nulla pariatur.
												Excepteur sint occaecat cupidatat non proident, sunt in
												culpa qui officia deserunt mollit anim id est laborum."</h4>
											</p>
										</td>
									</tr>
									<tr>
										<td>
											<div align="right">
							<form method="get" action="${urlReda}"> <button type="submit" class="btn btn-success">Responder</button></form>
							</div>
								<div align="right">
							
										</td>
										<td>
											<div align="right">
							<form method="get" action="${urlReda}"> <button type="submit" class="btn btn-danger">Reenviar</button></form>
							</div>
								<div align="right">
							
										</td>
									</tr>
								</table>
							</div>
						</table></td>
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