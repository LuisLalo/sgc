package edu.uabc.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/buscar")
public class BusquedaController {

	//@Autowired
	
	@GetMapping("/buscarDocumetos")
	public String mostrarBusqueda(@RequestParam("nombre") String nombre, Model model) {
		System.out.println("nombre" + nombre);
		String texto = "%" + nombre + "%";
		
		//List<DocumentoConsulta> listaDocumentoConsulta = 
		return "";
	}
}
