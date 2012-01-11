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
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@Named
@RequestScoped
public class UpdateAccountBean {

	private final static Logger LOGGER = Logger.getLogger(ImportNewAccountBean.class.getName());
	@Inject
	private AccountManagerLocal accountManager;
	@Inject
	private SessionBean sessionBean;
	

	/**
	 * Creates a new instance of ImportNewAccountBean
	 */
	public UpdateAccountBean() {
	}

	public String updateAccount() {
		try {
			accountManager.importGroupsAndContacts(sessionBean.getAktAccount().getId());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Account is imported!", ""));
		} catch (AccountNotFoundException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));
		} catch (ConnectorException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));

		}
		
		return null;
	}
}
