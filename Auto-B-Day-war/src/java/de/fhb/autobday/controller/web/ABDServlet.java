package de.fhb.autobday.controller.web;

import de.fhb.autobday.commons.web.HttpRequestActionBase;
import de.fhb.autobday.commons.web.HttpServletControllerBase;
import de.fhb.autobday.controller.web.actions.user.ShowAllUserAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Michael Koppen
 */
@WebServlet(name = "ABDServlet",
			urlPatterns = {
				"/ABDServlet"
			})
@Named("ABDServlet")
public class ABDServlet extends HttpServletControllerBase {
	@Inject
	private ShowAllUserAction showAllUserAction;
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
		
		HttpRequestActionBase action = null;
		//actions = new HashMap();
		
		actions.put("ShowAllUser", showAllUserAction);
	}
	
}
