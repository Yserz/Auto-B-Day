package de.fhb.autobday.exception.group;


/**
 *
 * @author Michael Koppen
 */
public class GroupNotFoundException extends GroupException {
	/**
	 * Konstruktor
	 */
	public GroupNotFoundException() {
		super();
	}
	
	/**
	 * Konstruktor mit Parameter fuer die Message
	 * @param string
	 */
	public GroupNotFoundException(String string) {
		super(string);
	}
}
