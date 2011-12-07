package de.fhb.autobday.controller.web;

import de.fhb.autobday.commons.web.HttpServletControllerBase;
import de.fhb.autobday.controller.web.actions.contact.ActivateContactAction;
import de.fhb.autobday.controller.web.actions.contact.DeactivateContactAction;
import de.fhb.autobday.controller.web.actions.contact.ShowContactDatailsAction;
import de.fhb.autobday.controller.web.actions.google.ReadOutGoogleContactsAction;
import de.fhb.autobday.controller.web.actions.group.*;
import de.fhb.autobday.controller.web.actions.user.*;
import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@WebServlet(name = "ABDServlet",
			urlPatterns = {
				"/ABDServlet"
			})
@Named("ABDServlet")
public class ABDServlet extends HttpServletControllerBase {


	
	@Inject private ActivateContactAction activateContactAction;
	@Inject private DeactivateContactAction deactivateContactAction;
	@Inject private ShowContactDatailsAction showContactDatailsAction;
	@Inject private ReadOutGoogleContactsAction readOutGoogleContactsAction;
	@Inject private ActivateGroupAction activateGroupAction;
	@Inject private DeactivateGroupAction deactivateGroupAction;
	@Inject private SaveTemplateAction saveTemplateAction;
	@Inject private ShowAllGroups showAllGroups;
	@Inject private ShowContactsOfGroupAction showContactsOfGroupAction;
	@Inject private ShowTemplateAction showTemplateAction;
	@Inject private ChangeOptionsAction changeOptionsAction;
	@Inject private LoginAction loginAction;
	@Inject private LogoutAction logoutAction;
	@Inject private ShowAllUserAction showAllUserAction;
	@Inject private ShowOptionsAction showOptionsAction;
	
	/** 
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Hello, iam the ABDServlet");
	}
	

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	
	/** 
	 * Handles the HTTP <code>GET</code> method.
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		super.doGet(req, resp);
		processRequest(req, resp);
	}

	/** 
	 * Handles the HTTP <code>POST</code> method.
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
		processRequest(req, resp);
	}

	/** 
	 * Returns a short description of the servlet.
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

	@Override
	protected String getOperation(HttpServletRequest req) {
		return req.getParameter("do");
	}

	@Override
	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
		

		actions.put("ActivateContactAction",activateContactAction);
		actions.put("DeactivateContactAction",deactivateContactAction);
		actions.put("ShowContactDatailsAction",showContactDatailsAction);
		actions.put("ReadOutGoogleContactsAction",readOutGoogleContactsAction);
		actions.put("ActivateGroupAction",activateGroupAction);
		actions.put("DeactivateGroupAction",deactivateGroupAction);
		actions.put("SaveTemplateAction",saveTemplateAction);
		actions.put("ShowAllGroups",showAllGroups);
		actions.put("ShowContactsOfGroupAction",showContactsOfGroupAction);
		actions.put("ShowTemplateAction",showTemplateAction);
		actions.put("ChangeOptionsAction",changeOptionsAction);
		actions.put("LoginAction",loginAction);
		
	}
	
}
