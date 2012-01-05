package de.fhb.autobday.beans;

import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@Named
@RequestScoped
public class GroupBean {
	private final static Logger LOGGER = Logger.getLogger(GroupBean.class.getName());

	/**
	 * Creates a new instance of GroupBean
	 */
	public GroupBean() {
	}
}
