package de.fhb.autobday.beans;

import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.data.AbdGroup;
import de.fhb.autobday.data.AbdGroupToContact;
import de.fhb.autobday.exception.contact.ContactException;
import de.fhb.autobday.manager.contact.ContactManagerLocal;
import de.fhb.autobday.manager.group.GroupManagerLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@Named
@RequestScoped
public class GroupBean {
	private final static Logger LOGGER = Logger.getLogger(GroupBean.class.getName());

	@Inject
	private GroupManagerLocal groupManager;
	@Inject
	private ContactManagerLocal contactManager;
	@Inject
	private SessionBean sessionBean;
	
	private ListDataModel<AbdContact> contactList;
	private boolean activeState = false;
	/**
	 * Creates a new instance of GroupBean
	 */
	public GroupBean() {
		
	}
	
	public String showGroup(){
		return "showgroup";
	}
	public String showTemplate(){
		return "edittemplate";
	}
	public String editTemplate(){
		return "edittemplate";
	}
	private void getAllContactsFromGroup(){
		try {
			contactList = new ListDataModel(groupManager.getAllContactsFromGroup(sessionBean.getAktGroup()));
			
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, null, ex);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));
		}
	}
	
	public ListDataModel<AbdContact> getContactList() {
		getAllContactsFromGroup();
		return contactList;
	}

	public void setContactList(ListDataModel<AbdContact> contactList) {
		this.contactList = contactList;
	}
	public void toggleContactActivation() {
		System.out.println("toggleContactActivation");
		AbdGroup aktGroup = sessionBean.getAktGroup();
		AbdContact aktContact = contactList.getRowData();
  
		for (AbdGroupToContact gtc : aktContact.getAbdGroupToContactCollection()) {
			if (gtc.getAbdGroup().equals(aktGroup)) {
				try {
					if (gtc.getActive()) {
						System.out.println("was aktive");
						contactManager.setActive(aktContact, aktGroup, false);
					}else{
						System.out.println("was inaktive");
						contactManager.setActive(aktContact, aktGroup, true);
					}
				} catch (ContactException ex) {
					LOGGER.log(Level.SEVERE, null, ex);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));
				}
			}
		}
          
    }
	private boolean changeAktiveState(){
		AbdGroup aktGroup = sessionBean.getAktGroup();
		AbdContact aktContact = contactList.getRowData();
		boolean active = false;
		
		
		for (AbdGroupToContact gtc : aktContact.getAbdGroupToContactCollection()) {
			if (gtc.getAbdGroup().equals(aktGroup)) {
				active = gtc.getActive();
			}
		}
		
		return active;
	}

	public boolean isActiveState() {
		activeState = changeAktiveState();
		System.out.println("is Aktive?: "+activeState);
		return activeState;
	}

	public void setActiveState(boolean activeState) {
		this.activeState = activeState;
	}
	
}
