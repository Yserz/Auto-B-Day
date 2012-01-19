package de.fhb.autobday.exception.connector;

/**
 *
 * @author Tino Reuschel <reuschel@fh-brandenburg.de>
 */
public class ConnectorCouldNotLoginException extends ConnectorException {

	/**
	 * Constructor
	 */
	public ConnectorCouldNotLoginException() {
		super();
	}

	/**
	 * Constructor with message
	 *
	 * @param string
	 */
	public ConnectorCouldNotLoginException(String string) {
		super(string);
	}
}
