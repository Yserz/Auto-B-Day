package de.fhb.autobday.beans.actions;

import de.fhb.autobday.beans.SessionBean;
import de.fhb.autobday.exception.commons.HashFailException;
import de.fhb.autobday.exception.mail.MailException;
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
 * ActionBean for sending a new password-form.
 *
 * @author Michael Koppen mail: koppen@fh-brandenburg.de
 */
@Named
@RequestScoped
public class ForgotPasswordBean {

	private final static Logger LOGGER = Logger.getLogger(ForgotPasswordBean.class.getName());
	@Inject
	private UserManagerLocal userManager;
	@Inject
	private SessionBean sessionBean;
	private String userName;

	/**
	 * Creates a new instance of ForgotPasswordBean
	 */
	public ForgotPasswordBean() {
	}
	/**
	 * will send a System-Mail with a new Password.
	 * @return redirect to index
	 */
	public String sendForgotPasswordMail() {
		try {
			userManager.sendForgotPasswordMail(userName);
			FacesContext.getCurrentInstance().addMessage(
					null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mail was send. Please check your mails.", ""));

		} catch (MailException ex) {
			LOGGER.log(Level.SEVERE, null, ex.getMessage());
			FacesContext.getCurrentInstance().addMessage(
					null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));
		} catch (UserNotFoundException ex) {
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
