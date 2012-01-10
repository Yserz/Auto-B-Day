package de.fhb.autobday.exception.user;


/**
 *
 * @author 
 * Michael Koppen <koppen@fh-brandenburg.de>
 * Andy Klay <klay@fh-brandenburg.de>
 */
public class NoValidUserNameException extends UserException {
	
	/**
	 * Constructor
	 */
	public NoValidUserNameException() {
		super();
	}
	
	/**
	 * Constructor with message
	 * @param string
	 */
	public NoValidUserNameException(String string) {
		super(string);
	}
}
