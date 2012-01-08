package de.fhb.autobday.beans;

import de.fhb.autobday.data.AbdContact;
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
	private SessionBean sessionBean;
	
	private ListDataModel<AbdContact> contactList;
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
	
}
