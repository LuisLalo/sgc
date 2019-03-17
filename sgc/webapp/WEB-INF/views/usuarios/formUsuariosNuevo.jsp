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
<spring:url value="/usuarios/guardar" var="urlForm"></spring:url>
<link rel="stylesheet" href="${ urlResources }/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/sgc/resources/css/bootstrap-4-navbar.css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

</head>

<body>
	
	<jsp:include page="../includes/header.jsp"></jsp:include>
	
	<div class="container">
		
		<br>
		<h3 class="text-center font-weight-bold">Datos del Usuario</h3>
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

      <form:form action="${ urlForm }" method="post" enctype="multipart/form-data" modelAttribute="usuarioActualizar">
        <div class="row">
          <div class="col-sm-2">
            <div class="form-group">
              <label for="numero empleado">Número de Empleado</label>
              <form:input type="text" class="form-control" path="num_empleado" id="num_empleado" required="required" />
            </div>  
          </div>
          <div class="col-sm-3">
            <div class="form-group">
              <label for="nombres">Nombres</label>
              <form:input type="text" class="form-control" path="nombres" id="nombres" required="required" />
            </div>  
          </div>
          <div class="col-sm-3">
            <div class="form-group">
              <label for="apellidos">Apellidos</label>
              <form:input type="text" class="form-control" path="apellidos" id="apellidos" required="required" />
            </div>  
          </div>
          <div class="col-sm-4">
            <div class="form-group">
              <label for="correos">Correo</label>
              <form:input type="email" class="form-control" path="correo" id="correo" required="required" />
            </div>  
          </div>
		</div>
        <div class="row">
          <div class="col-sm-3">
            <div class="form-group">
              <label for="contrasena">Contraseña</label>
              <form:input type="password" class="form-control" path="contrasena" id="contrasena" required="required" />
            </div>  
          </div>
          <div class="col-sm-3">
            <div class="form-group">
              <label for="puesto" class="control-label">Puesto</label>              
              <form:select id="puesto" path="id_puesto" class="form-control" >
                <c:forEach var="puestos" items="${ puestos }">
                	<form:option value="${ puestos.id_puesto }">${ puestos.nombre }</form:option>
                </c:forEach>
              </form:select>             
            </div> 
          </div>
          <div class="col-sm-3">
            <div class="form-group">
              <label for="departamento" class="control-label">Departamento</label>              
              <form:select id="departamento" path="id_departamento" class="form-control">
                <c:forEach var="departamentos" items="${ departamentos }">
                	<form:option value="${ departamentos.id_departamento }">${ departamentos.nombre }</form:option>
                </c:forEach>
              </form:select>             
            </div> 
          </div>
          <div class="col-sm-3">
            <div class="form-group">
              <label for="rol" class="control-label">Rol</label>              
              <form:select id="rol" path="id_rol" class="form-control">
                <c:forEach var="roles" items="${ roles }">
                	<form:option value="${ roles.id_rol }">${ roles.descripcion }</form:option>
                </c:forEach>
              </form:select>             
            </div> 
          </div>
          
          <div class="col-sm-3">
            <div class="form-group">
              <label for="estatus" class="control-label">Estatus</label>              
              <form:select id="genero" path="estatus" class="form-control">
                <c:forEach var="estatusUsuario" items="${ estatusUsuario }">
                	<form:option value="${ estatusUsuario.idEstatusUsuario }">${ estatusUsuario.nombre }</form:option>
                </c:forEach>
              </form:select>             
            </div> 
          </div>
        </div>

        
        <button type="submit" class="btn btn-success" >Guardar</button>
        <a class="btn btn-danger" href="${ urlRoot }usuarios/index" role="button">Cancelar</a>
      </form:form> 
		
	</div>
	
	<jsp:include page="../includes/footer.jsp"></jsp:include>
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
	<script src="${ urlResources }/bootstrap/js/bootstrap.min.js"></script>
	<script src="/sgc/resources/js/bootstrap-4-navbar.js"></script>

</body>
</html>