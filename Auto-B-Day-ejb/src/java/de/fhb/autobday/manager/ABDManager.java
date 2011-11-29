package de.fhb.autobday.manager;

import de.fhb.autobday.dao.AbdAccountFacade;
import de.fhb.autobday.dao.AbdContactFacade;
import de.fhb.autobday.dao.AbdGroupFacade;
import de.fhb.autobday.dao.AbdGroupToContactFacade;
import de.fhb.autobday.dao.AbdUserFacade;
import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.data.AbdUser;
import de.fhb.autobday.data.AbdGroup;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

/**
 *
 * @author Michael Koppen
 */
@Stateless
public class ABDManager implements ABDManagerLocal, Serializable {
	private final static Logger LOGGER = Logger.getLogger(ABDManager.class.getName());
	
	@EJB
	private AbdUserFacade userDAO;
	@EJB
	private AbdGroupFacade groupDAO;
	@EJB
	private AbdGroupToContactFacade grouptocontactDAO;
	@EJB
	private AbdAccountFacade accountdataDAO;
	@EJB
	private AbdContactFacade contactDAO;
	


	public ABDManager() {
	}

	@Override
	public List<AbdUser> getAllUser() {
		return userDAO.findAll();
	}

	@Override
	public List<AbdGroup> getAllGroups() {
		return groupDAO.findAll();
	}

	@Override
	public List<AbdAccount> getAllAccountdata() {
		return accountdataDAO.findAll();
	}

	@Override
	public List<AbdContact> getAllContacts() {
		return contactDAO.findAll();
	}
	@Override
	public String hallo() {
		return "hallo";
	}
	
	@Schedule(minute="*/1", hour="*")
	private void checkEveryMinute(){
		System.out.println("every minute idle message..."+new Date());
		
	}
	
	@Schedule(minute="50", hour="23")
	private void checkEveryDay(){
		System.out.println("every hour idle message..."+new Date());
		
		AbdGroup aktGroup = null;
		String parsedTemplate = "";
		Collection<AbdContact> birthdayContacts = contactDAO.findContactByBday(new Date());
		
		if (birthdayContacts.isEmpty()) {
			System.out.println("No Birthdaycontacts found");
		}else{
			for (AbdContact aktContact : birthdayContacts) {
				System.out.println("Contact: "+aktContact.toString());
				//TODO find contacts group
			}
		}
		
	}
}
