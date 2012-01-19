package de.fhb.autobday.exception.group;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class GroupNotFoundException extends GroupException {

	/**
	 * Constructor
	 */
	public GroupNotFoundException() {
		super();
	}

	/**
	 * Constructor with message
	 *
	 * @param string
	 */
	public GroupNotFoundException(String string) {
		super(string);
	}
}
