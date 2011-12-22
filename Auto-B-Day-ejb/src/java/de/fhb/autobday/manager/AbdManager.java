package de.fhb.autobday.manager;

import de.fhb.autobday.dao.*;
import de.fhb.autobday.data.*;
import de.fhb.autobday.exception.AbdException;
import de.fhb.autobday.exception.contact.NoContactGivenException;
import de.fhb.autobday.manager.group.GroupManager;
import de.fhb.autobday.manager.mail.MailManagerLocal;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

/**
 * This manager speaks to the scheduler and ???
 * TODO bitte ergaenzen!! wer findet eine passende beschreibung?
 *
 * @author 
 * Michael Koppen <koppen@fh-brandenburg.de>,
 * Andy Klay <klay@fh-brandenburg.de>
 */
@Stateless
public class AbdManager implements AbdManagerLocal, Serializable {
	
	private final static Logger LOGGER = Logger.getLogger(AbdManager.class.getName());
	
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
	
	@EJB
	private MailManagerLocal mailManager;
	
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
	@Override
	public String hallo() {
		return "hallo";
	}
	
	//@Schedule(minute="*/1", hour="*")
	/*private void checkEveryMinute(){
		LOGGER.log(Level.INFO, "every minute idle message...{0}", new Date());
	}
	*//*
	@Schedule(minute="0", hour="8")
	private void checkEveryDay() throws AbdException{
		
		LOGGER.log(Level.INFO, "EverDayCheck {0}", new Date());
		
		String parsedMessageFromTemplate = "Empty Message!";
		String template;
		AbdContact contact;
		
		//search all contacts, which have bday today
		Collection<AbdContact> birthdayContacts = contactDAO.findContactByBday(new Date());
		
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
									
									template=aktGroupToContact.getAbdGroup().getTemplate();
									contact=aktGroupToContact.getAbdContact();
									
									//parse Template
									parsedMessageFromTemplate=groupManager.parseTemplate(template, contact);
									
									//TODO absender aendern								
									//and send mail
									mailManager.sendBdayMail("autobday@smile.de",contact.getMail(), "Happy Birthday", parsedMessageFromTemplate);
									
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
	*/
}