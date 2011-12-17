package de.fhb.autobday.exception;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class AbdException extends Exception{
	/**
	 * Konstruktor
	 */
	public AbdException() {
		super();
	}
	
	/**
	 * Konstruktor mit Parameter fuer die Message
	 * @param string
	 */
	public AbdException(String string) {
		super(string);
	}
}
