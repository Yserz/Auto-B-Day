package de.fhb.autobday.exception;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class ABDException extends Exception{
	/**
	 * Konstruktor
	 */
	public ABDException() {
		super();
	}
	
	/**
	 * Konstruktor mit Parameter fuer die Message
	 * @param string
	 */
	public ABDException(String string) {
		super(string);
	}
}
