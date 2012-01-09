package de.fhb.autobday.beans;

import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.data.AbdGroup;
import de.fhb.autobday.data.AbdGroupToContact;
import de.fhb.autobday.exception.contact.ContactException;
import de.fhb.autobday.manager.contact.ContactManagerLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@Named
@RequestScoped
public class ContactBean {
	private final static Logger LOGGER = Logger.getLogger(ContactBean.class.getName());
	@Inject
	private SessionBean sessionBean;
	@Inject
	private ContactManagerLocal contactManager;
	
	
	/**
	 * Creates a new instance of ContactBean
	 */
	public ContactBean() {
	}
	public void toggleContactActivation(AjaxBehaviorEvent e) {
		AbdGroup aktGroup = sessionBean.getAktGroup();
		AbdContact aktContact = (AbdContact) e.getComponent().getAttributes().get("contact");
  
		for (AbdGroupToContact gtc : aktGroup.getAbdGroupToContactCollection()) {
			if (gtc.getAbdContact().equals(aktContact)) {
				try {
					if (gtc.getActive()) {
						contactManager.setActive(aktContact, aktGroup, false);
					}else{
						contactManager.setActive(aktContact, aktGroup, true);
					}
				} catch (ContactException ex) {
					LOGGER.log(Level.SEVERE, null, ex);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));
				}
			}
		}
          
    }

	
}
