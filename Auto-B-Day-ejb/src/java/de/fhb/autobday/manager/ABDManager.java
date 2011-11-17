package de.fhb.autobday.manager;

import de.fhb.autobday.dao.AbdgroupFacade;
import de.fhb.autobday.dao.AbduserFacade;
import de.fhb.autobday.dao.AccountdataFacade;
import de.fhb.autobday.dao.ContactFacade;
import de.fhb.autobday.data.Abduser;
import de.fhb.autobday.data.Abdgroup;
import de.fhb.autobday.data.Accountdata;
import de.fhb.autobday.data.Contact;
import de.fhb.autobday.manager.group.GroupManager;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;

/**
 *
 * @author Michael Koppen
 */
@Startup
@Singleton
public class ABDManager implements ABDManagerLocal, Serializable {
	private final static Logger LOGGER = Logger.getLogger(ABDManager.class.getName());
	
	@EJB
	private AbduserFacade userDAO;
	@EJB
	private AbdgroupFacade groupDAO;
	@EJB
	private AccountdataFacade accountdataDAO;
	@EJB
	private ContactFacade contactDAO;
	


	public ABDManager() {
	}

	@Override
	public List<Abduser> getAllUser() {
		return userDAO.findAll();
	}

	@Override
	public List<Abdgroup> getAllGroups() {
		return groupDAO.findAll();
	}

	@Override
	public List<Accountdata> getAllAccountdata() {
		return accountdataDAO.findAll();
	}

	@Override
	public List<Contact> getAllContacts() {
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
		
		Abdgroup aktGroup = null;
		String parsedTemplate = "";
		Collection<Contact> birthdayContacts = contactDAO.findContactByBday(new Date());
		
		if (birthdayContacts.isEmpty()) {
			System.out.println("No Birthdaycontacts found");
		}else{
			for (Contact aktContact : birthdayContacts) {
				System.out.println("Contact: "+aktContact.toString());
				if (aktContact.getActive()==true) {
					System.out.println("Contakt is aktive");
					aktGroup = aktContact.getAbdgroup();
					if (aktGroup.getActive()==true) {
						System.out.println("parentgroup is aktive");
						parsedTemplate = parseTemplate(aktGroup.getTemplate(), 
													   aktGroup.getAccount().getAbduser(), 
													   aktContact);
					}
				}
			}
		}
		
	}
	private String parseTemplate(String unparsedTemplate, Abduser user, Contact contact) {
		//TODO get information about user and contact
		//TODO Parse the template with information
		return null;
	}
}
