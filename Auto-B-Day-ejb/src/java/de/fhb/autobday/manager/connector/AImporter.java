package de.fhb.autobday.manager.connector;

import de.fhb.autobday.data.Abdaccount;

/**
 * Strategy-Muster zur Kapselung der Connectionart.
 *
 * @author Michael Koppen
 */
public abstract class AImporter {
	public abstract void getConnection(Abdaccount data);
	public abstract void importContacts();
}
