package de.fhb.autobday.controller.web.actions.group;

import de.fhb.autobday.commons.web.HttpRequestActionBase;
import de.fhb.autobday.manager.group.GroupManagerLocal;
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
@Named(value = "ActivateGroupAction")
public class ActivateGroupAction extends HttpRequestActionBase {
	
	@Inject
	private GroupManagerLocal groupManager;
	
	private final static Logger LOGGER = Logger.getLogger(ActivateGroupAction.class.getName());
	
	@Override
	public void perform(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		
		System.out.println("Hello, iam the " + ActivateGroupAction.class.getName());
		
		//TODO implement
		
		

	}

	
}

