package de.fhb.autobday.commons.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Aktion die durch Ableitung definiert werden muss.
 * 
 * Die Aktion fuehrt Operationen mit dem Modell aus, und 
 * bereitet die Daten fuer die Ausgabe auf. Als letzte Aktion sollte eine 
 * Aktion zu einer View den Request weiterleiten, wo dann das Ergebnis
 * der Aktion eingelesen und in eine (HTML-)Seite eingebunden wird.
 * 
 * @author berdux
 * @version 1.0
 */
public abstract class HttpRequestActionBase {

	/**
	 * Standard-Methode, die durch Servlet aufgerufen wird.
	 * 
	 * @param req aktueller Request der bearbeitet werden soll
	 * @param resp 
	 * @throws ServletException 
	 */
	public abstract void perform(HttpServletRequest req, HttpServletResponse resp) throws ServletException;
	
	/**
	 * Kapselt das Weiterleiten zu einer weiteren Action oder View. Die Action/der View
	 * wird als Name angeben.
	 * 
	 * @param req aktueller Request der bearbeitet werden soll und in dem Ergebnisse der Action abgelegt sind
	 * @param resp  Response-Objekt zum Schreiben des Ergebnisses
	 * @param contentFile Name von Seite/JSP/Servlet, die angezeigt wird
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void forward(HttpServletRequest req, HttpServletResponse resp, String contentFile) throws ServletException, IOException {
		req.setAttribute("contentFile", contentFile);
		RequestDispatcher reqDis = req.getRequestDispatcher("index.jsp");
		reqDis.forward(req, resp);
		
	}
	/**
	 * Kapselt das Redirect zu einer weiteren Action. Die Action
	 * wird als Name angeben.
	 * 
	 * @param req aktueller Request der bearbeitet werden soll und in dem Ergebnisse der Action abgelegt sind
	 * @param resp  Response-Objekt zum Schreiben des Ergebnisses
	 * @param aktServlet Name von Servlet, die aktuell angesprochen wurde
	 * @param action Name von Action, die angezeigt wird
	 * @param parameter parameter, die mitgegeben werden sollen
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void redirect(HttpServletRequest req, HttpServletResponse resp, String aktServlet, String action, String[] parameter) throws ServletException, IOException {
		String redirectTo = "";
		String params = "";
		
		if (action == null) {
			redirectTo = aktServlet;
			System.out.println("redirectTo: "+redirectTo);
		}else{
			if (parameter != null) {
				for (String param : parameter) {
					params += "&"+param;
				}
			}
			redirectTo = aktServlet+"?do="+action+""+params;
			System.out.println("redirectTo: "+redirectTo);
		}
		
		resp.sendRedirect(redirectTo);
	}
}
