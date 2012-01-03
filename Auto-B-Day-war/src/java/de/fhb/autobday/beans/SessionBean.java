package de.fhb.autobday.beans;

import de.fhb.autobday.data.AbdAccount;
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
	
	

	/**
	 * Creates a new instance of SessionBean
	 */
	public SessionBean() {
	}

	public boolean isLoggedIn() {
		return aktUser != null;
	}

	
	public AbdUser getAktUser() {
		return aktUser;
	}

	public void setAktUser(AbdUser aktUser) {
		this.aktUser = aktUser;
	}

	public AbdAccount getAktAccount() {
		return aktAccount;
	}

	public void setAktAccount(AbdAccount aktAccount) {
		this.aktAccount = aktAccount;
	}

	public AbdGroup getAktGroup() {
		return aktGroup;
	}

	public void setAktGroup(AbdGroup aktGroup) {
		this.aktGroup = aktGroup;
	}
	
}
