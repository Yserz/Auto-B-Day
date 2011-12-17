package de.fhb.autobday.exception;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class AbdRuntimeException extends RuntimeException{
	/**
	 * Konstruktor
	 */
	public AbdRuntimeException() {
		super();
	}
	
	/**
	 * Konstruktor mit Parameter fuer die Message
	 * @param string
	 */
	public AbdRuntimeException(String string) {
		super(string);
	}
}
