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
	<title>UABC | Tesorer�a</title>

<spring:url value="/resources" var="urlResources"></spring:url>
<spring:url value="/menu/editar" var="urlEditar"></spring:url>
<spring:url value="/menu/opcion" var="urlOpcion"></spring:url>
<spring:url value="/" var="urlRoot"></spring:url>
<link rel="stylesheet" href="${ urlResources }/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/sgc/resources/css/bootstrap-4-navbar.css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<!--  L�nea para agregar los �conos de las acciones-->
<link rel="stylesheet" href="${ urlResources }/bootstrap3.7/css/glyphicons.css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
</head>

<body>
	
	<jsp:include page="../includes/header.jsp"></jsp:include>

	<div class="container">
		<h3 class="text-center font-weight-bold">Opciones de Menu</h3>
		
		<c:if test="${ mensaje!=null }">
      		<div class="alert alert-success" role="alert">${ mensaje }</div>
        </c:if>
      
		<!-- Button trigger modal -->
		<a href="${ urlRoot }menu/crear" class="btn btn-success" role="button" title="Crear Nueva Opci�n de Menu">
  			Nuevo Opci�n
		</a>
	
		<br>
		
		<!-- Modal 
		<div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  			<div class="modal-dialog" role="document">
    			<div class="modal-content">
    				Header de la ventana
      				<div class="modal-header">
        				<h5 class="modal-title" id="exampleModalLongTitle">Generar Nueva Secci�n</h5>
        				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
          					<span aria-hidden="true">&times;</span>
        				</button>
      				</div>
      				Contenido de la ventana
      				<div class="modal-body">
      				<p>Hola</p>	
      					
      					
       				</div>
        			Contenido del footer
      				<div class="modal-footer">
      					<button type="button" class="btn btn-success">Guardar</button>
        				<button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
      				</div>
    			</div>
  			</div>
		</div>-->
		
		<br>
		
		<!-- Se crea la tabla con el listado del menu -->
		<div class="table-responsive">
        <table class="table table-hover table-striped table-bordered">
            <tr>
                <th>Nombre de la Opci�n del Menu</th>
                <th>Acciones</th>
            </tr>
            <c:forEach var="listaMenu" items="${ listaMenu }">
            <tr>
                <td>${ listaMenu.nombre }</td>
                <td>
                    <a href="${ urlEditar }/${ listaMenu.idMenu }" class="btn btn-success btn-sm" role="button" title="Editar" ><span class="glyphicon glyphicon-pencil"></span></a>
                    <a href="${ urlOpcion }/${ listaMenu.idMenu }" class="btn btn-success btn-sm" role="button" title="Editar Secciones"><span class="glyphicon glyphicon-th-list"></span></a>
				</td>
			</tr>                  
            </c:forEach>
        </table>
      </div>
		
		
	</div>
	<br>
	
	
	<jsp:include page="../includes/footer.jsp"></jsp:include>
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
	<script src="${ urlResources }/bootstrap/js/bootstrap.min.js"></script>
	<script src="/sgc/resources/js/bootstrap-4-navbar.js"></script>

</body>
</html>