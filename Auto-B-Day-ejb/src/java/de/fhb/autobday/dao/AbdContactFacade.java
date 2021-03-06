package de.fhb.autobday.dao;

import de.fhb.autobday.data.AbdContact;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Facade-pattern for database operations for contacts.
 *
 * @author Michael Koppen mail: koppen@fh-brandenburg.de
 */
@Stateless
public class AbdContactFacade extends AbstractFacade<AbdContact> {

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

	/**
	 * finds any contact with the given birthday.
	 *
	 * @param bday
	 * @return ContactCollection
	 */
	public Collection<AbdContact> findContactByBday(Date bday) {
		return (Collection<AbdContact>) em.createNamedQuery("Contact.findByBday").setParameter("bday", bday).getResultList();
	}

	public AbdContactFacade() {
		super(AbdContact.class);
	}
}
