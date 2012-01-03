package de.fhb.autobday.beans;

import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.exception.user.UserNotFoundException;
import de.fhb.autobday.manager.user.UserManagerLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@Named
@RequestScoped
public class UserBean {
	@Inject
	private UserManagerLocal userManager;
	@Inject
	private SessionBean sessionBean;

	/**
	 * Creates a new instance of UserBean
	 */
	public UserBean() {
	}
	
	public DataModel<AbdAccount> getAllAccountsFromUser(){
		ListDataModel<AbdAccount> list = new ListDataModel<AbdAccount>();
		try {
			list = new ListDataModel<AbdAccount>(userManager.getAllAccountsFromUser(sessionBean.getAktUser()));
		} catch (NullPointerException ex) {
			Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));
		} catch (UserNotFoundException ex) {
			Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));
		}
		return list;
	}
	
}
