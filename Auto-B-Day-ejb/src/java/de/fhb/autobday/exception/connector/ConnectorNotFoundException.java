package de.fhb.autobday.exception.connector;


/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class ConnectorNotFoundException extends ConnectorException{
	/**
	 * Konstruktor
	 */
	public ConnectorNotFoundException() {
		super();
	}
	
	/**
	 * Konstruktor mit Parameter fuer die Message
	 * @param string
	 */
	public ConnectorNotFoundException(String string) {
		super(string);
	}
}
