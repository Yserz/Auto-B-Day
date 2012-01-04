package de.fhb.autobday.exception.account;


/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class NoConnectionException extends AccountException{
	/**
	 * Konstruktor
	 */
	public NoConnectionException() {
		super();
	}
	
	/**
	 * Konstruktor mit Parameter fuer die Message
	 * @param string
	 */
	public NoConnectionException(String string) {
		super(string);
	}
}
