package de.fhb.autobday.controller.web.actions.user;

import de.fhb.autobday.commons.web.HttpRequestActionBase;
import de.fhb.autobday.manager.AbdManagerLocal;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/** 
 * 
 * @author @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@Named(value = "ShowAllUserAction")
@RequestScoped
public class ShowAllUserAction extends HttpRequestActionBase {
	
	@Inject
	private AbdManagerLocal abdManager;
	
	@Override
	public void perform(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException {
		System.out.println(abdManager.hallo());
		
		System.out.println("Hello, iam the ShowAllUserAction ;D");
	}
}
