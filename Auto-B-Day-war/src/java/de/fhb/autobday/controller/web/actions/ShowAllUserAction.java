package de.fhb.autobday.controller.web.actions;

import de.fhb.autobday.commons.web.HttpRequestActionBase;

import de.fhb.autobday.manager.ABDManager;
import de.fhb.autobday.manager.ABDManagerLocal;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/** 
 * 
 * @author Michael Koppen
 */
@Named("ShowAllUserAction")
@SessionScoped
public class ShowAllUserAction extends HttpRequestActionBase implements Serializable {
	@Inject
	private ABDManagerLocal abdManager;
	@Override
	public void perform(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException {
		System.out.println(abdManager.hallo());
		
		System.out.println("Hello, iam the ShowAllUserAction ;D");
	}
}
