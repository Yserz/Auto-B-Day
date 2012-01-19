package de.fhb.autobday.exception;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class AbdRuntimeException extends RuntimeException {

	/**
	 * Constructor
	 */
	public AbdRuntimeException() {
		super();
	}

	/**
	 * Constructor with message
	 *
	 * @param string
	 */
	public AbdRuntimeException(String string) {
		super(string);
	}
}
