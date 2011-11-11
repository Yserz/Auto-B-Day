/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.autobday.dao;

import de.fhb.autobday.data.Accountdata;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author MacYser
 */
@Local
public interface AccountdataFacadeLocal {

	void create(Accountdata accountdata);

	void edit(Accountdata accountdata);

	void remove(Accountdata accountdata);

	Accountdata find(Object id);

	List<Accountdata> findAll();

	List<Accountdata> findRange(int[] range);

	int count();
	
}
