package de.fhb.autobday.manager;

import de.fhb.autobday.dao.AbdaccountFacade;
import de.fhb.autobday.dao.AbdcontactFacade;
import de.fhb.autobday.dao.AbdgroupFacade;
import de.fhb.autobday.dao.AbduserFacade;
import de.fhb.autobday.data.Abdaccount;
import de.fhb.autobday.data.Abdcontact;
import de.fhb.autobday.data.Abduser;
import de.fhb.autobday.data.Abdgroup;
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
	private AbduserFacade userDAO;
	@EJB
	private AbdgroupFacade groupDAO;
	@EJB
	private AbdaccountFacade accountdataDAO;
	@EJB
	private AbdcontactFacade contactDAO;
	


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
	public List<Abdaccount> getAllAccountdata() {
		return accountdataDAO.findAll();
	}

	@Override
	public List<Abdcontact> getAllContacts() {
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
		Collection<Abdcontact> birthdayContacts = contactDAO.findContactByBday(new Date());
		
		if (birthdayContacts.isEmpty()) {
			System.out.println("No Birthdaycontacts found");
		}else{
			for (Abdcontact aktContact : birthdayContacts) {
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
	private String parseTemplate(String unparsedTemplate, Abduser user, Abdcontact contact) {
		//TODO get information about user and contact
		//TODO Parse the template with information
		return null;
	}
}
