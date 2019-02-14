package edu.uabc.app.util;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import edu.uabc.app.model.Contador;
import edu.uabc.app.model.Departamento;
import edu.uabc.app.model.DocumentoActualizar;
import edu.uabc.app.model.DocumentoConsulta;
import edu.uabc.app.model.TipoArchivo;
import edu.uabc.app.model.TipoDocumento;

public class Utileria {
	
	public static String guardarDocumento(MultipartFile multiPart, HttpServletRequest request, String tipoDocumento, String departamento, String extension) {
		
		//Se obtiene el nombre del archivo
		String nombreArchivo = multiPart.getOriginalFilename();
		nombreArchivo = nombreArchivo.replaceAll(" ", "-");
		
		//nombreFinal = nombreFinal.concat(extension);
		
		// Obtenemos la ruta ABSOLUTA del directorio images
		// Apache-tomcat/webapps/sgc/resources/docs/
		String rutaFinal = request.getServletContext().getRealPath("/resources/docs/"+tipoDocumento+"/"+departamento+"/");
		//String rutaFinal = "C:/spring-workspace/sgc/webapp/resources/docs/"+tipoDocumento+"/"+departamento+"/";
		System.out.println("Ruta final: " + rutaFinal);
		System.out.println("Nombre final: " + nombreArchivo);
		//rutaFinal = rutaFinal.concat(nombreFinal);
		try {
			// Formamos el nombre del archivo para guardarlo en el disco duro
			File imageFile = new File(rutaFinal + nombreArchivo);
			System.out.println(imageFile.getAbsolutePath());
			// Aquì se guarda fìsicamente el archivo en el disco duro
			multiPart.transferTo(imageFile);
			return nombreArchivo;
		} catch (IOException e) {
			System.out.println("Error " + e.getMessage());
			return null;
		}
	}
	
	public static String guardarDocumentoSinClave(MultipartFile multiPart, HttpServletRequest request, String ruta) {
		
		//Se obtiene el nombre del archivo
		String nombreArchivo = multiPart.getOriginalFilename();
		nombreArchivo = nombreArchivo.replaceAll(" ", "-");
		
		// Obtenemos la ruta ABSOLUTA del directorio images
		// Apache-tomcat/webapps/sgc/resources/docs/
		//String rutaFinal = request.getServletContext().getRealPath("/resources/docs/"+tipoDocumento+"/"+departamento+"/");
		String rutaFinal = "C:/spring-workspace/sgc/webapp/resources/docs/"+ruta+"/";
		System.out.println("Ruta final: " + rutaFinal);
		//rutaFinal = rutaFinal.concat(nombreFinal);
		try {
			// Formamos el nombre del archivo para guardarlo en el disco duro
			File imageFile = new File(rutaFinal + nombreArchivo);
			System.out.println(imageFile.getAbsolutePath());
			// Aquì se guarda fìsicamente el archivo en el disco duro
			multiPart.transferTo(imageFile);
			return nombreArchivo;
		} catch (IOException e) {
			System.out.println("Error " + e.getMessage());
			return null;
		}
	}


	//Método para generar una cadena de longitud N de caracteres aleatorios
	public static String randomAlphaNumeric(int count) {
		String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * CARACTERES.length());
			builder.append(CARACTERES.charAt(character));
		}
		return builder.toString();
	}
	
	//Método para generar la fecha actual
	public static Date generarFecha() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		Calendar calendar = Calendar.getInstance();
		Date current = Calendar.getInstance().getTime();
		//System.out.println("Prueba uno: " + dateFormat.format(calendar.getTime()));
		//System.out.println("Prueba dos: " + current);
		return current;
	}
	
	//Método para generar la fecha actual
		public static Date devolverFecha(Date date) {
			DateFormat dateFormat = new SimpleDateFormat("E MM dd hh:mm:ss z YYYY"); 
			//Calendar calendar = Calendar.getInstance();
		//	Date current = 
			//System.out.println("Prueba uno: " + dateFormat.format(calendar.getTime()));
			//System.out.println("Prueba dos: " + current);
			return null;
		}
	
	//Método para obtener la nomenclatura del departamento
	public static String nomenclaturaDepartamento(DocumentoActualizar documento, List<Departamento> listaDepartamento) {
		int id_departamento = documento.getIdDepartamento();
		
		int cont = 0;
		String nomenclaturaDepartmento = "";
		while(cont<listaDepartamento.size()) {
			Departamento depa = listaDepartamento.get(cont);
			int id = depa.getId_departamento();
			if(id_departamento == id) {
				nomenclaturaDepartmento = depa.getNomenclatura();
			}
			cont++;
		}
		
		System.out.println("   ");
		System.out.println("Nomenclatura: " + nomenclaturaDepartmento);
		System.out.println("   ");
		
		return nomenclaturaDepartmento;
	}
	
	//Método para obtener el departamento
		public static String obtenerDepartamento(DocumentoActualizar documento, List<Departamento> listaDepartamento) {
			int id_departamento = documento.getIdDepartamento();
			
			int cont = 0;
			String departmento = "";
			while(cont<listaDepartamento.size()) {
				Departamento depa = listaDepartamento.get(cont);
				int id = depa.getId_departamento();
				if(id_departamento == id) {
					departmento = depa.getRuta();
				}
				cont++;
			}
			
			System.out.println("   ");
			System.out.println("Nombre del Departamento: " + departmento);
			System.out.println("   ");
			
			return departmento;
	}
		
	//Método para obtener la nomenclatura del tipo de documento
	public static String nomenclaturaTipoDocumento(DocumentoActualizar documento, List<TipoDocumento> listaTipoDocumento) {
		int id_t_documento = documento.getIdTipoDocumento();
		
		int cont = 0;
		String nomenclaturaTipoDocumento = "";
		while(cont<listaTipoDocumento.size()) {
			TipoDocumento doc = listaTipoDocumento.get(cont);
			int id = doc.getIdTipoDocumento();
			if(id_t_documento == id) {
				nomenclaturaTipoDocumento = doc.getNomenclatura();
			}
			cont++;
		}
		
		System.out.println("   ");
		System.out.println("Nomenclatura: " + nomenclaturaTipoDocumento);
		System.out.println("   ");
		
		return nomenclaturaTipoDocumento;
	}
	
	//Método para obtener la nomenclatura del tipo de documento
	public static String obtenerTipoDocumento(DocumentoActualizar documento, List<TipoDocumento> listaTipoDocumento) {
		int id_t_documento = documento.getIdTipoDocumento();
		
		int cont = 0;
		String tipoDocumento = "";
		while(cont<listaTipoDocumento.size()) {
			TipoDocumento doc = listaTipoDocumento.get(cont);
			int id = doc.getIdTipoDocumento();
			if(id_t_documento == id) {
				tipoDocumento = doc.getRuta();
			}
			cont++;
		}
		
		System.out.println("   ");
		System.out.println("Nombre del tipo de documento: " + tipoDocumento);
		System.out.println("   ");
		
		return tipoDocumento;
	}
	
	//Método para obtener el consecutivo del documento
	public static String obtenerConsecutivo(List<Contador> contador) {
		System.out.println("Listado del contador:" + contador);
		Contador cont = contador.get(0);
		int conta = cont.getContador();
		
		String consecutivo = Integer.toString(conta);
		if(consecutivo.length()==1) {
			consecutivo = "00" + consecutivo;
		}
		else if (consecutivo.length()==2) {
			consecutivo = "0" + consecutivo;
		}
		return consecutivo;
	}
	
	//Método para aumentar el consecutivo del documento
	public static Contador aumentarConsecutivo(List<Contador> contador) {
		System.out.println("Listado del contador:" + contador);
		Contador cont = contador.get(0);
		int conta = cont.getContador();
		conta++;
		System.out.println("Contador aumentado: " + conta);
		cont.setContador(conta);
		return cont;
	}
	
	//Método para identificar y agregar las extensiones al nombre de los archivos
	public static String agregarExtensionArchivos(MultipartFile multiPart) {
		
		// Se obtiene el nombre original del archivo
		String nombreOriginal = multiPart.getOriginalFilename();		
		nombreOriginal = nombreOriginal.replace(" ", "-");
		System.out.println(nombreOriginal);
		String extension = "";
		
		int contador = nombreOriginal.lastIndexOf(".");
		if(contador > 0) {
			int largoExtension = nombreOriginal.length();
			extension = nombreOriginal.substring(contador, largoExtension);
			extension.toLowerCase();
			System.out.println("Extension: " + extension);
		}
		return extension;
	}
	
	//Método para identificar y eliminar las extensiones al nombre de los archivos
	public static String eliminarExtensionArchivos(MultipartFile multiPart) {
		// Se obtiene el nombre del archivo que aparece en ruta
		String ruta = "";
		return ruta;
	}
	
	//Método para identificar el tipo de archivo que se guardará en la base de datos
	public static int identificarExtensionArchivos(String extension, List<TipoArchivo> listaTipoArchivo) {
		int id_tipo_archivo = 0;
		int contador = 0;
		extension = extension.toLowerCase();
		
		while(contador<5) {
			TipoArchivo tipoArchivo = listaTipoArchivo.get(contador);
			String ruta = tipoArchivo.getRuta();
			System.out.println("Ruta: " + ruta);
			System.out.println("Extension: " + extension);
			if(ruta.equals(extension)) {
				id_tipo_archivo = tipoArchivo.getId_tipo_archivo();
				System.out.println("id_tipo_archivo: " + id_tipo_archivo);
			}
			contador++;
		}
		System.out.println("id_tipo_archivo: " + id_tipo_archivo);
		return id_tipo_archivo;
	}
	
	//Método para identificar la clasificación de los documentos
	public static List<DocumentoActualizar> identificarDocumento(List<DocumentoActualizar> lista) {
		
		return lista;
	}
	
	//Método para identificar los documentos que corresponden a un departamento
	public static List<DocumentoActualizar> identificarDocumentosPorDepartamento(List<DocumentoActualizar> listaDocumento, int id_departamento, int id_tipo_documento, int lineaAutorizacion) {
		List<DocumentoActualizar> documento = new ArrayList<DocumentoActualizar>();
		for (int index=0; index < listaDocumento.size(); index++) {
			DocumentoActualizar doc = listaDocumento.get(index);
			if((doc.getIdDepartamento() == id_departamento) && (doc.getIdTipoDocumento() == id_tipo_documento) && (doc.getEstatus() == lineaAutorizacion)){
				documento.add(doc);
			}
		}
		return documento;
	}
	
	//Método para separar el nombre de la clave
	public static List<DocumentoActualizar> separarNombreClave(List<DocumentoActualizar> documento) {
		for (int index=0; index < documento.size(); index++) {
			DocumentoActualizar doc = documento.get(index);
			String clave = doc.getRuta();
			System.out.println("Clave: " + clave);
			clave = clave.substring(0, 9);
			doc.setEstatus(1);
			documento.set(index, doc);
		}
		return documento;
	}
	
	//Método para separar el nombre de la clave
	public static List<DocumentoActualizar> separarNombreClaveMGC(List<DocumentoActualizar> documento) {
		for (int index=0; index < documento.size(); index++) {
			DocumentoActualizar doc = documento.get(index);
			String clave = doc.getRuta();
			if(index==9) {
				clave = clave.substring(0, 17);
				System.out.println("Ultima Clave: " + clave);
				doc.setEstatus(1);
			}
			else {
				clave = clave.substring(0, 16);
				doc.setEstatus(1);
				System.out.println("Clave: " + index + clave);
			}
			documento.set(index, doc);
		}
		return documento;
	}
	
	//Método para identificar los documentos pendientes de autorizar
	public static List<DocumentoConsulta> identificarDocumentosPorAutorizar(List<DocumentoConsulta> listaDocumento) {
		List<DocumentoConsulta> documento = new ArrayList<DocumentoConsulta>();
		for (int index=0; index < listaDocumento.size(); index++) {
			DocumentoConsulta doc = listaDocumento.get(index);
			if(doc.getEstatus() != 4){
				documento.add(doc);
			}
		}
		return documento;
	}
	
	//Método para identificar el tipo de documento correspondiente a Manual de Gestión de Calidad
	public static List<DocumentoActualizar> identificarMGC(List<DocumentoActualizar> listaDocumento, int lineaAutorizacion) {
		List<DocumentoActualizar> documento = new ArrayList<DocumentoActualizar>();
		for (int index=0; index < listaDocumento.size(); index++) {
			DocumentoActualizar doc = listaDocumento.get(index);
				if((doc.getIdTipoDocumento() == 1) &&	(doc.getEstatus() == lineaAutorizacion)){
					documento.add(doc);
				}
		}
		return documento;
	}
	
	//Método para identificar el tipo de documento correspondiente a Reunión Directiva
	public static List<DocumentoActualizar> identificarReunionDirectiva(List<DocumentoActualizar> listaDocumento, int lineaAutorizacion) {
		List<DocumentoActualizar> documento = new ArrayList<DocumentoActualizar>();
		for (int index=0; index < listaDocumento.size(); index++) {
			DocumentoActualizar doc = listaDocumento.get(index);

			if((doc.getIdTipoDocumento() == 6) &&	(doc.getEstatus() == lineaAutorizacion)){
				documento.add(doc);
			}
		}
		return documento;
	}
	
	//Método para identificar el tipo de documento correspondiente a Auditoría Interna
	public static List<DocumentoActualizar> identificarAuditoriaInterna(List<DocumentoActualizar> listaDocumento, int lineaAutorizacion) {
		List<DocumentoActualizar> documento = new ArrayList<DocumentoActualizar>();
		for (int index=0; index < listaDocumento.size(); index++) {
			DocumentoActualizar doc = listaDocumento.get(index);

			if((doc.getIdTipoDocumento() == 7) &&	(doc.getEstatus() == lineaAutorizacion)){
				documento.add(doc);
			}
		}
		return documento;
	}
	
	//Método para identificar el tipo de documento correspondiente a Auditoría Externa
	public static List<DocumentoActualizar> identificarAuditoriaExterna(List<DocumentoActualizar> listaDocumento, int lineaAutorizacion) {
		List<DocumentoActualizar> documento = new ArrayList<DocumentoActualizar>();
		for (int index=0; index < listaDocumento.size(); index++) {
			DocumentoActualizar doc = listaDocumento.get(index);

			if((doc.getIdTipoDocumento() == 8) &&	(doc.getEstatus() == lineaAutorizacion)){
				documento.add(doc);
			}
		}
		return documento;
	}
	
	//Método para identificar el tipo de documento correspondiente a Encuesta Clima Laboral
	public static List<DocumentoActualizar> identificarEncuestaClimaLaboral(List<DocumentoActualizar> listaDocumento, int lineaAutorizacion) {
		List<DocumentoActualizar> documento = new ArrayList<DocumentoActualizar>();
		for (int index=0; index < listaDocumento.size(); index++) {
			DocumentoActualizar doc = listaDocumento.get(index);

			if((doc.getIdTipoDocumento() == 9) &&	(doc.getEstatus() == lineaAutorizacion)){
				documento.add(doc);
			}
		}
		return documento;
	}
	
	//Método para identificar el tipo de documento correspondiente a Reunión Trabajo
	public static List<DocumentoActualizar> identificarReunionTrabajo(List<DocumentoActualizar> listaDocumento, int lineaAutorizacion) {
		List<DocumentoActualizar> documento = new ArrayList<DocumentoActualizar>();
		for (int index=0; index < listaDocumento.size(); index++) {
			DocumentoActualizar doc = listaDocumento.get(index);

			if((doc.getIdTipoDocumento() == 10) &&	(doc.getEstatus() == lineaAutorizacion)){
				documento.add(doc);
			}
		}
		return documento;
	}
}
