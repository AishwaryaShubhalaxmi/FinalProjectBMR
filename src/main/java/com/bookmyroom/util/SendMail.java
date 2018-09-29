package com.bookmyroom.util;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {
	public static void sendMail(String location){
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", "587"); 
		Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("frameworkdemoemail@gmail.com", "Test@123");
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("frameworkdemoemail@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("Aishwarya.Shubhalaxmi2@mindtree.com"));
			message.setSubject("BookMyRoom: Test Report");
			BodyPart messageBodyPart1 = new MimeBodyPart();
			messageBodyPart1.setText("PFA the testing report of BookMyRoom (This is an auto generated mail)");
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();
			System.out.println("--------- 1 ----------");
			String filename = location;
			DataSource source = new FileDataSource(filename);
			System.out.println("--------- 2 ----------");
			messageBodyPart2.setDataHandler(new DataHandler(source));
			messageBodyPart2.setFileName(filename);
			System.out.println("--------- 3 ----------");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart2);
			multipart.addBodyPart(messageBodyPart1);
			System.out.println("--------- 4 ----------");
			message.setContent(multipart);
			System.out.println("--------- Message: "+message+" ----------");
			Transport.send(message);
			System.out.println("=====Email Sent=====");
		} catch (MessagingException e) {
			throw new RuntimeException(e);

		}

	}

}

