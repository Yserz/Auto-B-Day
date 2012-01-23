package de.fhb.autobday.dao;

import de.fhb.autobday.data.AbdGroup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Facade-pattern for database operations for groups.
 *
 * @author Michael Koppen mail: koppen@fh-brandenburg.de
 */
@Stateless
public class AbdGroupFacade extends AbstractFacade<AbdGroup> {

	@PersistenceContext(unitName = "Auto-B-Day-ejbPU")
	private EntityManager em;

	public AbdGroupFacade() {
		super(AbdGroup.class);
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
