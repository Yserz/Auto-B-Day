package de.fhb.autobday.controller.web.actions.user;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.fhb.autobday.commons.HashHelper;
import de.fhb.autobday.commons.web.HttpRequestActionBase;
import de.fhb.autobday.data.AbdUser;
import de.fhb.autobday.manager.user.UserManagerLocal;

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
		
		HttpSession session = req.getSession();
		AbdUser user = null;
		
		try {
			
			LOGGER.log(Level.INFO,"parameter:");
			LOGGER.log(Level.INFO, "loginName: {0}", req.getParameter("loginName"));
			LOGGER.log(Level.INFO, "password: {1}", req.getParameter("password"));
			
			String loginName = req.getParameter("loginName").toLowerCase();
			String password = req.getParameter("password");
			
			try {
				
				password = HashHelper.calcSHA1(password);
				System.out.println("Password: "+password);
				
			} catch (NoSuchAlgorithmException ex) {
				
				LOGGER.log(Level.SEVERE,"Konnte Algorithmus zum hashen nicht finden.", ex);
				throw new Exception("Konnte Algorithmus zum hashen nicht finden.");
				
			} catch (UnsupportedEncodingException ex) {
				
				LOGGER.log(Level.SEVERE,"Konnte Password nicht encodieren.", ex);
				throw new Exception("Konnte Password nicht encodieren.");
				
			} 
			//manager call
			userManager.login(loginName, password);
			
			//pack into Session
			synchronized(session){

				session.setAttribute("aktUser", user.getId());
			}
			
			LOGGER.info("Erfolgreich eingeloggt!");
			req.setAttribute("triedLogin", false);
			
		}catch (Exception e) {
			
			LOGGER.log(Level.SEVERE,e.getMessage(), e);
			req.setAttribute("triedLogin", true);
			req.setAttribute("contentFile", "error.jsp");
			req.setAttribute("errorString", e.getMessage());
			
		}
		

	}

	
}