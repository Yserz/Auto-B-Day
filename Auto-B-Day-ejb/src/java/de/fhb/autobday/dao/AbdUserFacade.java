package de.fhb.autobday.dao;

import de.fhb.autobday.data.AbdUser;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@Stateless
public class AbdUserFacade extends AbstractFacade<AbdUser> {
	@PersistenceContext(unitName = "Auto-B-Day-ejbPU")
	private EntityManager em;

	@Override
	public EntityManager getEntityManager() {
		return em;
	}
	
	@Override
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
	public AbdUser findUserByUsername(String username){
		AbdUser user = null;
		
		try {
			user = (AbdUser) em.createNamedQuery("AbdUser.findByUsername")
				.setParameter("username", username).getSingleResult();
		} catch (NoResultException e) {
			System.out.println("Exception: "+e.getMessage());
		}
        return user;
    }
	
	public AbdUserFacade() {
		super(AbdUser.class);
	}
	
}
