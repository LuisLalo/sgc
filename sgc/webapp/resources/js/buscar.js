/**
 * 
 */
 /*
function buscar() {
	var search = $("input#search").val();
	
	if(search != "") {
		$.post("formLineaAutorizacion.jsp",{valorBusqueda: search}, function(mensaje) {
			$("#resultadoBusqueda").html(mensaje);
		});
	} else {
		("#resultadoBusqueda").html('');
	};
};*/

/*
function obtener_registros(usuarios) {
	$.ajax({
		url: 'apoyo_informatico/2',
		type: 'GET',
		dataType: 'jsp',
		data: { usuarios: usuarios },
	})
	
	.done(function(resultado){
		$("#tabla_resultado").html(resultado);
	})
}

$(document).on('keyup','search',function(){
	var valorBusqueda=$(this).val();
	if(valorBusqueda!="") {
		obtener_registros(valorBusqueda)
	}
	else {
		obtener_registros();
	}
});*/
/*
$(document).ready(function(){
	alert('JQuery is ready and integrated');
});*/

$(document).ready(function(){
	$('#buscar').click(function(){
		$.ajax({
			type: 'GET',
			url: 'http://localhost:8080/sgc/linea-autorizacion/apoyo_informatico/2',
			success: function(result){
				$('#result').html(result);
			}
		});
	});
});