package de.fhb.autobday.controller.web.actions.contact;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.fhb.autobday.commons.web.HttpRequestActionBase;
import de.fhb.autobday.manager.contact.ContactManager;

/** 
 * 
 * Action for HttpRequest
 * 
 * @author Andy Klay <klay@fh-brandenburg.de>
 * 
 */
public class ActivateContactAction extends HttpRequestActionBase {
	
	@Inject
	private ContactManager contactManager;
	
	private final static Logger LOGGER = Logger.getLogger(ActivateContactAction.class.getName());
	
	@Override
	public void perform(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		
		System.out.println("Hello, iam the " + ActivateContactAction.class.getName());
		
		//TODO implement
		
		

	}

	
}

