package de.fhb.autobday.exception.account;


/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class AccountNotFoundException extends AccountException{
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
