/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.autobday.dao;

import de.fhb.autobday.data.Abdgroup;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author MacYser
 */
@Local
public interface AbdgroupFacadeLocal {

	void create(Abdgroup abdgroup);

	void edit(Abdgroup abdgroup);

	void remove(Abdgroup abdgroup);

	Abdgroup find(Object id);

	List<Abdgroup> findAll();

	List<Abdgroup> findRange(int[] range);

	int count();
	
}
