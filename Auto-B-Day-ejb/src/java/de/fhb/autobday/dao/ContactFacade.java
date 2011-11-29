package de.fhb.autobday.dao;

import de.fhb.autobday.data.Contact;
import java.util.Collection;
import java.util.Date;
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
public class ContactFacade extends AbstractFacade<Contact> {
	@PersistenceContext(unitName = "Auto-B-Day-ejbPU")
	private EntityManager em;

	protected EntityManager getEntityManager() {
		return em;
	}
	public Collection<Contact> findContactByBday(Date bday) {
        return (Collection<Contact>) em.createNamedQuery("Contact.findByBday")
				.setParameter("bday", bday).getResultList();
    }

	public ContactFacade() {
		super(Contact.class);
	}
	
}
