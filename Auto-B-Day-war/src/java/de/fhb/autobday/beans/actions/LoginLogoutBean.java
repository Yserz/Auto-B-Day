/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.autobday.beans.actions;

import de.fhb.autobday.beans.SessionBean;
import de.fhb.autobday.data.AbdUser;
import de.fhb.autobday.exception.HashFailException;
import de.fhb.autobday.exception.user.UserException;
import de.fhb.autobday.manager.user.UserManagerLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@Named
@RequestScoped
public class LoginLogoutBean {

	@Inject
	private UserManagerLocal userManager;
	@Inject
	private SessionBean sessionBean;
	private String userName;
	private String password;

	/**
	 * Creates a new instance of LoginBean
	 */
	public LoginLogoutBean() {
		userName = "username";
		password = "password";
	}

	public String login() {
		AbdUser aktUser = null;
		try {
			aktUser = userManager.login(userName, password);
			password = "password";
			sessionBean.setAktUser(aktUser);
		} catch (UserException ex) {
			Logger.getLogger(SessionBean.class.getName()).log(Level.SEVERE, null, ex);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));
		} catch (HashFailException ex) {
			Logger.getLogger(SessionBean.class.getName()).log(Level.SEVERE, null, ex);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));
		}



		return "index";
	}

	public String logout() {
		sessionBean.setAktUser(null);

		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.invalidate();

		return "index";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}