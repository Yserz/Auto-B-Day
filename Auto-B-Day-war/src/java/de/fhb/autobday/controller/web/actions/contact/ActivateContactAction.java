package de.fhb.autobday.controller.web.actions.contact;

import de.fhb.autobday.commons.web.HttpRequestActionBase;
import de.fhb.autobday.manager.contact.ContactManagerLocal;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
 * 
 * Action for HttpRequest
 * 
 * @author Andy Klay <klay@fh-brandenburg.de>
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 * 
 */
@Named(value = "ActivateContactAction")
public class ActivateContactAction extends HttpRequestActionBase {
	
	@Inject
	private ContactManagerLocal contactManager;
	
	private final static Logger LOGGER = Logger.getLogger(ActivateContactAction.class.getName());
	
	@Override
	public void perform(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		
		System.out.println("Hello, iam the " + ActivateContactAction.class.getName());
		
		//TODO implement
		
		

	}

	
}

