package de.fhb.autobday.exception.group;

import de.fhb.autobday.exception.AbdException;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class GroupException extends AbdException {
	/**
	 * Konstruktor
	 */
	public GroupException() {
		super();
	}
	
	/**
	 * Konstruktor mit Parameter fuer die Message
	 * @param string
	 */
	public GroupException(String string) {
		super(string);
	}
}
