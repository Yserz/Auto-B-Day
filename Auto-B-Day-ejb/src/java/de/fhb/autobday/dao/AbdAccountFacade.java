package de.fhb.autobday.dao;

import de.fhb.autobday.data.AbdAccount;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@Stateless
public class AbdAccountFacade extends AbstractFacade<AbdAccount> {
	@PersistenceContext(unitName = "Auto-B-Day-ejbPU")
	private EntityManager em;

	protected EntityManager getEntityManager() {
		return em;
	}

	public AbdAccountFacade() {
		super(AbdAccount.class);
	}
	
}
