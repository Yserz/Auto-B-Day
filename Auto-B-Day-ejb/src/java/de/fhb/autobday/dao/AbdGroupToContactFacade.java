package de.fhb.autobday.dao;

import de.fhb.autobday.data.AbdGroupToContact;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@Stateless
public class AbdGroupToContactFacade extends AbstractFacade<AbdGroupToContact> {
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
	
	public Collection<AbdGroupToContact> findGroupByContact(String id) {
        return (Collection<AbdGroupToContact>) em.createNamedQuery("Contact.findByContact")
				.setParameter("contact", id).getResultList();
    }
	
	public AbdGroupToContactFacade() {
		super(AbdGroupToContact.class);
	}
	
}
