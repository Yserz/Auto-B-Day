/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.autobday.dao;

import de.fhb.autobday.data.Abdcontact;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author MacYser
 */
@Stateless
public class AbdcontactFacade extends AbstractFacade<Abdcontact> {
	@PersistenceContext(unitName = "Auto-B-Day-ejbPU")
	private EntityManager em;

	protected EntityManager getEntityManager() {
		return em;
	}
	public Collection<Abdcontact> findContactByBday(Date bday) {
        return (Collection<Abdcontact>) em.createNamedQuery("Contact.findByBday")
				.setParameter("bday", bday).getResultList();
    }
	public AbdcontactFacade() {
		super(Abdcontact.class);
	}
	
}
