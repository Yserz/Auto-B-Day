package de.fhb.autobday.exception.account;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class AccountNotFoundException extends AccountException {

	/**
	 * Constructor
	 */
	public AccountNotFoundException() {
		super();
	}

	/**
	 * Constructor with message
	 *
	 * @param string
	 */
	public AccountNotFoundException(String string) {
		super(string);
	}
}
