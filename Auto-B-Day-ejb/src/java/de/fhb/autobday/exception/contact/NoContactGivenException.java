package de.fhb.autobday.exception.contact;

/**
 * Exception die geworfen wird, wenn ein Kontakt in einer Gruppe nicht gefunden wird
 *
 * @author Andy Klay <klay@fh-brandenburg.de>
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class NoContactGivenException extends ContactException {
	
	/**
	 * Konstruktor
	 */
	public NoContactGivenException() {
		super();
	}
	
	/**
	 * Konstruktor mit Parameter fuer die Message
	 * @param string
	 */
	public NoContactGivenException(String string) {
		super(string);
	}
	
}