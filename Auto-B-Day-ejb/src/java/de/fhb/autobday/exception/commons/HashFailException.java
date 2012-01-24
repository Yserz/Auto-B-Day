package de.fhb.autobday.exception.commons;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class HashFailException extends Exception {

	/**
	 * Constructor
	 */
	public HashFailException() {
		super();
	}

	/**
	 * Konstruktor mit Parameter fuer die Message
	 *
	 * @param string
	 */
	public HashFailException(String string) {
		super(string);
	}
}
