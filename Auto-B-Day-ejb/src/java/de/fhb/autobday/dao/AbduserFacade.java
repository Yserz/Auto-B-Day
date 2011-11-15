package de.fhb.autobday.dao;

import de.fhb.autobday.data.Abduser;
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
public class AbduserFacade extends AbstractFacade<Abduser>{
	@PersistenceContext(unitName = "Auto-B-Day-ejbPU")
	private EntityManager em;

	protected EntityManager getEntityManager() {
		return em;
	}

	public AbduserFacade() {
		super(Abduser.class);
	}
	
}
