package de.fhb.autobday.exception;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class AbdException extends Exception {

	/**
	 * Constructor
	 */
	public AbdException() {
		super();
	}

	/**
	 * Constructor with message
	 *
	 * @param string
	 */
	public AbdException(String string) {
		super(string);
	}
}
