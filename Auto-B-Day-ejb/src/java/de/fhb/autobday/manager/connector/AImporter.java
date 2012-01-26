package de.fhb.autobday.manager.connector;

import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.exception.connector.ConnectorCouldNotLoginException;
import de.fhb.autobday.exception.connector.ConnectorInvalidAccountException;
import de.fhb.autobday.exception.connector.ConnectorNoConnectionException;
import java.util.List;

/**
 * Strategy-Muster for capsulation of connectiontype.
 *
 * @author Michael Koppen mail: koppen@fh-brandenburg.de
 */
public abstract class AImporter {

	/**
	 * connect to a account (e.g. google)
	 *
	 * @param data
	 * @throws ConnectorCouldNotLoginException
	 * @throws ConnectorInvalidAccountException
	 */
	public abstract void getConnection(AbdAccount data) throws ConnectorCouldNotLoginException, ConnectorInvalidAccountException;

	/**
	 * importing the contacts information of the account which is given with the
	 * getConnection method
	 *
	 * @return errorStack
	 * @throws ConnectorNoConnectionException
	 */
	public abstract List<String> importContacts() throws ConnectorNoConnectionException;

	/**
	 * get information about success of connect
	 *
	 * @return boolean
	 */
	public abstract boolean isConnectionEtablished();
}
