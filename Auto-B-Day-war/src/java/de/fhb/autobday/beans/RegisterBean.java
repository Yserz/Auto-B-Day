package de.fhb.autobday.beans;

import de.fhb.autobday.beans.utils.ErrorBean;
import de.fhb.autobday.exception.user.IncompleteUserRegisterException;
import de.fhb.autobday.exception.user.NoValidUserNameException;
import de.fhb.autobday.manager.user.UserManagerLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@Named(value = "registerBean")
@RequestScoped
public class RegisterBean {
	@Inject
	private UserManagerLocal userManager;
	
	private String firstName;
	private String name;
	private String userName;
	private String password;
	
	private ErrorBean errorBean;

	/**
	 * Creates a new instance of RegisterBean
	 */
	public RegisterBean() {
		errorBean = new ErrorBean();
	}
	
	public String register(){
		String returnStat = "index";
		
		try {
			//TODO passwort mit  passwortwiederholung als letzten paramter ersetzen
			userManager.register(firstName, name, userName, password, password);
		} catch (IncompleteUserRegisterException ex) {
			Logger.getLogger(RegisterBean.class.getName()).log(Level.SEVERE, null, ex);
			returnStat = errorBean.handleException(ex);
			
		} catch (NoValidUserNameException ex) {
			Logger.getLogger(RegisterBean.class.getName()).log(Level.SEVERE, null, ex);
			returnStat =  errorBean.handleException(ex);
		}
		return returnStat;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
