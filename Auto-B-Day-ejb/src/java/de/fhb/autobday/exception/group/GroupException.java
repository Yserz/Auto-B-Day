package de.fhb.autobday.exception.group;

import de.fhb.autobday.exception.AbdException;

/**
 *
 * @author
 * Michael Koppen <koppen@fh-brandenburg.de>
 */
public class GroupException extends AbdException {
	
	/**
	 * Constructor
	 */
	public GroupException() {
		super();
	}
	
	/**
	 * Constructor with message
	 * @param string
	 */
	public GroupException(String string) {
		super(string);
	}
}
