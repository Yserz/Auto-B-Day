package de.fhb.autobday.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@Named(value = "groupBean")
@RequestScoped
public class GroupBean {

	/**
	 * Creates a new instance of GroupBean
	 */
	public GroupBean() {
	}
}
