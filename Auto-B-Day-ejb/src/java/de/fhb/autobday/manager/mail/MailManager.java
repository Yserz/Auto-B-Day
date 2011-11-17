package de.fhb.autobday.manager.mail;

import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 * TODO This will may change position to ABDManager!!!
 *
 * @author Michael Koppen
 */
@Stateless
public class MailManager implements MailManagerLocal {
	private final static Logger LOGGER = Logger.getLogger(MailManager.class.getName());

	@Override
	public void sendMail() {
	}

	
	
}
