package de.fhb.autobday.controller.web.actions.group;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.fhb.autobday.commons.web.HttpRequestActionBase;
import de.fhb.autobday.manager.group.GroupManager;

/** 
 * 
 * Action for HttpRequest
 * 
 * @author Andy Klay <klay@fh-brandenburg.de>
 * 
 */
@Named(value = "ShowContactsOfGroupAction")
public class ShowContactsOfGroupAction extends HttpRequestActionBase {
	
	@Inject
	private GroupManager groupManager;
	
	private final static Logger LOGGER = Logger.getLogger(ShowContactsOfGroupAction.class.getName());
	
	@Override
	public void perform(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		
		System.out.println("Hello, iam the " + ShowContactsOfGroupAction.class.getName());
		
		//TODO implement
		
		

	}

	
}

