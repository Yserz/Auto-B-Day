package de.fhb.autobday.exception.account;

import de.fhb.autobday.exception.ABDException;

/**
 *
 * @author Michael Koppen
 */
public class AccountException extends ABDException{
	/**
	 * Konstruktor
	 */
	public AccountException() {
		super();
	}
	
	/**
	 * Konstruktor mit Parameter fuer die Message
	 * @param string
	 */
	public AccountException(String string) {
		super(string);
	}
}
