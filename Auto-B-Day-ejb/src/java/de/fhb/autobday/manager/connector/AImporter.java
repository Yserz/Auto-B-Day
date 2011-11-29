package de.fhb.autobday.manager.connector;

import de.fhb.autobday.data.AbdAccount;

/**
 * Strategy-Muster zur Kapselung der Connectionart.
 *
 * @author Michael Koppen
 */
public abstract class AImporter {
	public abstract void getConnection(AbdAccount data);
	public abstract void importContacts();
}
