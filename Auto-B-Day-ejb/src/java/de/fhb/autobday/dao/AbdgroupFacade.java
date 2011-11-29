package de.fhb.autobday.dao;

import de.fhb.autobday.data.Abdgroup;
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
public class AbdgroupFacade extends AbstractFacade<Abdgroup>{
	@PersistenceContext(unitName = "Auto-B-Day-ejbPU")
	private EntityManager em;

	protected EntityManager getEntityManager() {
		return em;
	}

	public AbdgroupFacade() {
		super(Abdgroup.class);
	}
	
}
