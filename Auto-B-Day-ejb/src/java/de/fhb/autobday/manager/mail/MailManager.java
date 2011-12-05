package de.fhb.autobday.manager.mail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import de.fhb.autobday.commons.PasswortGenerator;
import de.fhb.autobday.dao.AbdUserFacade;
import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.data.AbdUser;
import de.fhb.autobday.exception.mail.MailException;

/**
 * TODO This will may change position to ABDManager!!!
 *
 * @author Michael Koppen
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
	public void sendBdayMail(/*String subject, String message, String recipient||Abduser recipient, String from||Abduser from*/) {
		InternetAddress from = new InternetAddress();
		from.setAddress("ABSENDER@from.com"/*from*/);
		
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
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", "mymail.server.org");
		props.setProperty("mail.user", "emailuser");
		props.setProperty("mail.password", "");

		mailSession = Session.getDefaultInstance(props, null);

	}
	
	private void connectToOwnMailServer() {
		try {
			InitialContext ic = new InitialContext();
			String snName = "java:comp/env/mail/MyMailSession";
			mailSession = (Session) ic.lookup(snName);
		} catch (NamingException ex) {
			Logger.getLogger(MailManager.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
}
