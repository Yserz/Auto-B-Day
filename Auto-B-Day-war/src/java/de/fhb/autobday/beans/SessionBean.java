package de.fhb.autobday.beans;

import de.fhb.autobday.data.AbdAccount;
import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.data.AbdGroup;
import de.fhb.autobday.data.AbdUser;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@Named
@SessionScoped
public class SessionBean implements Serializable {
	private AbdUser aktUser;
	private AbdAccount aktAccount;
	private AbdGroup aktGroup;
	private AbdContact aktContact;

	/**
	 * Creates a new instance of SessionBean
	 */
	public SessionBean() {
	}

	public boolean isLoggedIn() {
		return aktUser != null;
	}

	
	public AbdContact getAktContact() {
		return aktContact;
	}

	public void setAktContact(AbdContact aktContact) {
		System.out.println("Setting aktContact to: "+aktContact);
		this.aktContact = aktContact;
	}
	
	public AbdUser getAktUser() {
		return aktUser;
	}

	public void setAktUser(AbdUser aktUser) {
		System.out.println("Setting aktUser to: "+aktUser);
		this.aktUser = aktUser;
	}

	public AbdAccount getAktAccount() {
		return aktAccount;
	}

	public void setAktAccount(AbdAccount aktAccount) {
		System.out.println("Setting aktAccount to: "+aktAccount);
		this.aktAccount = aktAccount;
	}

	public AbdGroup getAktGroup() {
		return aktGroup;
	}

	public void setAktGroup(AbdGroup aktGroup) {
		System.out.println("Setting aktGroup to: "+aktGroup);
		this.aktGroup = aktGroup;
	}
	
	
}
