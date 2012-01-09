package de.fhb.autobday.manager.mail;

import de.fhb.autobday.commons.PropertyLoader;
import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.manager.LoggerInterceptor;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.interceptor.Interceptors;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 * 
 */

@Singleton
@Startup
@LocalBean
@Interceptors(LoggerInterceptor.class)
public class GoogleMailManager {
	private final static Logger LOGGER = Logger.getLogger(GoogleMailManager.class.getName());

	public GoogleMailManager() {
	}

	
	public synchronized void sendSystemMail(String subject, String message, String to) throws Exception{
		Properties systemProps = null;
		Properties accountProps = null;
		try {
			systemProps = PropertyLoader.loadSystemMailProperty();
			accountProps = PropertyLoader.loadSystemMailAccountProperty();
		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
			throw new Exception(ex);
		}
		
		try {
            //Obtain the default mail session
            Session session = Session.getDefaultInstance(systemProps, null);
            session.setDebug(true);
			
            //Construct the mail message
            MimeMessage mail = new MimeMessage(session);
			
            mail.setText(message);
            mail.setSubject(subject);
            mail.setFrom(new InternetAddress(accountProps.getProperty("mail.smtp.user")));
            mail.addRecipient(RecipientType.TO, new InternetAddress(to));
            mail.saveChanges();
			
            //Use Transport to deliver the message
            Transport transport = session.getTransport("smtp");
            transport.connect(systemProps.getProperty("mail.smtp.host"), accountProps.getProperty("mail.smtp.user"), accountProps.getProperty("mail.smtp.password"));
            transport.sendMessage(mail, mail.getAllRecipients());
            transport.close();
			
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
			throw new Exception(ex);
        }

	}
	public synchronized void sendUserMail(AbdAccount account, String subject, String message, String to) throws Exception{
		Properties systemProps = null;
		try {
			systemProps = PropertyLoader.loadSystemMailProperty();
		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
			throw new Exception(ex);
		}
		
		try {
            //Obtain the default mail session
            Session session = Session.getDefaultInstance(systemProps, null);
            session.setDebug(true);
			
            //Construct the mail message
            MimeMessage mail = new MimeMessage(session);
			
            mail.setText(message);
            mail.setSubject(subject);
            mail.setFrom(new InternetAddress(account.getUsername()));
            mail.addRecipient(RecipientType.TO, new InternetAddress(to));
            mail.saveChanges();
			
            //Use Transport to deliver the message
            Transport transport = session.getTransport("smtp");
            transport.connect(systemProps.getProperty("mail.smtp.host"), account.getUsername(), account.getPasswort());
            transport.sendMessage(mail, mail.getAllRecipients());
            transport.close();
			
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
			throw new Exception(ex);
        }

	}
}

