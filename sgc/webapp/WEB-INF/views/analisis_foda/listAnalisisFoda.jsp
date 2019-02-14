<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<link rel="stylesheet" href="${ urlResources }/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/sgc/resources/css/bootstrap-4-navbar.css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

</head>

<body>
	
	<jsp:include page="../includes/header.jsp"></jsp:include>

	<div class="container">
		<h3 class="text-center font-weight-bold">Análisis FODA</h3>
		
		<c:if test="${ mensaje!=null }">
      		<div class="alert alert-success" role="alert">${ mensaje }</div>
        </c:if>
      	
      	<sec:authorize access="hasAnyAuthority('SGC')">
        	<a href="${ urlRoot }analisis_foda/crear" class="btn btn-success" role="button" title="Subir Nuevo Análisis de Riesgo" >Nuevo Análisis de Riesgo</a><br><br>
		</sec:authorize>
		<sec:authorize access="hasAnyAuthority('Area')">
			<a href="${ urlRoot }analisis_foda/crear" class="btn btn-success" role="button" title="Subir Nuevo Análisis de Riesgo" >Nuevo Análisis de Riesgo</a><br><br>
		</sec:authorize>
		<div class="table-responsive">
	        <table class="table table-hover table-striped table-bordered">
    	        <tr>
        	        <th>No.</th>
            	    <th>Departamento</th>
	            </tr>
    	        <c:forEach var="departamentos" items="${ departamentos }">
        	    <tr>
            		<td>${ departamentos.id_departamento }</td>
            		<td><a class="text-dark" href="${ urlRoot }analisis_foda/${departamentos.id_departamento}">Análisis FODA de ${ departamentos.nombre }</a></td>
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