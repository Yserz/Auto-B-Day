package de.fhb.autobday.exception.contact;

/**
 * Exception, which is thrown, if to a method is no contact given
 *
 * @author Andy Klay <klay@fh-brandenburg.de>
 */
public class NoContactInThisGroupException extends ContactException {

	/**
	 * Constructor
	 */
	public NoContactInThisGroupException() {
		super();
	}

	/**
	 * Constructor with message
	 *
	 * @param string
	 */
	public NoContactInThisGroupException(String string) {
		super(string);
	}
}