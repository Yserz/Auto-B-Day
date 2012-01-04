package de.fhb.autobday.beans.actions;

import de.fhb.autobday.beans.SessionBean;
import de.fhb.autobday.data.AbdUser;
import de.fhb.autobday.exception.account.AccountAlreadyExsistsException;
import de.fhb.autobday.exception.user.UserNotFoundException;
import de.fhb.autobday.manager.account.AccountManagerLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@Named
@RequestScoped
public class ImportNewAccountBean {
	@Inject
	private AccountManagerLocal accountManager;
	@Inject
	private SessionBean sessionBean;
	
	private String password;
	private String userName;
	private String type;
	
	/**
	 * Creates a new instance of ImportNewAccountBean
	 */
	public ImportNewAccountBean() {
		type = "google";
		userName = "fhbtestacc@googlemail.com";
		password = "TestGoogle123";
	}
	public String importNewAccount(){
		try {
			AbdUser aktUser = sessionBean.getAktUser();
			accountManager.addAccount(aktUser.getId(), password, userName, type);
		} catch (UserNotFoundException ex) {
			Logger.getLogger(ImportNewAccountBean.class.getName()).log(Level.SEVERE, null, ex);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));
		}catch (AccountAlreadyExsistsException ex) {
			Logger.getLogger(ImportNewAccountBean.class.getName()).log(Level.SEVERE, null, ex);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));
		}
		return null;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}
	
}
