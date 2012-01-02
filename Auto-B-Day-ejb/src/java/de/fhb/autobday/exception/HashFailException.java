package de.fhb.autobday.exception;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class HashFailException extends Exception{
	/**
	 * Konstruktor
	 */
	public HashFailException() {
		super();
	}
	
	/**
	 * Konstruktor mit Parameter fuer die Message
	 * @param string
	 */
	public HashFailException(String string) {
		super(string);
	}
}
