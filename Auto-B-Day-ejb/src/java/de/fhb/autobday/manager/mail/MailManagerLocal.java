package de.fhb.autobday.manager.mail;

import de.fhb.autobday.exception.mail.MailException;
import de.fhb.autobday.exception.user.UserNotFoundException;

import javax.ejb.Local;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@Local
public interface MailManagerLocal {

	void sendBdayMail(String from, String to, String subject, String body);

	void sendNotificationMail();

	void sendForgotPasswordMail(int userId) throws MailException, UserNotFoundException ;
	
}
