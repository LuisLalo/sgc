<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<spring:url value="/resources" var="urlResources"></spring:url>

<!-- Encabezado -->
	<div class="container">
		<div class="row">
		<img src="${ urlResources }/images/Banner.png" class="rounded mx-auto d-block" alt="Inicio" width="720" height="123">
		</div>
	</div>

<!-- Datos de la persona que está dentro del SGC -->
	<div class="container">
		<p class="text-right text-muted">${ usuarioAuth.nombres } ${ usuarioAuth.apellidos }<br />
		<sec:authentication property="principal.username"/></p>
    </div>

<!-- Menu -->
<spring:url value="/" var="urlRoot" />
<spring:url value="/resources" var="urlResources"></spring:url>

${ menuCompleto }
<br>
<!-- 
<div class="container">
  <nav class="navbar navbar-light">
    <a class="nav-item nav-link" href="#">Atrás</a>
    <form class="form-inline">
      <input class="form-control mr-sm-2" type="search" placeholder="Buscar" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Buscar</button>
    </form>
  </nav>
</div> -->