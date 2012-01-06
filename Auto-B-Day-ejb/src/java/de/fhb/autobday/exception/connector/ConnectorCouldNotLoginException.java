package de.fhb.autobday.exception.connector;

public class ConnectorCouldNotLoginException extends ConnectorException{
	
	/**
	 * Konstruktor
	 */
	public ConnectorCouldNotLoginException() {
		super();
	}
	
	/**
	 * Konstruktor mit Parameter fuer die Message
	 * @param string
	 */
	public ConnectorCouldNotLoginException(String string) {
		super(string);
	}
}

