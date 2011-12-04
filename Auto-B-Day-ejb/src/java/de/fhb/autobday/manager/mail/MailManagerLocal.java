package de.fhb.autobday.manager.mail;

import javax.ejb.Local;

import de.fhb.autobday.exception.mail.MailException;

/**
 *
 * @author Michael Koppen
 */
@Local
public interface MailManagerLocal {

	void sendBdayMail();

	void sendNotificationMail();

	void sendForgotPasswordMail(int userId) throws MailException;
	
}
