package de.fhb.autobday.beans;

import de.fhb.autobday.data.AbdGroup;
import de.fhb.autobday.exception.account.AccountException;
import de.fhb.autobday.exception.account.AccountNotFoundException;
import de.fhb.autobday.exception.group.GroupNotFoundException;
import de.fhb.autobday.manager.account.AccountManagerLocal;
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
public class AccountBean {
	private final static Logger LOGGER = Logger.getLogger(AccountBean.class.getName());
	
	@Inject
	private AccountManagerLocal accountManager;
	@Inject
	private GroupManagerLocal groupManager;
	@Inject
	private SessionBean sessionBean;
	
	private ListDataModel<AbdGroup> groupList;
	/**
	 * Creates a new instance of AccountBean
	 */
	public AccountBean() {
		groupList = new ListDataModel<AbdGroup>();
	}
	
	public String showAccount(){
		return "showaccount";
	}
	public String deleteAccount(){
		try {
			accountManager.removeAccount(sessionBean.getAktAccount());
			sessionBean.setAktAccount(null);
		} catch (AccountException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully deleted account!", ""));
		return "index";
	}
	private void getAllGroupsFromAccount(){
		try {
			groupList = new ListDataModel<AbdGroup>(accountManager.getAllGroupsFromAccount(sessionBean.getAktAccount()));
			
		} catch (AccountNotFoundException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));
		}
	}

	public ListDataModel<AbdGroup> getGroupList() {
		getAllGroupsFromAccount();
		return groupList;
	}

	public void setGroupList(ListDataModel<AbdGroup> groupList) {
		this.groupList = groupList;
	}
	public void toggleGroupActivation() {
		AbdGroup aktGroup = groupList.getRowData();
		String infoString = "";
		boolean toggle = false;
  
		if (aktGroup.getActive()) {
			infoString = "deactivated";
			toggle=false;
		}else{
			infoString = "activated";
			toggle=true;
		}
		try {
			groupManager.setActive(aktGroup, toggle);
		} catch (GroupNotFoundException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));
		}
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "You successfully "+infoString+" this Group!", ""));
    }  
	
}
