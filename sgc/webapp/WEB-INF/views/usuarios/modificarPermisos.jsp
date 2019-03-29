<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<spring:url value="/usuarios/permisos" var="urlEditar" />
<spring:url value="/menu/guardar" var="urlForm"></spring:url>
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
		
		<br>
		<h3 class="text-center font-weight-bold">Permisos del usuario: ${ usuarioConsulta.nombres } ${ usuarioConsulta.apellidos }</h3>
		<br>
		<h4 class="text-center font-weight-bold">Sección: ${ permisoSeccion.menu.nombre }</h4>
		<br>
		
	<spring:hasBindErrors name="usuario">
		<div class="alert alert-danger" role="alert">
			Por favor corrija los siguientes errores:
			<ul>
				<c:forEach var="error" items="${ errors.allErrors }">
					<li><spring:message message="${ error }" /></li>
				</c:forEach>
			</ul>
		</div>
	</spring:hasBindErrors>

	<div class="table-responsive">
        <table class="table table-hover table-striped table-bordered">
            <tr>
                <th>Sección</th>
                <th>Estatus del Permiso</th>
                <th>Acciones</th>
            </tr>
            <c:forEach var="permiso" items="${ permiso }">
            <tr>
                <td>${ permiso.menu.nombre }</td>
                <c:choose>
                	<c:when test="${ permiso.menu.idSubmenu eq 0 }">
	                	<c:choose>
    	        	    	<c:when test="${ permiso.estatusPermiso.nombre eq 'Activo'}">
        		        		<td><span class="badge badge-success">${ permiso.estatusPermiso.nombre }</span></td>
    	    	        	</c:when>
	            	    	<c:otherwise>
                				<td><span class="badge badge-danger">${ permiso.estatusPermiso.nombre }</span></td>
	            	    	</c:otherwise>
    	    	        </c:choose>
    		            <c:choose>
	        	        	<c:when test="${ permiso.estatusPermiso.nombre eq 'Activo'}">
                				<td>
                					<a href="${ urlEditar }/${ permiso.numEmpleado }/cambiar/${ permisoSeccion.menu.idMenu }/seccion/${ permiso.idPermiso }" onclick='return confirm("¿Deseas cambiar el permiso de la sección?")' class="btn btn-danger btn-sm" role="button" title="Permisos de la Sección" ><span class="glyphicon glyphicon-remove"></span></a>
            	    			
	        	        		</td>
    		            	</c:when>
	    	            	<c:otherwise>
            	    			<td>
                					<a href="${ urlEditar }/${ permiso.numEmpleado }/cambiar/${ permisoSeccion.menu.idMenu }/seccion/${ permiso.idPermiso }" onclick='return confirm("¿Deseas cambiar el permiso de la sección?")' class="btn btn-success btn-sm" role="button" title="Permisos de la Sección" ><span class="glyphicon glyphicon-ok"></span></a>

        	        			</td>
    	            		</c:otherwise>
	                	</c:choose>
	                </c:when>
	                <c:otherwise>
	                	<c:choose>
	                		<c:when test="${ permiso.menu.idSubmenu eq 1 }">
	                			<c:choose>
	                				<c:when test="${ permiso.estatusPermiso.nombre eq 'Activo'}">
        		        				<td><span class="badge badge-success">${ permiso.estatusPermiso.nombre }</span></td>
    	    	        			</c:when>
	            	    			<c:otherwise>
                						<td><span class="badge badge-danger">${ permiso.estatusPermiso.nombre }</span></td>
	            	    			</c:otherwise>
	                			</c:choose>
	                			<c:choose>
			        	        	<c:when test="${ permiso.estatusPermiso.nombre eq 'Activo'}">
        		        				<td>
                							<a href="${ urlEditar }/${ permiso.numEmpleado }/cambiar/${ permisoSeccion.menu.idMenu }/seccion/${ permiso.idPermiso }" onclick='return confirm("¿Deseas cambiar el permiso de la sección?")' class="btn btn-danger btn-sm" role="button" title="Permisos de la Sección" ><span class="glyphicon glyphicon-remove"></span></a>
            	    						<a href="${ urlEditar }/${ permiso.numEmpleado }/seccion/${ permisoSeccion.menu.idMenu }/seccion/${ permiso.idPermiso }" class="btn btn-success btn-sm" role="button" title="Permisos individuales de la sección" ><span class="glyphicon glyphicon-th-list"></span></a>
	        	        				</td>
    		            			</c:when>
	    	    	        		<c:otherwise>
            			    			<td>
        	        						<a href="${ urlEditar }/${ permiso.numEmpleado }/cambiar/${ permisoSeccion.menu.idMenu }/seccion/${ permiso.idPermiso }" onclick='return confirm("¿Deseas cambiar el permiso de la sección?")' class="btn btn-success btn-sm" role="button" title="Permisos de la Sección" ><span class="glyphicon glyphicon-ok"></span></a>
    	        	    					<a href="${ urlEditar }/${ permiso.numEmpleado }/seccion/${ permisoSeccion.menu.idMenu }/seccion/${ permiso.idPermiso }" class="btn btn-success btn-sm" role="button" title="Permisos individuales de la sección" ><span class="glyphicon glyphicon-th-list"></span></a>
	        	        				</td>
    	            				</c:otherwise>
	                			</c:choose>
	                		</c:when>
	                	</c:choose>
	                </c:otherwise>
                </c:choose>
                
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