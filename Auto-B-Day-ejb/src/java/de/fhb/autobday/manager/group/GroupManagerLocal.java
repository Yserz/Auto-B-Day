package de.fhb.autobday.manager.group;

import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.data.AbdGroup;

import javax.ejb.Local;

/**
 *
 * @author Michael Koppen
 */
@Local
public interface GroupManagerLocal {

	AbdGroup getGroup(int groupid);

	void setTemplate();

	void getTemplate();

	void testTemplate();

	void setActive();
	
	String parseTemplate(String template, AbdContact contact);
	
	String parseSlashExpression(String expression, char sex);
}