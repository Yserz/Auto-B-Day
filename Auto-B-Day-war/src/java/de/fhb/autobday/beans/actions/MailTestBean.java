/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.autobday.beans.actions;

import de.fhb.autobday.beans.SessionBean;
import de.fhb.autobday.manager.mail.GoogleMailManager;
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
@Named(value = "mailTestBean")
@RequestScoped
public class MailTestBean {
	@Inject
	private SessionBean sessionBean;
	@Inject
	private GoogleMailManager mailManager;
	
	private String mailTo;
	/**
	 * Creates a new instance of MailTestBean
	 */
	public MailTestBean() {
	}
	public void testSystemMailManager(){
		try {
			mailManager.sendSystemMail("Betreff", "Message", mailTo);
		} catch (Exception ex) {
			Logger.getLogger(MailTestBean.class.getName()).log(Level.SEVERE, null, ex);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SystemMail was send!", ""));
	}
	public void testUserMailManager(){
		try {
			mailManager.sendUserMail(sessionBean.getAktAccount(), "Betreff", "Message", mailTo);
		} catch (Exception ex) {
			Logger.getLogger(MailTestBean.class.getName()).log(Level.SEVERE, null, ex);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "UserMail was send!", ""));
	}

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}
	
}
