package de.fhb.autobday.beans;

import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.exception.user.UserNotFoundException;
import de.fhb.autobday.manager.user.UserManagerLocal;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@Named
@RequestScoped
public class UserBean implements Serializable{
	@Inject
	private UserManagerLocal userManager;
	@Inject
	private SessionBean sessionBean;
	
	private ListDataModel<AbdAccount> accountList;
	

	/**
	 * Creates a new instance of UserBean
	 */
	public UserBean() {
		accountList = new ListDataModel<AbdAccount>();
	}
	
	private void getAllAccountsFromUser(){
		try {
			accountList = new ListDataModel<AbdAccount>(userManager.getAllAccountsFromUser(sessionBean.getAktUser()));
			
		} catch (NullPointerException ex) {
			Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));
		} catch (UserNotFoundException ex) {
			Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));
		}
	}

	public ListDataModel<AbdAccount> getAccountList() {
		getAllAccountsFromUser();
		return accountList;
	}

	public void setAccountList(ListDataModel<AbdAccount> accountList) {
		this.accountList = accountList;
	}

	
}
