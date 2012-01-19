package de.fhb.autobday.beans.actions;

import de.fhb.autobday.beans.AccountBean;
import de.fhb.autobday.beans.SessionBean;
import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.data.AbdUser;
import de.fhb.autobday.exception.account.AccountAlreadyExsistsException;
import de.fhb.autobday.exception.account.AccountNotFoundException;
import de.fhb.autobday.exception.connector.ConnectorException;
import de.fhb.autobday.exception.user.NoValidUserNameException;
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
 * ActionBean for importing-new-account-form.
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@Named
@RequestScoped
public class ImportNewAccountBean {

	private final static Logger LOGGER = Logger.getLogger(ImportNewAccountBean.class.getName());
	@Inject
	private AccountManagerLocal accountManager;
	@Inject
	private SessionBean sessionBean;
	@Inject
	private AccountBean accountBean;
	private String password;
	private String userName;
	private String type;

	/**
	 * Creates a new instance of ImportNewAccountBean
	 */
	public ImportNewAccountBean() {
		type = "google";
	}

	public String importNewAccount() {
		try {
			AbdUser aktUser = sessionBean.getAktUser();
			AbdAccount aktAccount = accountManager.addAccount(aktUser.getId(), password, userName, type);
			sessionBean.setAktAccount(aktAccount);
			try {
				accountManager.importGroupsAndContacts(sessionBean.getAktAccount().getId());
				FacesContext.getCurrentInstance().addMessage(
						null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Account is imported!", ""));
			} catch (AccountNotFoundException ex) {
				LOGGER.log(Level.SEVERE, null, ex);
				accountBean.deleteAccount();
				FacesContext.getCurrentInstance().addMessage(
						null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));
			} catch (ConnectorException ex) {
				LOGGER.log(Level.SEVERE, null, ex);
				accountBean.deleteAccount();
				FacesContext.getCurrentInstance().addMessage(
						null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));

			}
		} catch (NoValidUserNameException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
			FacesContext.getCurrentInstance().addMessage(
					null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));
		} catch (UserNotFoundException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
			FacesContext.getCurrentInstance().addMessage(
					null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));
		} catch (AccountAlreadyExsistsException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
			FacesContext.getCurrentInstance().addMessage(
					null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));
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
