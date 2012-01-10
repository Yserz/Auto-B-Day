package de.fhb.autobday.manager.connector;

import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.exception.connector.ConnectorCouldNotLoginException;
import de.fhb.autobday.exception.connector.ConnectorInvalidAccountException;
import de.fhb.autobday.exception.connector.ConnectorNoConnectionException;

/**
 * Strategy-Muster for capsulation of connectiontype.
 *
 * @author
 * Michael Koppen <koppen@fh-brandenburg.de>
 */
public abstract class AImporter {
	
	/**
	 * connect to a account (e.g. google)
	 * 
	 * @param AbdAccount data
	 * @throws ConnectorCouldNotLoginException
	 * @throws ConnectorInvalidAccountException
	 */
	public abstract void getConnection(AbdAccount data) throws ConnectorCouldNotLoginException, ConnectorInvalidAccountException;
	
	/**
	 * importing the contacts information of the account which is given with the getConnection method
	 * @throws ConnectorNoConnectionException
	 */
	public abstract void importContacts() throws ConnectorNoConnectionException;
	
	/**
	 * get information about success of connect
	 * @return
	 */
	public abstract boolean isConnectionEtablished();
}
