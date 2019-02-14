package edu.uabc.app.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnviarCorreo {
	
	public static void EnviarNotificacion() throws Exception {
		String text = "Tiene un nuevo mensaje dentro del Sistema de Gesti�n de Calidad del Patronato Universitario. \n \nPara ver el mensaje, ingrese a http://localhost:8080/sgc/login \n \nSaludos \n \nAdministrador del Sistema de Gesti�n de Calidad";
		String text1 = "Se ha registrado un nuevo documento en el Sistema de Gesti�n de Calidad del Patronato Universitario que requiere su autorizaci�n. \n \nPara autorizar el documento, ingrese a http://localhost:8080/sgc/login secci�n Herramientas, opci�n Documentos por Autorizar \n \nSaludos \n \nAdministrador del Sistema de Gesti�n de Calidad";
		String host = "smtp.gmail.com";
		String from = "luis.eduardo.chavez.escudero@uabc.edu.mx";
		String password = "Vfdxfw029154";
		String to = "luis.eduardo.chavez.escudero@uabc.edu.mx";
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		
		// Create Authenticator object to pass in Session.getInstance argument
		Authenticator authenticator = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
				
			}
		};
		
		
		Session session =  Session.getDefaultInstance(properties, authenticator);
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setSubject("Notificaci�n del Sistema de Gesti�n de Calidad");
		message.setText("Tiene un nuevo mensaje dentro del Sistema de Gesti�n de Calidad del Patronato Universitario. \n \nPara ver el mensaje, ingrese a http://localhost:8080/sgc/login \n \nSaludos \n \nAdministrador del Sistema de Gesti�n de Calidad");
		Transport.send(message);
	}
	
}
