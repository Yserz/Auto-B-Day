package de.fhb.autobday.beans;

import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.exception.user.UserNotFoundException;
import de.fhb.autobday.manager.user.UserManagerLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Bean for user-tasks.
 *
 * @author Michael Koppen mail: koppen@fh-brandenburg.de
 */
@Named
@RequestScoped
public class UserBean {

	private final static Logger LOGGER = Logger.getLogger(UserBean.class.getName());
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
	/**
	 * will get all accounts from the given user.
	 */
	private void getAllAccountsFromUser() {
		try {
			accountList = new ListDataModel<AbdAccount>(userManager.getAllAccountsFromUser(sessionBean.getAktUser()));

		} catch (NullPointerException ex) {
			LOGGER.log(Level.SEVERE, null, ex.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));
		} catch (UserNotFoundException ex) {
			LOGGER.log(Level.SEVERE, null, ex.getMessage());
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
