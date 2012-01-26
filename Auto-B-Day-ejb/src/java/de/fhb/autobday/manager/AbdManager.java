package de.fhb.autobday.manager;

import de.fhb.autobday.dao.AbdAccountFacade;
import de.fhb.autobday.dao.AbdContactFacade;
import de.fhb.autobday.dao.AbdGroupFacade;
import de.fhb.autobday.dao.AbdUserFacade;
import de.fhb.autobday.data.*;
import de.fhb.autobday.exception.contact.NoContactGivenException;
import de.fhb.autobday.manager.group.GroupManagerLocal;
import de.fhb.autobday.manager.mail.GoogleMailManagerLocal;
import java.util.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.*;
import javax.interceptor.Interceptors;

/**
 * Implementation of AbdManager.
 *
 * @author Michael Koppen mail: koppen@fh-brandenburg.de
 * @author Andy Klay mail: klay@fh-brandenburg.de
 */
@Startup
@Stateless
@Local
@Interceptors(LoggerInterceptor.class)
public class AbdManager implements AbdManagerLocal {

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
		initLoggers();
	}

	/**
	 * {@inheritDoc}
	 *
	 */
	@Override
	public List<AbdUser> getAllUser() {
		return userDAO.findAll();
	}

	/**
	 * {@inheritDoc}
	 *
	 */
	@Override
	public List<AbdGroup> getAllGroups() {
		return groupDAO.findAll();
	}

	/**
	 * {@inheritDoc}
	 *
	 */
	@Override
	public List<AbdAccount> getAllAccountdata() {
		return accountdataDAO.findAll();
	}

	/**
	 * {@inheritDoc}
	 *
	 */
	@Override
	public List<AbdContact> getAllContacts() {
		return contactDAO.findAll();
	}

	@Schedule(minute = "*/1", hour = "*")
	private void checkEveryMinute() {
		LOGGER.log(Level.INFO, "every minute idle message...{0}", new Date(System.currentTimeMillis()));

	}

	/**
	 * {@inheritDoc}
	 *
	 */
	@Schedule(minute = "53", hour = "20")
	@Override
	public void checkEveryDay() {

		LOGGER.log(Level.INFO, "EverDayCheck {0}", new Date(System.currentTimeMillis()));


		Calendar currentDateCal = Calendar.getInstance();
		Calendar bdayDateCal = Calendar.getInstance();

		String parsedMessageFromTemplate = "Empty Message!";
		String template;
		String sender;
		AbdContact contact;
		AbdAccount actAccount;

		//search all contacts, which have bday today
		Date currentDate = new Date(System.currentTimeMillis());
		currentDateCal.setTime(currentDate);
		LOGGER.log(Level.INFO, "Getting all Contacts");
		Collection<AbdContact> Contacts = contactDAO.findAll();

		List<AbdContact> birthdayContacts = new ArrayList<AbdContact>();
		LOGGER.log(Level.INFO, "Searching for birthday-contacts");
		for (AbdContact abdContact : Contacts) {
			System.out.println(abdContact.getFirstname() + " " + abdContact.getName() + " has bday on " + abdContact.getBday());

			bdayDateCal.setTime(abdContact.getBday());


			LOGGER.log(Level.INFO, "DAY: {0} CURRENT DAY: {1}",
					new Object[]{bdayDateCal.get(Calendar.DAY_OF_MONTH), currentDateCal.get(Calendar.DAY_OF_MONTH)});
			LOGGER.log(Level.INFO, "MONTH: {0} CURRENT MONTH: {1}",
					new Object[]{bdayDateCal.get(Calendar.MONTH), currentDateCal.get(Calendar.MONTH)});

			if ((bdayDateCal.get(Calendar.DAY_OF_MONTH) == currentDateCal.get(Calendar.DAY_OF_MONTH)) 
					&& (bdayDateCal.get(Calendar.MONTH) == currentDateCal.get(Calendar.MONTH))) {
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
					if (aktGroupToContact.getAbdContact().equals(aktContact)) {
						//and if group is marked as active
						if (aktGroupToContact.getActive()) {
							//and if contactToGroup is marked as active
							if (aktGroupToContact.getAbdGroup().getActive()) {

								actAccount = aktGroupToContact.getAbdGroup().getAccount();
								template = aktGroupToContact.getAbdGroup().getTemplate();
								contact = aktGroupToContact.getAbdContact();
								sender = getSender(aktGroupToContact);
								try {
									//parse Template
									parsedMessageFromTemplate = groupManager.parseTemplate(template, contact);
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

		} else {
			LOGGER.log(Level.INFO, "No Birthdaycontacts found");
		}

	}

	/**
	 * initializes the loggers for the whole project.
	 *
	 */
	private void initLoggers() {
		Level consoleHandlerLevel = Level.INFO;


		Logger rootLogger = Logger.getLogger("");

		Handler[] handlers = rootLogger.getHandlers();

		ConsoleHandler chandler = null;

		for (int i = 0; i < handlers.length; i++) {
			if (handlers[i] instanceof ConsoleHandler) {
				chandler = (ConsoleHandler) handlers[i];
			}
		}

		if (chandler != null) {
			chandler.setLevel(consoleHandlerLevel);
		} else {
			LOGGER.log(Level.SEVERE, "No ConsoleHandler there.");
		}
	}

	private String getSender(AbdGroupToContact aktGroupToContact) {
		return aktGroupToContact.getAbdGroup().getAccount().getUsername();
	}
}
