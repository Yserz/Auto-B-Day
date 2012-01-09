/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.autobday.beans.actions;

import de.fhb.autobday.manager.mail.MailManagerLocal;
import javax.enterprise.context.RequestScoped;
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
	private MailManagerLocal mailManager;
	
	private String mailTo;
	/**
	 * Creates a new instance of MailTestBean
	 */
	public MailTestBean() {
	}
	public void testMailManager(){
		mailManager.sendBdayMail("someone@bla.de", mailTo, "Betreff", "Body");
	}

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}
	
}
