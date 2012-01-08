package de.fhb.autobday.beans;

import de.fhb.autobday.data.AbdGroup;
import de.fhb.autobday.exception.account.AccountException;
import de.fhb.autobday.exception.account.AccountNotFoundException;
import de.fhb.autobday.manager.account.AccountManagerLocal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.TabChangeEvent;

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
	public void onTabChange(TabChangeEvent event){
		System.out.println("ID des Tabs: "+event.getTab().getId());
		//sessionBean.setAktGroup(groupList.get(event.getTab().getId().));
		
	}
}
