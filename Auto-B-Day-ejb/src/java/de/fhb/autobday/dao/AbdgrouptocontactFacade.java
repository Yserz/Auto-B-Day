/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.autobday.dao;

import de.fhb.autobday.data.Abdgrouptocontact;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author MacYser
 */
@Stateless
public class AbdgrouptocontactFacade extends AbstractFacade<Abdgrouptocontact> {
	@PersistenceContext(unitName = "Auto-B-Day-ejbPU")
	private EntityManager em;

	protected EntityManager getEntityManager() {
		return em;
	}

	public AbdgrouptocontactFacade() {
		super(Abdgrouptocontact.class);
	}
	
}
