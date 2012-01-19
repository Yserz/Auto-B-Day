package de.fhb.autobday.exception;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class CanNotConvetGoogleBirthdayException extends AbdException {

	/**
	 * Constructor
	 */
	public CanNotConvetGoogleBirthdayException() {
		super();
	}

	/**
	 * Constructor with message
	 *
	 * @param string
	 */
	public CanNotConvetGoogleBirthdayException(String string) {
		super(string);
	}
}
