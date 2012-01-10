package de.fhb.autobday.manager;

import de.fhb.autobday.dao.*;
import de.fhb.autobday.data.*;
import de.fhb.autobday.exception.AbdException;
import de.fhb.autobday.exception.contact.NoContactGivenException;
import de.fhb.autobday.exception.mail.MailException;
import de.fhb.autobday.manager.group.GroupManager;
import de.fhb.autobday.manager.mail.GoogleMailManagerLocal;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

/**
 * This manager schedueles the sending of bdaymails
 *
 * @author 
 * Michael Koppen <koppen@fh-brandenburg.de>,
 * Andy Klay <klay@fh-brandenburg.de>
 */
@Stateless
@Interceptors(LoggerInterceptor.class)
public class AbdManager implements AbdManagerLocal, Serializable {
	
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
	private GroupManager groupManager;


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
	
	//@Schedule(minute="*/1", hour="*")
	/*private void checkEveryMinute(){
		LOGGER.log(Level.INFO, "every minute idle message...{0}", new Date());
	}
	*/
	
	@Schedule(minute="0", hour="8")
	public void checkEveryDay() throws MailException,AbdException{
		
		LOGGER.log(Level.INFO, "EverDayCheck {0}", new Date(System.currentTimeMillis()));
		
		String parsedMessageFromTemplate = "Empty Message!";
		String template;
		String sender;
		AbdContact contact;
		AbdAccount actAccount;
		
		//search all contacts, which have bday today
		Collection<AbdContact> birthdayContacts = contactDAO.findContactByBday(new Date(System.currentTimeMillis()));
		
		if (!birthdayContacts.isEmpty()) {
			
			//go through all bday candidates
			for (AbdContact aktContact : birthdayContacts) {
				
				LOGGER.log(Level.INFO, "Contact with bday found: {0}", aktContact.toString());
				
				Collection<AbdGroupToContact> aktContactInGroups = aktContact.getAbdGroupToContactCollection();
				
				for (AbdGroupToContact aktGroupToContact : aktContactInGroups) {
					
					//if right relation found
					if(aktGroupToContact.getAbdContact().equals(aktContact)){
						//and if group is marked as active
						if (aktGroupToContact.getActive()) {
							//and if contactToGroup is marked as active
							if (aktGroupToContact.getAbdGroup().getActive()) {
								
								try {
									actAccount=aktGroupToContact.getAbdGroup().getAccount();
									template=aktGroupToContact.getAbdGroup().getTemplate();
									contact=aktGroupToContact.getAbdContact();
									sender =getSender(aktGroupToContact);
									
									//parse Template
									parsedMessageFromTemplate=groupManager.parseTemplate(template, contact);
																
									//and send mail
									try {
										mailManager.sendUserMail(actAccount, "Happy Birthday", parsedMessageFromTemplate, contact.getMail());
									} catch (Exception e) {
										LOGGER.log(Level.SEVERE, e.getMessage());
										throw new MailException(e.getMessage());
									}
									
								} catch (NoContactGivenException e) {
									LOGGER.log(Level.SEVERE, "No Contact given while trying to send bdaymail!");
									throw new AbdException("No Contact given while trying to send bdaymail!");
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