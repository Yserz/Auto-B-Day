package de.fhb.autobday.exception;

/**
 *
 * @author Michael Koppen
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
