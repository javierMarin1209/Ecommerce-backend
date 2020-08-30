package com.ias.Ecommerce.models.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Deliverymessage {
	
	private Properties proprerties;
	
	public Deliverymessage() {
		try (InputStream input = new FileInputStream("tmp/gmail.properties")) {
	        proprerties = new Properties();
	        proprerties.load(input);
	    } catch (IOException ex) {
	    }
	}
	public Properties getProp() {
		return proprerties;
	}
	public void setProp(Properties prop) {
		this.proprerties = prop;
	}
	
	public String enviarConGMail(String destinatario, String subject, String body) {
		
		    Session session = Session.getDefaultInstance(proprerties);
		    MimeMessage message = new MimeMessage(session);
		    try {
		        message.setFrom(new InternetAddress(proprerties.getProperty("mail.smtp.user")));
		        message.addRecipients(Message.RecipientType.TO, destinatario);   //Se podrían añadir varios de la misma manera
		        message.setSubject(subject);
		        message.setText(body+proprerties.getProperty("body.footer"));
		        Transport transport = session.getTransport("smtp");
		        transport.connect("smtp.gmail.com", proprerties.getProperty("mail.smtp.user"), proprerties.getProperty("mail.smtp.clave"));
		        transport.sendMessage(message, message.getAllRecipients());
		        transport.close();
		        return null;
		    }
		    catch (MessagingException me) {
		        me.printStackTrace();
		        return me.getMessage();
		    }
		}

}
