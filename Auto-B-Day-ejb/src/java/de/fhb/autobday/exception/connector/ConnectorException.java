package de.fhb.autobday.exception.connector;

import de.fhb.autobday.exception.AbdException;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class ConnectorException extends AbdException {

	/**
	 * Constructor
	 */
	public ConnectorException() {
		super();
	}

	/**
	 * Constructor with message
	 *
	 * @param string
	 */
	public ConnectorException(String string) {
		super(string);
	}
}
