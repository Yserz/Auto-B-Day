package de.fhb.autobday.exception.user;


/**
 * Exception die geworfen wird, wenn Logindaten fehlen
 *
 * @author Andy Klay <klay@fh-brandenburg.de>
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class IncompleteLoginDataException extends UserException {
	
	/**
	 * Konstruktor
	 */
	public IncompleteLoginDataException() {
		super();
	}
	
	/**
	 * Konstruktor mit Parameter fuer die Message
	 * @param string
	 */
	public IncompleteLoginDataException(String string) {
		super(string);
	}
	
}
