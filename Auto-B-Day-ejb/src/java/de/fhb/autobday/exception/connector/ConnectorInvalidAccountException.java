package de.fhb.autobday.exception.connector;

public class ConnectorInvalidAccountException extends ConnectorException{
	
	/**
	 * Konstruktor
	 */
	public ConnectorInvalidAccountException() {
		super();
	}
	
	/**
	 * Konstruktor mit Parameter fuer die Message
	 * @param string
	 */
	public ConnectorInvalidAccountException(String string) {
		super(string);
	}

}
