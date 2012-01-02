package de.fhb.autobday.manager.mail;

import de.fhb.autobday.exception.mail.MailException;
import de.fhb.autobday.exception.user.UserNotFoundException;

import javax.ejb.Local;

/**
 * This is the mailmanager, which is responsible for sending mails
 *
 * @author
 * Andy Klay <klay@fh-brandenburg.de>
 * Michael Koppen <koppen@fh-brandenburg.de>
 */
@Local
public interface MailManagerLocal {

	/**
	 * send a bdaymail for a contact of a user
	 * 
	 * @param from
	 * @param to
	 * @param subject
	 * @param body
	 */
	void sendBdayMail(String from, String to, String subject, String body);


	/**
	 * send a notificationmail
	 */
	void sendNotificationMail();

	/**
	 * send a mail for a new password
	 * 
	 * @param userId
	 * @throws MailException
	 * @throws UserNotFoundException
	 */
	void sendForgotPasswordMail(int userId) throws MailException, UserNotFoundException ;

	
}