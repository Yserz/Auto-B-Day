package de.fhb.autobday.beans.actions;

import de.fhb.autobday.beans.SessionBean;
import de.fhb.autobday.manager.mail.GoogleMailManagerLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * ActionBean for mail-form.
 *
 * @author Michael Koppen mail: koppen@fh-brandenburg.de
 */
@Named
@RequestScoped
public class MailTestBean {
	private final static Logger LOGGER = Logger.getLogger(MailTestBean.class.getName());
	@Inject
	private SessionBean sessionBean;
	@Inject
	private GoogleMailManagerLocal mailManager;
	
	private String mailTo;
	/**
	 * Creates a new instance of MailTestBean
	 */
	public MailTestBean() {
	}
	public void testSystemMailManager(){
		try {
			mailManager.sendSystemMail("Betreff", "Message", mailTo);
			FacesContext.getCurrentInstance().addMessage(
				null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SystemMail was send!", ""));
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, null, ex.getMessage());
			FacesContext.getCurrentInstance().addMessage(
					null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));
		}
		
	}
	public void testUserMailManager(){
		try {
			mailManager.sendUserMail(sessionBean.getAktAccount(), "Betreff", "Message", mailTo);
			FacesContext.getCurrentInstance().addMessage(
					null, new FacesMessage(FacesMessage.SEVERITY_INFO, "UserMail was send!", ""));
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, null, ex.getMessage());
			FacesContext.getCurrentInstance().addMessage(
					null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));
		}
		
	}

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}
	
}
