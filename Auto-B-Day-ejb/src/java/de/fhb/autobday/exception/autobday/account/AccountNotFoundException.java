package de.fhb.autobday.exception.autobday.account;

import de.fhb.autobday.exception.ABDException;

/**
 *
 * @author MacYser
 */
public class AccountNotFoundException extends ABDException{
	/**
	 * Konstruktor
	 */
	public AccountNotFoundException() {
		super();
	}
	
	/**
	 * Konstruktor mit Parameter fuer die Message
	 * @param string
	 */
	public AccountNotFoundException(String string) {
		super(string);
	}
}
