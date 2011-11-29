package de.fhb.autobday.manager.group;

import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.data.AbdGroup;
import de.fhb.autobday.exception.group.GroupException;

import javax.ejb.Local;

/**
 *
 * @author Michael Koppen
 */
@Local
public interface GroupManagerLocal {

	AbdGroup getGroup(int groupid);

	void setTemplate(int groupid, String template);

	String getTemplate(int groupid);

	String testTemplate(int groupid, String contactid)throws GroupException;

	void setActive(int groupid, boolean active);
	
	String parseTemplate(String template, AbdContact contact);
	
	String parseSlashExpression(String expression, char sex);
}