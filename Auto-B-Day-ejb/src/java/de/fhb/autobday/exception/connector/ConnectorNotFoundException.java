package de.fhb.autobday.exception.connector;

/**
 *
 * @author
 * Michael Koppen <koppen@fh-brandenburg.de>
 */
public class ConnectorNotFoundException extends ConnectorException{
	
	/**
	 * Constructor
	 */
	public ConnectorNotFoundException() {
		super();
	}
	
	/**
	 * Constructor with message
	 * @param string
	 */
	public ConnectorNotFoundException(String string) {
		super(string);
	}
}
