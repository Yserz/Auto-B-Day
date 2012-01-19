package de.fhb.autobday.exception.connector;

/**
 *
 * @author Tino Reuschel <reuschel@fh-brandenburg.de>
 */
public class ConnectorNoConnectionException extends ConnectorException {

	/**
	 * Constructor
	 */
	public ConnectorNoConnectionException() {
		super();
	}

	/**
	 * Constructor with message
	 *
	 * @param string
	 */
	public ConnectorNoConnectionException(String string) {
		super(string);
	}
}
