package de.fhb.autobday.manager.mail;

import de.fhb.autobday.commons.PasswordGenerator;
import de.fhb.autobday.dao.AbdUserFacade;
import de.fhb.autobday.data.AbdUser;
import de.fhb.autobday.exception.mail.MailException;
import de.fhb.autobday.exception.user.UserNotFoundException;
import de.fhb.autobday.manager.LoggerInterceptor;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.interceptor.Interceptors;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * This is the mailmanager, which is responsible for sending mails
 *
 * @author
 * Andy Klay <klay@fh-brandenburg.de>
 * Michael Koppen <koppen@fh-brandenburg.de>
 */
@Singleton
@Startup
@Interceptors(LoggerInterceptor.class)
public class MailManager implements MailManagerLocal {

	private final static Logger LOGGER = Logger.getLogger(MailManager.class.getName());
	private Session mailSession;
	
	@EJB
	private AbdUserFacade userDAO;

	public MailManager() {

	}

	/**
	 * (non-Javadoc)
	 * @see de.fhb.autobday.manager.mail.MailManagerLocal#sendBdayMail(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void sendBdayMail(String from, String to, String subject, String body) {
		
		//connect
		connectToMailServer();
		
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

	/**
	 * @return the userDAO
	 */
	public AbdUserFacade getUserDAO() {
		return userDAO;
	}

	/**
	 * @param userDAO the userDAO to set
	 */
	public void setUserDAO(AbdUserFacade userDAO) {
		this.userDAO = userDAO;
	}

	/**
	 * @return the mailSession
	 */
	public Session getMailSession() {
		return mailSession;
	}

	/**
	 * (non-Javadoc)
	 * @see de.fhb.autobday.manager.mail.MailManagerLocal#sendNotificationMail()
	 */
	@Override
	public void sendNotificationMail() {
	}

	/**
	 * (non-Javadoc)
	 * @see de.fhb.autobday.manager.mail.MailManagerLocal#sendForgotPasswordMail(int)
	 */
	@Override
	public void sendForgotPasswordMail(int userId) throws MailException, UserNotFoundException {
		// enge zusammenarbeit mit usermanager
		
		//getUser
		AbdUser user = null;
		String userMailAdress;
		String newPassword;
		String mailBody;
		
		user=userDAO.find(userId);
		
		if(user==null){
			LOGGER.log(Level.SEVERE, "User {0}not found!", userId);
			throw new UserNotFoundException("User " + userId + "not found!");
		}
		
		userMailAdress=user.getUsername();
		
		//connect
		connectToMailServer();
		
		//generate new Password
		newPassword=PasswordGenerator.generatePassword();
		
		// save new password into database
		user.setPasswort(newPassword);
		userDAO.edit(user);
		
		mailBody =  "You recieved a new password for your autobdayaccount: " + newPassword + "\n\n" + "greetz your Autobdayteam";
		
		// Send mail with new Password
		this.sendBdayMail("", userMailAdress, "Autobday Notification", mailBody);
	}

	
	private void connectToMailServer() {
		
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", "mymail.server.org");
		props.setProperty("mail.user", "fhbtestacc@googlemail.com");
		props.setProperty("mail.password", "TestGoogle123");

		mailSession = Session.getDefaultInstance(props, null);
		/*
		
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
		*/
	}

	public void setMailSession(Session mailSession) {
		this.mailSession = mailSession;
	}
	
}
