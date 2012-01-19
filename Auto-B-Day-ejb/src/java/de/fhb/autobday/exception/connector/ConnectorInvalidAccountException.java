package de.fhb.autobday.exception.connector;

/**
 *
 * @author Tino Reuschel <reuschel@fh-brandenburg.de>
 */
public class ConnectorInvalidAccountException extends ConnectorException {

	/**
	 * Constructor
	 */
	public ConnectorInvalidAccountException() {
		super();
	}

	/**
	 * Constructor with message
	 *
	 * @param string
	 */
	public ConnectorInvalidAccountException(String string) {
		super(string);
	}
}
