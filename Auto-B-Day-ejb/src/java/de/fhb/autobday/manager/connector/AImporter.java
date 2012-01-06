package de.fhb.autobday.manager.connector;

import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.exception.connector.ConnectorCouldNotLoginException;
import de.fhb.autobday.exception.connector.ConnectorInvalidAccountException;
import de.fhb.autobday.exception.connector.ConnectorNoConnectionException;

/**
 * Strategy-Muster zur Kapselung der Connectionart.
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public abstract class AImporter {
	public abstract void getConnection(AbdAccount data) throws ConnectorCouldNotLoginException, ConnectorInvalidAccountException;
	public abstract void importContacts() throws ConnectorNoConnectionException;
}
