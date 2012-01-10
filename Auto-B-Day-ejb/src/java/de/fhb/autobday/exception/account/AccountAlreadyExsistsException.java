package de.fhb.autobday.exception.account;


/**
 *
 * @author
 * Michael Koppen <koppen@fh-brandenburg.de>
 */
public class AccountAlreadyExsistsException extends AccountException{
	
	/**
	 * Constructor
	 */
	public AccountAlreadyExsistsException() {
		super();
	}
	
	/**
	 * Constructor with message
	 * @param string
	 */
	public AccountAlreadyExsistsException(String string) {
		super(string);
	}
}
