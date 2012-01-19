package de.fhb.autobday.exception.account;

import de.fhb.autobday.exception.AbdException;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class AccountException extends AbdException {

	/**
	 * Constructor
	 */
	public AccountException() {
		super();
	}

	/**
	 * Constructor with message
	 *
	 * @param string
	 */
	public AccountException(String string) {
		super(string);
	}
}
