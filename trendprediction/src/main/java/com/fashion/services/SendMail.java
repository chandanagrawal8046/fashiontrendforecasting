package com.fashion.services;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail 
{
	private String username;
	private String password;
	
	public SendMail(String username,String password)
	{
		this.username=username;
		this.password=password;
	}
	
	
     public void sendMail(String subject,String msg,String emailid)
    {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.live.com");
		props.put("mail.smtp.port", "25");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try 
                {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(emailid));
			message.setSubject(subject);
			message.setText(msg);
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

    }
}