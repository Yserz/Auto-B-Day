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
@Named(value = "ShowOptionsAction")
public class ShowOptionsAction extends HttpRequestActionBase {
	
	@Inject
	private UserManagerLocal userManager;
	
	private final static Logger LOGGER = Logger.getLogger(ShowOptionsAction.class.getName());
	
	@Override
	public void perform(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		
		System.out.println("Hello, iam the " + ShowOptionsAction.class.getName());
		
		//TODO implement
		
		

	}

	
}
