package de.fhb.autobday.dao;

import de.fhb.autobday.data.AbdUser;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@Stateless
public class AbdUserFacade extends AbstractFacade<AbdUser> {
	@PersistenceContext(unitName = "Auto-B-Day-ejbPU")
	private EntityManager em;

	protected EntityManager getEntityManager() {
		return em;
	}

	public AbdUserFacade() {
		super(AbdUser.class);
	}
	
}
