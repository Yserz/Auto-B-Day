package de.fhb.autobday.beans.actions;

import de.fhb.autobday.beans.SessionBean;
import de.fhb.autobday.exception.commons.HashFailException;
import de.fhb.autobday.exception.user.PasswordInvalidException;
import de.fhb.autobday.exception.user.UserNotFoundException;
import de.fhb.autobday.manager.user.UserManagerLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * ActionBean for changing the password-form.
 *
 * @author Michael Koppen mail: koppen@fh-brandenburg.de
 */
@Named
@RequestScoped
public class ChangePasswordBean {

	private final static Logger LOGGER = Logger.getLogger(ChangePasswordBean.class.getName());
	@Inject
	private UserManagerLocal userManager;
	@Inject
	private SessionBean sessionBean;
	private String oldPW;
	private String newPW;
	private String newPWRep;

	/**
	 * Creates a new instance of ChangePasswordBean
	 */
	public ChangePasswordBean() {
	}

	public String changePassword() {
		try {
			userManager.changePassword(sessionBean.getAktUser(), oldPW, newPW, newPWRep);
			FacesContext.getCurrentInstance().addMessage(
					null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully changed password!", ""));
		} catch (UserNotFoundException ex) {
			LOGGER.log(Level.SEVERE, null, ex.getMessage());
			FacesContext.getCurrentInstance().addMessage(
					null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));
		} catch (PasswordInvalidException ex) {
			LOGGER.log(Level.SEVERE, null, ex.getMessage());
			FacesContext.getCurrentInstance().addMessage(
					null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));
		} catch (HashFailException ex) {
			LOGGER.log(Level.SEVERE, null, ex.getMessage());
			FacesContext.getCurrentInstance().addMessage(
					null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));
		}
		return "index";
	}

	public String getNewPW() {
		return newPW;
	}

	public void setNewPW(String newPW) {
		this.newPW = newPW;
	}

	public String getNewPWRep() {
		return newPWRep;
	}

	public void setNewPWRep(String newPWRep) {
		this.newPWRep = newPWRep;
	}

	public String getOldPW() {
		return oldPW;
	}

	public void setOldPW(String oldPW) {
		this.oldPW = oldPW;
	}
}
