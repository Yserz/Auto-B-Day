package de.fhb.autobday.manager.mail;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import de.fhb.autobday.commons.AccountPropertiesFile;
import de.fhb.autobday.commons.PasswortGenerator;
import de.fhb.autobday.dao.AbdUserFacade;
import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.data.AbdUser;
import de.fhb.autobday.exception.mail.MailException;

/**
 * TODO This will may change position to ABDManager!!!
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@Singleton
@Startup
public class MailManager implements MailManagerLocal {

	private final static Logger LOGGER = Logger.getLogger(MailManager.class.getName());
	private Session mailSession;
	
	@EJB
	private AbdUserFacade userDAO;

	public MailManager() {
		connectToMailServer();
	}

	@Override
	public void sendBdayMail(String from, String to, String subject, String body) {
		/*
		InternetAddress from = new InternetAddress();
		from.setAddress("ABSENDER@from.com"from);
		
		//Override the JavaMail session properties if necessary.
		Properties props = mailSession.getProperties();
		props.put("mail.from", "user2@mailserver.com");
		
		
		try {
			MimeMessage message = new MimeMessage(mailSession);

			//adding META-Data
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("elvis@presley.org"));
			
			//Override the JavaMail session properties if necessary.
			//TODO choose setSender||setFrom||properties (whats the best solution?)
			message.setSender(from);
			message.setFrom(from);
			
			message.setSentDate(new Date());
			
			message.setSubject("Testing bday javamail html");

			message.setContent("This is a bday test <b>HOWTO<b>",
							   "text/html; charset=ISO-8859-1");

			
			sendMail(message);
			

		} catch (MessagingException ex) {
			Logger.getLogger(MailManager.class.getName()).log(Level.SEVERE, null, ex);
		}
		*/
		try {
			 
			Message message = new MimeMessage(mailSession);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(body);
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void sendNotificationMail() {
	}

	@Override
	public void sendForgotPasswordMail(int userId) throws MailException {
		// enge zusammenarbeit mit usermanager
		
		//getUser
		AbdUser user = null;
		List<AbdAccount> accounts;
		
		user=userDAO.find(userId);
		
		if(user==null){
			LOGGER.log(Level.SEVERE, "User {0}not found!", userId);
			//TODO Spezifische Exception!!
			throw new MailException("User " + userId + "not found!");
		}
		
		//TODO getUsersmail
		accounts = new ArrayList<AbdAccount>(user.getAbdAccountCollection());
		String userMailAdress=accounts.get(0).getUsername();
		
		//generate new Password
		String newPassword=PasswortGenerator.generatePassword();
		
		// save new password into database
		user.setPasswort(newPassword);
		userDAO.edit(user);
		
		//TODO Send mail with new Password
		
	}

	private void sendMail(MimeMessage message) {
		Transport transport;
		try {

			transport = mailSession.getTransport();


			transport.connect();
			transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			transport.close();

		} catch (MessagingException ex) {
			Logger.getLogger(MailManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void connectToMailServer() {
		/*
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", "mymail.server.org");
		props.setProperty("mail.user", "fhbtestacc@googlemail.com");
		props.setProperty("mail.password", "TestGoogle123");

		mailSession = Session.getDefaultInstance(props, null);
		*/
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
 
		mailSession = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					HashMap<String,String> properties = AccountPropertiesFile.getProperties("mailaccount.properties");
					return new PasswordAuthentication(properties.get("loginname"),properties.get("password"));
				}
			});

	}
	
}
