package de.fhb.autobday.exception.contact;

/**
 * Exception die geworfen wird, wenn ein Kontakt in einer Gruppe nicht gefunden wird
 *
 * @author Andy Klay <klay@fh-brandenburg.de>
 */
public class NoContactInThisGroupException extends ContactException {
	
	/**
	 * Konstruktor
	 */
	public NoContactInThisGroupException() {
		super();
	}
	
	/**
	 * Konstruktor mit Parameter fuer die Message
	 * @param string
	 */
	public NoContactInThisGroupException(String string) {
		super(string);
	}
	
}