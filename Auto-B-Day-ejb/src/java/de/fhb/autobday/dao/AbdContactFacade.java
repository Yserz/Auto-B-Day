package de.fhb.autobday.dao;

import de.fhb.autobday.data.AbdContact;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@Stateless
public class AbdContactFacade extends AbstractFacade<AbdContact> {
	@PersistenceContext(unitName = "Auto-B-Day-ejbPU")
	private EntityManager em;

	protected EntityManager getEntityManager() {
		return em;
	}
	public Collection<AbdContact> findContactByBday(Date bday) {
        return (Collection<AbdContact>) em.createNamedQuery("Contact.findByBday")
				.setParameter("bday", bday).getResultList();
    }
	public AbdContactFacade() {
		super(AbdContact.class);
	}
	
}
