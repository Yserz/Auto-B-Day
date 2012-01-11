package de.fhb.autobday.manager;

import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.data.AbdGroup;
import de.fhb.autobday.data.AbdUser;
import de.fhb.autobday.exception.AbdException;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 
 * Michael Koppen <koppen@fh-brandenburg.de>
 * Andy Klay <klay@fh-brandenburg.de>
 */
@Local
public interface AbdManagerLocal {

	/**
	 *  get all users
	 * @return List<AbdUser>
	 */
	List<AbdUser> getAllUser();

	/**
	 * get all groups
	 * @return List<AbdGroup>
	 */
	List<AbdGroup> getAllGroups();

	/**
	 * get all accounts
	 * @return List<AbdAccount>
	 */
	List<AbdAccount> getAllAccountdata();

	/**
	 * get all contacts
	 * @return List<AbdContact>
	 */
	List<AbdContact> getAllContacts();
	
	/**
	 * this method checks once a day if someone has bday today
	 * a sending a mail if this condition is fulfilled
	 */
	void checkEveryDay();
}