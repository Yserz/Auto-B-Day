package de.fhb.autobday.exception.contact;

/**
 * Exception die geworfen wird, wenn einer Methode kein Kontakt mitgegebn wurde
 *
 * @author Andy Klay <klay@fh-brandenburg.de>
 * @author Michael Koppen <koppen@fh-brandenburg.de>
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