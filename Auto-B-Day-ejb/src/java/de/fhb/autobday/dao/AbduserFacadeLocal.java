/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.autobday.dao;

import de.fhb.autobday.data.Abduser;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author MacYser
 */
@Local
public interface AbduserFacadeLocal {

	void create(Abduser abduser);

	void edit(Abduser abduser);

	void remove(Abduser abduser);

	Abduser find(Object id);

	List<Abduser> findAll();

	List<Abduser> findRange(int[] range);

	int count();
	
}
