package de.fhb.autobday.exception.connector;

import de.fhb.autobday.exception.ABDException;

/**
 *
 * @author Michael Koppen
 */
public class ConnectorException extends ABDException{
	/**
	 * Konstruktor
	 */
	public ConnectorException() {
		super();
	}
	
	/**
	 * Konstruktor mit Parameter fuer die Message
	 * @param string
	 */
	public ConnectorException(String string) {
		super(string);
	}
}
