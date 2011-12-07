package de.fhb.autobday.manager.mail;

import de.fhb.autobday.exception.mail.MailException;
import javax.ejb.Local;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@Local
public interface MailManagerLocal {

	void sendBdayMail();

	void sendNotificationMail();

	void sendForgotPasswordMail(int userId) throws MailException;
	
}
