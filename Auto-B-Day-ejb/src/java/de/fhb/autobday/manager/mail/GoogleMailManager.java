package de.fhb.autobday.manager.mail;

import de.fhb.autobday.commons.PropertyLoader;
import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.exception.mail.FailedToLoadPropertiesException;
import de.fhb.autobday.exception.mail.FailedToSendMailException;
import de.fhb.autobday.manager.LoggerInterceptor;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.interceptor.Interceptors;
import javax.mail.AuthenticationFailedException;
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

	
	public synchronized void sendSystemMail(String subject, String message, String to) throws Exception {

		Properties accountProps = null;
		AbdAccount systemAccount = null;
		try {
			accountProps = new PropertyLoader().loadSystemMailAccountProperty("SystemMailAccount.properties");
			

			String user = accountProps.getProperty("mail.smtp.user");
			String password = accountProps.getProperty("mail.smtp.password");
			systemAccount = new AbdAccount(1, user, password, "");
			
        } catch (IOException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
			throw new FailedToLoadPropertiesException("Failed to load mail properties.");
		} catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
			throw new FailedToSendMailException("Failed to send mail.");
        }
		
		this.sendUserMail(systemAccount, subject, message, to);

	}
	
	public synchronized void sendUserMail(AbdAccount account, String subject, String message, String to) throws Exception{
		Properties systemProps = null;
		try {
			systemProps = new PropertyLoader().loadSystemMailProperty("SystemMail.properties");
		
			//systemProps
			String host = systemProps.getProperty("mail.smtp.host");
			
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
            transport.connect(host, account.getUsername(), account.getPasswort());
            transport.sendMessage(mail, mail.getAllRecipients());
            transport.close();
			
        } catch (AuthenticationFailedException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
			throw new FailedToSendMailException("Authentication-Error. Please check your credentials.");
		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
			throw new FailedToLoadPropertiesException("Failed to load mail properties.");
		} catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
			throw new FailedToSendMailException("Failed to send mail.");
        }

	}
}

