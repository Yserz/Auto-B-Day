package de.fhb.autobday.manager.mail;

import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.exception.mail.FailedToLoadPropertiesException;
import de.fhb.autobday.exception.mail.FailedToSendMailException;
import javax.ejb.Local;

/**
 * This is the mailmanager, which is responsible for sending mails
 *
 * @author Andy Klay <klay@fh-brandenburg.de> Michael Koppen
 * <koppen@fh-brandenburg.de>
 */
@Local
public interface GoogleMailManagerLocal {

	/**
	 * send a system mail
	 *
	 * @param subject
	 * @param message
	 * @param to
	 * @throws FailedToLoadPropertiesException
	 * @throws FailedToSendMailException
	 */
	void sendSystemMail(String subject, String message, String to)
			throws FailedToLoadPropertiesException, FailedToSendMailException;

	/**
	 * send a user mail
	 *
	 * @param account
	 * @param subject
	 * @param message
	 * @param to
	 * @throws FailedToSendMailException
	 * @throws FailedToLoadPropertiesException
	 * @throws Exception
	 */
	void sendUserMail(AbdAccount account, String subject, String message, String to)
			throws FailedToSendMailException, FailedToLoadPropertiesException, Exception;
}