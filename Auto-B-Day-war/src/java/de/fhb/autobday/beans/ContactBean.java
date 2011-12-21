package de.fhb.autobday.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@Named(value = "contactBean")
@RequestScoped
public class ContactBean {

	/**
	 * Creates a new instance of ContactBean
	 */
	public ContactBean() {
	}
}
