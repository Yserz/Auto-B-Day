package de.fhb.autobday.controller.web.actions.user;

import de.fhb.autobday.commons.web.HttpRequestActionBase;
import de.fhb.autobday.manager.user.UserManagerLocal;
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
@Named(value = "LoginAction")
public class LoginAction extends HttpRequestActionBase {
	
	@Inject
	private UserManagerLocal userManager;
	
	private final static Logger LOGGER = Logger.getLogger(LoginAction.class.getName());
	
	@Override
	public void perform(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		
		System.out.println("Hello, iam the " + LoginAction.class.getName());
		
		//TODO implement
		
		

	}

	
}

