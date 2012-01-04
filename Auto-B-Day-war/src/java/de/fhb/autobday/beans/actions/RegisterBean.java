package de.fhb.autobday.beans.actions;

import de.fhb.autobday.beans.SessionBean;
import de.fhb.autobday.exception.HashFailException;
import de.fhb.autobday.exception.user.IncompleteUserRegisterException;
import de.fhb.autobday.exception.user.NoValidUserNameException;
import de.fhb.autobday.manager.user.UserManagerLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@Named
@RequestScoped
public class RegisterBean {
	@Inject
	private UserManagerLocal userManager;
	
	private String firstName;
	private String name;
	private String userName;
	private String mail;
	private String password;
	private String passwordWdhl;
	
	

	/**
	 * Creates a new instance of RegisterBean
	 */
	public RegisterBean() {
	}
	
	public String register(){
		
		try {
			userManager.register(firstName, name, userName, mail, password, passwordWdhl);
		} catch (IncompleteUserRegisterException ex) {
			Logger.getLogger(RegisterBean.class.getName()).log(Level.SEVERE, null, ex);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));
		} catch (NoValidUserNameException ex) {
			Logger.getLogger(RegisterBean.class.getName()).log(Level.SEVERE, null, ex);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));
		}catch (HashFailException ex) {
			Logger.getLogger(SessionBean.class.getName()).log(Level.SEVERE, null, ex);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));
		}
		
		return "index";
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPasswordWdhl() {
		return passwordWdhl;
	}

	public void setPasswordWdhl(String passwordWdhl) {
		this.passwordWdhl = passwordWdhl;
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
