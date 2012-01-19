package de.fhb.autobday.dao;

import de.fhb.autobday.data.AbdAccount;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Facade-pattern for database operations for accounts.
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@Stateless
public class AbdAccountFacade extends AbstractFacade<AbdAccount> {

	@PersistenceContext(unitName = "Auto-B-Day-ejbPU")
	private EntityManager em;

	public AbdAccountFacade() {
		super(AbdAccount.class);
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	@Override
	public void setEntityManager(EntityManager em) {
		this.em = em;

	}
}
