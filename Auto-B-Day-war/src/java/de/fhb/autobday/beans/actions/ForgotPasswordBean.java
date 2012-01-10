/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.autobday.beans.actions;

import de.fhb.autobday.beans.SessionBean;
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
 *
 * @author MacYser
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

	public String sendForgotPasswordMail(){
		try {
			userManager.sendForgotPasswordMail(userName);
		} catch (MailException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));
		} catch (UserNotFoundException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mail was send. Please check your mails.", ""));
		return "index";
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
