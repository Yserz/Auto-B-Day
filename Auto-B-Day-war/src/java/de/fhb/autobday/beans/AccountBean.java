package de.fhb.autobday.beans;

import de.fhb.autobday.data.AbdGroup;
import de.fhb.autobday.exception.account.AccountException;
import de.fhb.autobday.exception.account.AccountNotFoundException;
import de.fhb.autobday.manager.account.AccountManagerLocal;
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
		System.out.println("aktAccount: "+sessionBean.getAktAccount());
		return "showaccount";
	}
	public String deleteAccount(){
		System.out.println("aktAccount: "+sessionBean.getAktAccount());
		try {
			accountManager.removeAccount(sessionBean.getAktAccount());
		} catch (AccountException ex) {
			Logger.getLogger(AccountBean.class.getName()).log(Level.SEVERE, null, ex);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));
		}
		return null;
	}
	private void getAllGroupsFromAccount(){
		try {
			groupList = new ListDataModel<AbdGroup>(accountManager.getAllGroupsFromAccount(sessionBean.getAktAccount()));
		} catch (AccountNotFoundException ex) {
			Logger.getLogger(AccountBean.class.getName()).log(Level.SEVERE, null, ex);
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
	
}
