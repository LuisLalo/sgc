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
<!-- Barra de navegación -->

<div class="container">
		<sec:authorize access="hasAnyAuthority('SGC')">
 			<!-- Barra de Navegación Administrador SGC -->
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <a class="navbar-brand" href="${ urlRoot }">
                	<img src="${ urlResources }/images/ISOTIPO_Una-tinta_FONDO-OSCURO.png" width="50" height="30" class="d-inline-block align-top" alt="Inicio">
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNavDropdown">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="${ urlRoot }perfil/index">Perfil <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Estatus del SGC
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                <li><a class="dropdown-item" href="${ urlRoot }programa_actividades/index">Programa de Actividades</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }reunion_directiva/index">Reunión Directiva</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }auditoria_interna/index">Resultados Auditoría Interna</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }auditoria_externa/index">Resultados Auditoría Externa</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }clima_laboral/index">Encuesta Clima Laboral</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }reunion_trabajo/index">Reuniones de Trabajo</a></li>
                            </ul>
                        </li>
						<li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Documentos
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                <li><a class="dropdown-item" href="${ urlRoot }politica/index">Política y Objetivos</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }manual/index">Manual de Gestión del SGC</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }gestion_conocimiento/index">Gestión del Conocimiento</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }procedimientos/index">Procedimientos</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }instructivos/index">Instructivos</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }formatos/index">Formatos</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }documentos_varios/index">Documentos Varios</a></li>
                            </ul>                               
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Acciones
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                <li><a class="dropdown-item" href="${ urlRoot }accion_correctiva/index">Acciones Correctivas</a></li>        
                                <li><a class="dropdown-item dropdown-toggle" href="#">Análisis de Entorno</a>
                                    <ul class="dropdown-menu">
                                        <li><a class="dropdown-item" href="${ urlRoot }analisis_riesgo/index">Análisis de Riesgo</a></li>
                                        <li><a class="dropdown-item" href="${ urlRoot }analisis_foda/index">Análisis FODA</a></li>
										<li><a class="dropdown-item" href="${ urlRoot }plan_accion/index">Plan de Acción</a></li>
									</ul>
								</li>
								<li><a class="dropdown-item" href="${ urlRoot }proyecto_mejora/index">Proyectos de Mejora</a></li>
							</ul>
						</li>
						<li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Indicadores
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            	<li><a class="dropdown-item" href="${ urlRoot }tablero_resultados/index">Tablero de Resultados</a></li>
                            	<li><a class="dropdown-item" href="${ urlRoot }actualizacion_indicador/index">Actualización de Indicadores</a></li>
                            	<li><a class="dropdown-item" href="${ urlRoot }nuevo_indicador/index">Registrar Nuevo Indicador</a></li>
                            	<li><a class="dropdown-item" href="${ urlRoot }historial/index">Historial</a></li>
                            </ul>
						</li>	
						<li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Comunicación
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
								<li><a class="dropdown-item" href="${ urlRoot }mensajes/nuevo">Nuevo Mensaje</a></li>
								<li><a class="dropdown-item" href="${ urlRoot }mensajes/enviados">Mensajes Enviados</a></li>
								<li><a class="dropdown-item" href="${ urlRoot }mensajes/recibidos">Mensajes Recibidos</a></li>
							</ul>
						</li>
						<li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Herramientas
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            	<li><a class="dropdown-item" href="${ urlRoot }usuarios/index">Usuarios</a></li>
                            	<li><a class="dropdown-item" href="${ urlRoot }documentos/autorizar">Documentos por Autorizar</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }documentos/index">Nuevo Documento</a></li>
                            	<li><a class="dropdown-item" href="${ urlRoot }linea-autorizacion/index">Linea de autorización</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }menu/index">Menu</a></li>
                                <li><a class="dropdown-item dropdown-toggle" href="#">Capacitación</a>
                                    <ul class="dropdown-menu">
                                        <li><a class="dropdown-item" href="${ urlRoot }capacitacion/alta">Alta</a></li>
                                        <li><a class="dropdown-item" href="${ urlRoot }capacitacion/registro">Registro</a></li>
                                        <li><a class="dropdown-item" href="${ urlRoot }capacitacion/consultar">Consultar</a></li>
                                    </ul>
                                </li>
                                <li><a class="dropdown-item" href="${ urlRoot }convocatoria/index">Convocatoria</a></li>
                            </ul>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${ urlRoot }logout">Cerrar Sesión<span class="sr-only">(current)</span></a>
                        </li>
                    </ul>
                </div>
            </nav>
		</sec:authorize>
            
        <sec:authorize access="hasAnyAuthority('Area')">
            <!-- Barra de Navegación Administrador de Área -->
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <a class="navbar-brand" href="${ urlRoot }">
                	<img src="${ urlResources }/images/ISOTIPO_Una-tinta_FONDO-OSCURO.png" width="50" height="30" class="d-inline-block align-top" alt="Inicio">
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNavDropdown">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="${ urlRoot }perfil/index">Perfil <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Estatus del SGC
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                <li><a class="dropdown-item" href="${ urlRoot }programa_actividades/index">Programa de Actividades</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }reunion_directiva/index">Reunión Directiva</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }auditoria_interna/index">Resultados Auditoría Interna</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }auditoria_externa/index">Resultados Auditoría Externa</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }clima_laboral/index">Encuesta Clima Laboral</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }reunion_trabajo/index">Reuniones de Trabajo</a></li>
                            </ul>
                        </li>
						<li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Documentos
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                <li><a class="dropdown-item" href="${ urlRoot }politica/index">Política y Objetivos</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }manual/index">Manual de Gestión del SGC</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }gestion_conocimiento/index">Gestión del Conocimiento</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }procedimientos/index">Procedimientos</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }instructivos/index">Instructivos</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }formatos/index">Formatos</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }documentos_varios/index">Documentos Varios</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }documentos/index">Nuevo Documento</a></li>
                            </ul>                               
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Acciones
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                <li><a class="dropdown-item" href="${ urlRoot }accion_correctiva/index">Acciones Correctivas</a></li>        
                                <li><a class="dropdown-item dropdown-toggle" href="#">Análisis de Entorno</a>
                                    <ul class="dropdown-menu">
                                        <li><a class="dropdown-item" href="${ urlRoot }analisis_riesgo/index">Análisis de Riesgo</a></li>
                                        <li><a class="dropdown-item" href="${ urlRoot }analisis_foda/index">Análisis FODA</a></li>
										<li><a class="dropdown-item" href="${ urlRoot }plan_accion/index">Plan de Acción</a></li>
									</ul>
								</li>
								<li><a class="dropdown-item" href="${ urlRoot }proyecto_mejora/index">Proyectos de Mejora</a></li>
							</ul>
						</li>
						<li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Indicadores
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            	<li><a class="dropdown-item" href="${ urlRoot }tablero_resultados/index">Tablero de Resultados</a></li>
                            	<li><a class="dropdown-item" href="${ urlRoot }actualizacion_indicador/index">Actualización de Indicadores</a></li>
                            	<li><a class="dropdown-item" href="${ urlRoot }nuevo_indicador/index">Registrar Nuevo Indicador</a></li>
                            	<li><a class="dropdown-item" href="${ urlRoot }historial/index">Historial</a></li>
                            </ul>
						</li>	
						<li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Comunicación
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
								<li><a class="dropdown-item" href="${ urlRoot }mensajes/nuevo">Nuevo Mensaje</a></li>
								<li><a class="dropdown-item" href="${ urlRoot }mensajes/enviados">Mensajes Enviados</a></li>
								<li><a class="dropdown-item" href="${ urlRoot }mensajes/recibidos">Mensajes Recibidos</a></li>
							</ul>
						</li>
						<li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Herramientas
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            	<li><a class="dropdown-item" href="${ urlRoot }documentos/autorizar">Documentos por Autorizar</a></li>
                            </ul>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${ urlRoot }logout">Cerrar Sesión<span class="sr-only">(current)</span></a>
                        </li>
                    </ul>
                </div>
            </nav>
        </sec:authorize>
            
        <sec:authorize access="hasAnyAuthority('Usuario')">
            <!-- Barra de Navegación Usuario General -->
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <a class="navbar-brand" href="${ urlRoot }">
                	<img src="${ urlResources }/images/ISOTIPO_Una-tinta_FONDO-OSCURO.png" width="50" height="30" class="d-inline-block align-top" alt="Inicio">
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNavDropdown">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="${ urlRoot }perfil/index">Perfil <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Estatus del SGC
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                <li><a class="dropdown-item" href="${ urlRoot }programa_actividades/index">Programa de Actividades</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }reunion_directiva/index">Reunión Directiva</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }auditoria_interna/index">Resultados Auditoría Interna</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }auditoria_externa/index">Resultados Auditoría Externa</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }clima_laboral/index">Encuesta Clima Laboral</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }reunion_trabajo/index">Reuniones de Trabajo</a></li>
                            </ul>
                        </li>
						<li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Documentos
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                <li><a class="dropdown-item" href="${ urlRoot }politica/index">Política y Objetivos</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }manual/index">Manual de Gestión del SGC</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }gestion_conocimiento/index">Gestión del Conocimiento</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }procedimientos/index">Procedimientos</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }instructivos/index">Instructivos</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }formatos/index">Formatos</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }documentos_varios/index">Documentos Varios</a></li>
                            </ul>                               
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Acciones
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                <li><a class="dropdown-item" href="${ urlRoot }accion_correctiva/index">Acciones Correctivas</a></li>        
                                <li><a class="dropdown-item dropdown-toggle" href="#">Análisis de Entorno</a>
                                    <ul class="dropdown-menu">
                                        <li><a class="dropdown-item" href="${ urlRoot }analisis_riesgo/index">Análisis de Riesgo</a></li>
                                        <li><a class="dropdown-item" href="${ urlRoot }analisis_foda/index">Análisis FODA</a></li>
										<li><a class="dropdown-item" href="${ urlRoot }plan_accion/index">Plan de Acción</a></li>
									</ul>
								</li>
								
							</ul>
						</li>
						<li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Indicadores
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            	<li><a class="dropdown-item" href="${ urlRoot }tablero_resultados/index">Tablero de Resultados</a></li>
                            	<li><a class="dropdown-item" href="${ urlRoot }historial/index">Historial</a></li>
                            </ul>
						</li>	
						<li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Comunicación
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
								<li><a class="dropdown-item" href="${ urlRoot }mensajes/nuevo">Nuevo Mensaje</a></li>
								<li><a class="dropdown-item" href="${ urlRoot }mensajes/enviados">Mensajes Enviados</a></li>
								<li><a class="dropdown-item" href="${ urlRoot }mensajes/recibidos">Mensajes Recibidos</a></li>
							</ul>
						</li>
                        <li class="nav-item">
                            <a class="nav-link" href="${ urlRoot }logout">Cerrar Sesión<span class="sr-only">(current)</span></a>
                        </li>
                    </ul>
                </div>
            </nav>
        </sec:authorize>    
        
        <sec:authorize access="hasAnyAuthority('Interno')">    
            <!-- Barra de Navegación Auditor Interno -->
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <a class="navbar-brand" href="${ urlRoot }">
                	<img src="${ urlResources }/images/ISOTIPO_Una-tinta_FONDO-OSCURO.png" width="50" height="30" class="d-inline-block align-top" alt="Inicio">
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNavDropdown">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="${ urlRoot }perfil/index">Perfil <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Estatus del SGC
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                <li><a class="dropdown-item" href="${ urlRoot }programa_actividades/index">Programa de Actividades</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }reunion_directiva/index">Reunión Directiva</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }auditoria_interna/index">Resultados Auditoría Interna</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }auditoria_externa/index">Resultados Auditoría Externa</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }clima_laboral/index">Encuesta Clima Laboral</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }reunion_trabajo/index">Reuniones de Trabajo</a></li>
                            </ul>
                        </li>
						<li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Documentos
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                <li><a class="dropdown-item" href="${ urlRoot }politica/index">Política y Objetivos</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }manual/index">Manual de Gestión del SGC</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }gestion_conocimiento/index">Gestión del Conocimiento</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }procedimientos/index">Procedimientos</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }instructivos/index">Instructivos</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }formatos/index">Formatos</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }documentos_varios/index">Documentos Varios</a></li>
                            </ul>                               
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Acciones
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                <li><a class="dropdown-item" href="${ urlRoot }accion_correctiva/index">Acciones Correctivas</a></li>        
                                <li><a class="dropdown-item dropdown-toggle" href="#">Análisis de Entorno</a>
                                    <ul class="dropdown-menu">
                                        <li><a class="dropdown-item" href="${ urlRoot }analisis_riesgo/index">Análisis de Riesgo</a></li>
                                        <li><a class="dropdown-item" href="${ urlRoot }analisis_foda/index">Análisis FODA</a></li>
										<li><a class="dropdown-item" href="${ urlRoot }plan_accion/index">Plan de Acción</a></li>
									</ul>
								</li>
								<li><a class="dropdown-item" href="${ urlRoot }proyecto_mejora/index">Proyectos de Mejora</a></li>
							</ul>
						</li>
						<li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Indicadores
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            	<li><a class="dropdown-item" href="${ urlRoot }tablero_resultados/index">Tablero de Resultados</a></li>
                            	<li><a class="dropdown-item" href="${ urlRoot }historial/index">Historial</a></li>
                            </ul>
						</li>
                        <li class="nav-item">
                            <a class="nav-link" href="${ urlRoot }logout">Cerrar Sesión<span class="sr-only">(current)</span></a>
                        </li>
                    </ul>
                </div>
            </nav>
        </sec:authorize>    
        
        <sec:authorize access="hasAnyAuthority('Externo')">    
            <!-- Barra de Navegación Auditor Externo -->
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <a class="navbar-brand" href="${ urlRoot }">
                	<img src="${ urlResources }/images/ISOTIPO_Una-tinta_FONDO-OSCURO.png" width="50" height="30" class="d-inline-block align-top" alt="Inicio">
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNavDropdown">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="${ urlRoot }perfil/index">Perfil <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Estatus del SGC
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                <li><a class="dropdown-item" href="${ urlRoot }programa_actividades/index">Programa de Actividades</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }reunion_directiva/index">Reunión Directiva</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }auditoria_interna/index">Resultados Auditoría Interna</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }auditoria_externa/index">Resultados Auditoría Externa</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }clima_laboral/index">Encuesta Clima Laboral</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }reunion_trabajo/index">Reuniones de Trabajo</a></li>
                            </ul>
                        </li>
						<li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Documentos
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                <li><a class="dropdown-item" href="${ urlRoot }politica/index">Política y Objetivos</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }manual/index">Manual de Gestión del SGC</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }gestion_conocimiento/index">Gestión del Conocimiento</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }procedimientos/index">Procedimientos</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }instructivos/index">Instructivos</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }formatos/index">Formatos</a></li>
                                <li><a class="dropdown-item" href="${ urlRoot }documentos_varios/index">Documentos Varios</a></li>
                            </ul>                               
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Acciones
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                <li><a class="dropdown-item" href="${ urlRoot }accion_correctiva/index">Acciones Correctivas</a></li>        
                                <li><a class="dropdown-item dropdown-toggle" href="#">Análisis de Entorno</a>
                                    <ul class="dropdown-menu">
                                        <li><a class="dropdown-item" href="${ urlRoot }analisis_riesgo/index">Análisis de Riesgo</a></li>
                                        <li><a class="dropdown-item" href="${ urlRoot }analisis_foda/index">Análisis FODA</a></li>
										<li><a class="dropdown-item" href="${ urlRoot }plan_accion/index">Plan de Acción</a></li>
									</ul>
								</li>
								<li><a class="dropdown-item" href="${ urlRoot }proyecto_mejora/index">Proyectos de Mejora</a></li>
							</ul>
						</li>
						<li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Indicadores
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            	<li><a class="dropdown-item" href="${ urlRoot }tablero_resultados/index">Tablero de Resultados</a></li>
                            	<li><a class="dropdown-item" href="${ urlRoot }historial/index">Historial</a></li>
                            </ul>
						</li>	
                        <li class="nav-item">
                            <a class="nav-link" href="${ urlRoot }logout">Cerrar Sesión<span class="sr-only">(current)</span></a>
                        </li>
                    </ul>
                </div>
            </nav>
        </sec:authorize>    

</div>

<div class="container">
<nav class="navbar navbar-light">
  <a class="navbar-brand"></a>
  <form class="form-inline">
    <input class="form-control mr-sm-2" type="search" placeholder="Buscar" aria-label="Search">
    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Buscar</button>
  </form>
</nav>
</div>