package de.fhb.autobday.exception.user;


/**
 *
 * @author 
 * Michael Koppen <koppen@fh-brandenburg.de>
 * Andy Klay <klay@fh-brandenburg.de>
 */
public class NoValidUserNameException extends UserException {
	/**
	 * Konstruktor
	 */
	public NoValidUserNameException() {
		super();
	}
	
	/**
	 * Konstruktor mit Parameter fuer die Message
	 * @param string
	 */
	public NoValidUserNameException(String string) {
		super(string);
	}
}
