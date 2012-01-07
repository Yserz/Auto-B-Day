package de.fhb.autobday.exception.group;


/**
 *
 * @author 
 * Andy Klay <klay@fh-brandenburg.de>
 * 
 */
public class NoGroupGivenException extends GroupException {
	
	/**
	 * Konstruktor
	 */
	public NoGroupGivenException() {
		super();
	}
	
	/**
	 * Konstruktor mit Parameter fuer die Message
	 * @param string
	 */
	public NoGroupGivenException(String string) {
		super(string);
	}
	
}