package edu.uabc.app.util;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Log {

	protected static String defaultLogFile = "/var/www/html/contaduria.uabc.mx/sgc.log";
	
	public Log() {
		
	}
	
	public static void write(String s) throws IOException {
		write(defaultLogFile, s);
	}
	
	public static void write(String f, String s) throws IOException {
		TimeZone tz = TimeZone.getTimeZone("PST");
		Date now = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		dateFormat.setTimeZone(tz);
		String currentTime = dateFormat.format(now);
		
		FileWriter aWriter = new FileWriter(f, true);
		aWriter.write(currentTime + " " + s + "\n");
		aWriter.flush();
		aWriter.close();
	}
}
