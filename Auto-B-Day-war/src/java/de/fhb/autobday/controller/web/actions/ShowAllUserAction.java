package de.fhb.autobday.controller.web.actions;

import de.fhb.autobday.commons.web.HttpRequestActionBase;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/** 
 * 
 * @author Michael Koppen
 */
public class ShowAllUserAction extends HttpRequestActionBase {

	@Override
	public void perform(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException {

		System.out.println("Hello, iam the ShowAllUserAction ;D");
	}
}
