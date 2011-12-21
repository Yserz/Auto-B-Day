package de.fhb.autobday.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@Named(value = "userBean")
@RequestScoped
public class UserBean {

	/**
	 * Creates a new instance of UserBean
	 */
	public UserBean() {
	}
}
