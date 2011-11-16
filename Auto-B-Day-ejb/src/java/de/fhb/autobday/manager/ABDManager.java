package de.fhb.autobday.manager;

import de.fhb.autobday.dao.AbdgroupFacade;
import de.fhb.autobday.dao.AbduserFacade;
import de.fhb.autobday.dao.AccountdataFacade;
import de.fhb.autobday.dao.ContactFacade;
import de.fhb.autobday.data.Abduser;
import de.fhb.autobday.data.Abdgroup;
import de.fhb.autobday.data.Accountdata;
import de.fhb.autobday.data.Contact;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Michael Koppen
 */
@Stateless
public class ABDManager implements ABDManagerLocal, Serializable {
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
	public List<Abduser> showAllUser() {
		return userDAO.findAll();
	}

	@Override
	public List<Abdgroup> showAllGroups() {
		return groupDAO.findAll();
	}

	@Override
	public List<Accountdata> showAllAccountdata() {
		return accountdataDAO.findAll();
	}

	@Override
	public List<Contact> showAllContacts() {
		return contactDAO.findAll();
	}
	@Override
	public String hallo() {
		return "hallo";
	}
	@Schedule(minute="*/1", hour="*")
	private void bla(){
		System.out.println("every minute idle message..."+new Date());
	}
	
}
