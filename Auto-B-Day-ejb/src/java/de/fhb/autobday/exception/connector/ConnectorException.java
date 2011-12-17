package de.fhb.autobday.exception.connector;

import de.fhb.autobday.exception.AbdException;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class ConnectorException extends AbdException{
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
