package de.fhb.autobday.dao;


import de.fhb.autobday.data.Accountdata;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Michael Koppen
 */
@Stateless
@LocalBean
public class AccountdataFacade extends AbstractFacade<Accountdata> {
	@PersistenceContext(unitName = "Auto-B-Day-ejbPU")
	private EntityManager em;

	protected EntityManager getEntityManager() {
		return em;
	}

	public AccountdataFacade() {
		super(Accountdata.class);
	}
	
	
}
