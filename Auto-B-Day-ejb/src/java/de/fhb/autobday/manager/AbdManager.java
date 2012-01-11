package de.fhb.autobday.manager;

import de.fhb.autobday.dao.AbdAccountFacade;
import de.fhb.autobday.dao.AbdContactFacade;
import de.fhb.autobday.dao.AbdGroupFacade;
import de.fhb.autobday.dao.AbdUserFacade;
import de.fhb.autobday.data.*;
import de.fhb.autobday.exception.contact.NoContactGivenException;
import de.fhb.autobday.manager.group.GroupManagerLocal;
import de.fhb.autobday.manager.mail.GoogleMailManagerLocal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.*;
import javax.interceptor.Interceptors;

/**
 * This manager schedueles the sending of bdaymails
 *
 * @author 
 * Michael Koppen <koppen@fh-brandenburg.de>,
 * Andy Klay <klay@fh-brandenburg.de>
 */
@Startup
@Stateless
@Local
@Interceptors(LoggerInterceptor.class)
public class AbdManager implements AbdManagerLocal{
	
	private final static Logger LOGGER = Logger.getLogger(AbdManager.class.getName());
	
	@EJB
	private AbdUserFacade userDAO;
	
	@EJB
	private AbdGroupFacade groupDAO;
	
	@EJB
	private AbdAccountFacade accountdataDAO;
	
	@EJB
	private AbdContactFacade contactDAO;
	
	@EJB
	private GoogleMailManagerLocal mailManager;
	
	@EJB
	private GroupManagerLocal groupManager;
	
	
	
	public AbdManager() {
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
	
	@Schedule(minute="*/1", hour="*")
	private void checkEveryMinute(){
		System.out.println("every minute idle message..."+new Date(System.currentTimeMillis()));

	}
	
	@Schedule(minute="13", hour="1")
	@Override
	public void checkEveryDay() {
		
		LOGGER.log(Level.INFO, "EverDayCheck {0}", new Date(System.currentTimeMillis()));
		
		String parsedMessageFromTemplate = "Empty Message!";
		String template;
		String sender;
		AbdContact contact;
		AbdAccount actAccount;
		
		//search all contacts, which have bday today
		Date currentDate = new Date(System.currentTimeMillis());
		LOGGER.log(Level.INFO, "Getting all Contacts");
		Collection<AbdContact> Contacts = contactDAO.findAll();
		
		List<AbdContact> birthdayContacts = new ArrayList<AbdContact>();
		LOGGER.log(Level.INFO, "Searching for birthday-contacts");
		for (AbdContact abdContact : Contacts) {
			System.out.println(abdContact.getFirstname()+" "+abdContact.getName()+" has bday on "+abdContact.getBday());
			
			if((abdContact.getBday().getDay()==currentDate.getDay())&&(abdContact.getBday().getMonth()==currentDate.getMonth())){
				LOGGER.log(Level.INFO, "Contact with bday found: {0}", abdContact);
				birthdayContacts.add(abdContact);
			}
		}
		
		if (!birthdayContacts.isEmpty()) {
			
			//go through all bday candidates
			for (AbdContact aktContact : birthdayContacts) {
				
				Collection<AbdGroupToContact> aktContactInGroups = aktContact.getAbdGroupToContactCollection();
				
				for (AbdGroupToContact aktGroupToContact : aktContactInGroups) {
					
					//if right relation found
					if(aktGroupToContact.getAbdContact().equals(aktContact)){
						//and if group is marked as active
						if (aktGroupToContact.getActive()) {
							//and if contactToGroup is marked as active
							if (aktGroupToContact.getAbdGroup().getActive()) {
								
								actAccount=aktGroupToContact.getAbdGroup().getAccount();
								template=aktGroupToContact.getAbdGroup().getTemplate();
								contact=aktGroupToContact.getAbdContact();
								sender =getSender(aktGroupToContact);
								try {
									//parse Template
									parsedMessageFromTemplate=groupManager.parseTemplate(template, contact);
								} catch (NoContactGivenException ex) {
									LOGGER.log(Level.SEVERE, null, ex);
								}

								//and send mail
								try {
									mailManager.sendUserMail(actAccount, "Happy Birthday", parsedMessageFromTemplate, contact.getMail());
								} catch (Exception e) {
									LOGGER.log(Level.SEVERE, e.getMessage());
									//throw new MailException(e.getMessage());
								}
							}
						}
					}

				}
			}			
			
		}else{
			LOGGER.log(Level.INFO,"No Birthdaycontacts found");
		}
		
	}
	
	private String getSender(AbdGroupToContact aktGroupToContact){
		 return aktGroupToContact.getAbdGroup().getAccount().getUsername();
	}
}