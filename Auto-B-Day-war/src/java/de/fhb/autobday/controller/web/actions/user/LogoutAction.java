package de.fhb.autobday.controller.web.actions.user;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.fhb.autobday.commons.web.HttpRequestActionBase;
import de.fhb.autobday.manager.user.UserManager;

/** 
 * 
 * Action for HttpRequest
 * 
 * @author Andy Klay <klay@fh-brandenburg.de>
 * 
 */
public class LogoutAction extends HttpRequestActionBase {
	
	@Inject
	private UserManager userManager;
	
	private final static Logger LOGGER = Logger.getLogger(LogoutAction.class.getName());
	
	@Override
	public void perform(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		
		System.out.println("Hello, iam the " + LogoutAction.class.getName());
		
		//TODO implement
		
		

	}

	
}
