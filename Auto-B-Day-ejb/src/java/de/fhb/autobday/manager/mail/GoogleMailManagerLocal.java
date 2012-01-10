package de.fhb.autobday.manager.mail;

import de.fhb.autobday.commons.PropertyLoader;
import de.fhb.autobday.data.AbdAccount;
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
public interface GoogleMailManagerLocal {

	/**
	 * send a system mail
	 * @param subject
	 * @param message
	 * @param to
	 * @throws Exception
	 */
	void sendSystemMail(String subject, String message, String to) throws Exception ;

	/**
	 * send a user mail
	 * 
	 * @param account
	 * @param subject
	 * @param message
	 * @param to
	 * @throws Exception
	 */
	void sendUserMail(AbdAccount account, String subject, String message, String to) throws Exception;
	
	/**
	 * get PropLoader
	 * 
	 * @return
	 */
	PropertyLoader getPropLoader();
	
	/**
	 * set PropLoader
	 * @param propLoader
	 */
	void setPropLoader(PropertyLoader propLoader);

	
}