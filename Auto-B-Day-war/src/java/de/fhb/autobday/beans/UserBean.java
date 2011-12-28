package de.fhb.autobday.beans;

import de.fhb.autobday.manager.user.UserManagerLocal;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@Named(value = "userBean")
@RequestScoped
public class UserBean {
	@Inject
	private UserManagerLocal userManager;

	/**
	 * Creates a new instance of UserBean
	 */
	public UserBean() {
	}
	
	public void getAllAccountsFromUser(){
		
	}
}
