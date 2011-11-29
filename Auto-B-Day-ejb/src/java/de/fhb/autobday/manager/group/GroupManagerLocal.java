package de.fhb.autobday.manager.group;

import de.fhb.autobday.data.Abdgroup;
import de.fhb.autobday.data.Contact;

import javax.ejb.Local;

/**
 *
 * @author Michael Koppen
 */
@Local
public interface GroupManagerLocal {

	Abdgroup getGroup(int groupid);

	void setTemplate();

	void getTemplate();

	void testTemplate();

	void setActive();
	
	String parseTemplate(String template, Contact contact);
	
	String parseSlashExpression(String expression, char sex);
}