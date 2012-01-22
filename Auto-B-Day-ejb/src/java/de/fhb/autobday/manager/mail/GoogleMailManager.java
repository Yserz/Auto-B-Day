package de.fhb.autobday.manager.mail;

import de.fhb.autobday.commons.CipherHelper;
import de.fhb.autobday.commons.PropertyLoader;
import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.exception.mail.FailedToLoadPropertiesException;
import de.fhb.autobday.exception.mail.FailedToSendMailException;
import de.fhb.autobday.manager.LoggerInterceptor;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.mail.AuthenticationFailedException;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Implementation of GoogleMailManager.
 *
 * @author Andy Klay mail: klay@fh-brandenburg.de
 * @author Michael Koppen mail: koppen@fh-brandenburg.de
 */
@Stateless
@Local
@Interceptors(LoggerInterceptor.class)
public class GoogleMailManager implements GoogleMailManagerLocal {

	private final static Logger LOGGER = Logger.getLogger(GoogleMailManager.class.getName());
	private PropertyLoader propLoader;

	public GoogleMailManager() {
		propLoader = new PropertyLoader();
	}

	/**
	 * send a mail with the systemaccount (see property-file).
	 *
	 * @param subject
	 * @param message
	 * @param to
	 * @throws FailedToLoadPropertiesException
	 * @throws FailedToSendMailException
	 */
	@Override
	public void sendSystemMail(String subject, String message, String to) throws FailedToLoadPropertiesException, FailedToSendMailException {

		Properties accountProps = null;

		try {

			//DONT CHANGE THIS PATH
			accountProps = propLoader.loadSystemProperty("/SystemMailAccount.properties");

			String user = accountProps.getProperty("mail.smtp.user");

			String password = accountProps.getProperty("mail.smtp.password");

			this.sendUserMailInternal(user, password, subject, message, to);

		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
			throw new FailedToLoadPropertiesException("Failed to load mail properties.");
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, null, ex);
			throw new FailedToSendMailException("Failed to send mail.");
		}



	}

	/**
	 * send a mail with the useraccount.
	 *
	 * @param account
	 * @param subject
	 * @param message
	 * @param to
	 * @throws FailedToSendMailException
	 * @throws FailedToLoadPropertiesException
	 * @throws Exception
	 */
	@Override
	public void sendUserMail(AbdAccount account, String subject, String message, String to) throws FailedToSendMailException, FailedToLoadPropertiesException, Exception {

		Properties masterPassword = propLoader.loadSystemProperty("/SystemChiperPassword.properties");
		String passwordDeciphered = CipherHelper.decipher(account.getPasswort(), masterPassword.getProperty("master"));

		sendUserMailInternal(account.getUsername(), passwordDeciphered, subject, message, to);
	}

	/**
	 * send a mail with any credentials.
	 *
	 * @param username
	 * @param password
	 * @param subject
	 * @param message
	 * @param to
	 * @throws FailedToSendMailException
	 * @throws FailedToLoadPropertiesException
	 * @throws Exception
	 */
	protected void sendUserMailInternal(String username, String password, String subject, String message, String to) throws FailedToSendMailException, FailedToLoadPropertiesException, Exception {
		Properties systemProps = null;
		try {

			//DONT CHANGE THIS PATH
			systemProps = propLoader.loadSystemProperty("/SystemMail.properties");

			//systemProps
			String host = systemProps.getProperty("mail.smtp.host");

			//Obtain the default mail session
			Session session = Session.getDefaultInstance(systemProps, null);
			session.setDebug(true);

			//Construct the mail message
			MimeMessage mail = new MimeMessage(session);

			mail.setText(message);
			mail.setSubject(subject);
			mail.setFrom(new InternetAddress(username));
			mail.addRecipient(RecipientType.TO, new InternetAddress(to));
			mail.saveChanges();

			//Use Transport to deliver the message
			Transport transport = session.getTransport("smtp");
			transport.connect(host, username, password);
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

	protected PropertyLoader getPropLoader() {
		return propLoader;
	}

	protected void setPropLoader(PropertyLoader propLoader) {
		this.propLoader = propLoader;
	}
}
