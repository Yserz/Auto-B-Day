package de.fhb.autobday.exception.connector;

public class ConnectorNoConnectionException extends ConnectorException{
	
	/**
	 * Konstruktor
	 */
	public ConnectorNoConnectionException() {
		super();
	}
	
	/**
	 * Konstruktor mit Parameter fuer die Message
	 * @param string
	 */
	public ConnectorNoConnectionException(String string) {
		super(string);
	}

}
