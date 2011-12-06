package de.fhb.autobday.controller.web.actions.group;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.fhb.autobday.commons.web.HttpRequestActionBase;
import de.fhb.autobday.manager.user.UserManager;
import de.fhb.autobday.manager.user.UserManagerLocal;

/** 
 * 
 * Action for HttpRequest
 * 
 * @author Andy Klay <klay@fh-brandenburg.de>
 * 
 */
@Named(value = "ShowAllGroups")
public class ShowAllGroups extends HttpRequestActionBase {
	
	@Inject
	private UserManagerLocal userManager;
	
	private final static Logger LOGGER = Logger.getLogger(ShowAllGroups.class.getName());
	
	@Override
	public void perform(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		
		System.out.println("Hello, iam the " + ShowAllGroups.class.getName());
		
		//TODO implement
		
		

	}

	
}

