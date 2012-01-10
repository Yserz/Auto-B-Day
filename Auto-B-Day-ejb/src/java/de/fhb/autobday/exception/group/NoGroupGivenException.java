package de.fhb.autobday.exception.group;


/**
 *
 * @author 
 * Andy Klay <klay@fh-brandenburg.de>
 */
public class NoGroupGivenException extends GroupException {
	
	/**
	 * Constructor
	 */
	public NoGroupGivenException() {
		super();
	}
	
	/**
	 * Constructor with message
	 * @param string
	 */
	public NoGroupGivenException(String string) {
		super(string);
	}
	
}