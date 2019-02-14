/**
 * Prueba de ajax
 */

// JavaScript Document

var btnCargar = document.getElementById('cargar');

function cargarContenidoAjax() {
    //alert("Funciona!");

    // crear XmlHttpRequest
    var xhr = new XMLHttpRequest();

    xhr.open("GET", "texto_prueba.txt", true);
    xhr.onreadystatechange = function() {
        console.log(xhr.readyState);
        if(xhr.readyState == 4 && xhr.status == 200) {
            var contenido = document.getElementById('contenido');
            contenido.innerHTML = xhr.responseText;
        }
    }

    xhr.send();
}

btnCargar.addEventListener('click', cargarContenidoAjax);