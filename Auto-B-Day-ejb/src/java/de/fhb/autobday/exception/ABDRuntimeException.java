package de.fhb.autobday.exception;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class ABDRuntimeException extends RuntimeException{
	/**
	 * Konstruktor
	 */
	public ABDRuntimeException() {
		super();
	}
	
	/**
	 * Konstruktor mit Parameter fuer die Message
	 * @param string
	 */
	public ABDRuntimeException(String string) {
		super(string);
	}
}
