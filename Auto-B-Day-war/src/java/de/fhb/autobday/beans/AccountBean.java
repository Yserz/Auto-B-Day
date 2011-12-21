package de.fhb.autobday.beans;

import de.fhb.autobday.manager.account.AccountManagerLocal;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@Named(value = "accountBean")
@RequestScoped
public class AccountBean {
	@Inject
	private AccountManagerLocal accountManager;
	@Inject
	private SessionBean sessionBean;
	/**
	 * Creates a new instance of AccountBean
	 */
	public AccountBean() {
	}
	
	public void getAllAccounts(){
		sessionBean.getUser();
		
	}
}
