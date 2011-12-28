package de.fhb.autobday.beans;

import de.fhb.autobday.beans.utils.ErrorBean;
import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.data.AbdGroup;
import de.fhb.autobday.data.AbdUser;
import de.fhb.autobday.exception.user.UserException;
import de.fhb.autobday.manager.user.UserManagerLocal;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@Named(value = "sessionBean")
@SessionScoped
public class SessionBean implements Serializable {
	@Inject
	private UserManagerLocal userManager;
	private AbdUser aktUser;
	private AbdAccount aktAccount;
	private AbdGroup aktGroup;
	private String contextPath;
	
	private String loginName;
	private String password;
	
	private ErrorBean errorBean;

	/**
	 * Creates a new instance of SessionBean
	 */
	public SessionBean() {
		errorBean = new ErrorBean();
		aktUser = null;
		loginName = "";
		password = "";

	}

	public String login() {
		System.out.println("loginName: "+loginName);
		System.out.println("Password: "+password);
		try {
			aktUser = userManager.login(loginName, password);
			
			loginName = null;
			password = null;
		} catch (UserException ex) {
			Logger.getLogger(SessionBean.class.getName()).log(Level.SEVERE, null, ex);
			errorBean.handleException(ex);
		}
		return "/success.xhtml";
	}

	public String logout() {
		aktUser = null;
		return "/index.xhtml";
	}

	public boolean isLoggedIn() {
		return aktUser != null;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public AbdUser getUser() {
		return aktUser;
	}

	public void setUser(AbdUser user) {
		this.aktUser = user;
	}
	//TODO Delete
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	//TODO Delete
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
