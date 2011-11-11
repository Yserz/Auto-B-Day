/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.autobday.dao;

import de.fhb.autobday.data.Contact;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author MacYser
 */
@Local
public interface ContactFacadeLocal {

	void create(Contact contact);

	void edit(Contact contact);

	void remove(Contact contact);

	Contact find(Object id);

	List<Contact> findAll();

	List<Contact> findRange(int[] range);

	int count();
	
}
