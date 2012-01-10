package de.fhb.autobday.exception.contact;

/**
 * Exception, which is thrown, if no ContactToGroupRealtion found 
 *
 * @author
 * Andy Klay <klay@fh-brandenburg.de>
 */
public class ContactToGroupNotFoundException extends ContactException {
	
	/**
	 * Constructor
	 */
	public ContactToGroupNotFoundException() {
		super();
	}
	
	/**
	 * Constructor with message
	 * @param string
	 */
	public ContactToGroupNotFoundException(String string) {
		super(string);
	}
	
}