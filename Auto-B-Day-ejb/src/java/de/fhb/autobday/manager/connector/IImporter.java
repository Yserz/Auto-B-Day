package de.fhb.autobday.manager.connector;

import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.exception.connector.ConnectorCouldNotLoginException;
import de.fhb.autobday.exception.connector.ConnectorInvalidAccountException;
import de.fhb.autobday.exception.connector.ConnectorNoConnectionException;
import java.util.List;

/**
 *
 * @author Michael Koppen mail: koppen@fh-brandenburg.de
 */
public interface IImporter {
	/**
	 * connect to a account (e.g. google)
	 *
	 * @param data
	 * @throws ConnectorCouldNotLoginException
	 * @throws ConnectorInvalidAccountException
	 */
	void getConnection(AbdAccount data) throws ConnectorCouldNotLoginException, ConnectorInvalidAccountException;

	/**
	 * importing the contacts information of the account which is given with the
	 * getConnection method
	 *
	 * @return errorStack
	 * @throws ConnectorNoConnectionException
	 */
	List<String> importContacts() throws ConnectorNoConnectionException;

	/**
	 * get information about success of connect
	 *
	 * @return boolean
	 */
	boolean isConnectionEtablished();
}
