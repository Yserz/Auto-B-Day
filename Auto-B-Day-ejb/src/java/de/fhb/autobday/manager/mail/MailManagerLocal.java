package de.fhb.autobday.manager.mail;

import javax.ejb.Local;

/**
 *
 * @author Michael Koppen
 */
@Local
public interface MailManagerLocal {

	void sendBdayMail();

	void sendNotificationMail();

	void sendForgotPasswordMail();
	
}
