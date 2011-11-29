package de.fhb.autobday.manager.connector;

import de.fhb.autobday.data.Accountdata;
import de.fhb.autobday.data.Contact;
import java.util.List;

/**
 * Strategy-Muster zur Kapselung der Connectionart.
 *
 * @author Michael Koppen
 */
public abstract class AImporter {
	public abstract void getConnection(Accountdata data);
	public abstract void importContacts();
}
