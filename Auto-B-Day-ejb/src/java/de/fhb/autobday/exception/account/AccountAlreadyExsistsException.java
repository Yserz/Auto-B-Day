package de.fhb.autobday.exception.account;


/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class AccountAlreadyExsistsException extends AccountException{
	/**
	 * Konstruktor
	 */
	public AccountAlreadyExsistsException() {
		super();
	}
	
	/**
	 * Konstruktor mit Parameter fuer die Message
	 * @param string
	 */
	public AccountAlreadyExsistsException(String string) {
		super(string);
	}
}
