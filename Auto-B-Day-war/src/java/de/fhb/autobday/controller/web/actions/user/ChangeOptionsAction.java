package de.fhb.autobday.controller.web.actions.user;

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
@Named(value = "ChangeOptionsAction")
public class ChangeOptionsAction extends HttpRequestActionBase {
	
	@Inject
	private UserManagerLocal userManager;
	
	private final static Logger LOGGER = Logger.getLogger(ChangeOptionsAction.class.getName());
	
	@Override
	public void perform(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		
		System.out.println("Hello, iam the " + ChangeOptionsAction.class.getName());
		
		//TODO implement
		
		

	}

	
}

