/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.autobday.manager.mail;

import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MacYser
 */
public class MailManagerTest {
	
	public MailManagerTest() {
	}

	@BeforeClass
	public static void setUpClass() throws Exception {
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
	}
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}

	/**
	 * Test of sendBdayMail method, of class MailManager.
	 */
	@Test
	public void testSendBdayMail() throws Exception {
		System.out.println("sendBdayMail");
		EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
		MailManagerLocal instance = (MailManagerLocal)container.getContext().lookup("java:global/classes/MailManager");
		instance.sendBdayMail();
		container.close();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of sendNotificationMail method, of class MailManager.
	 */
	@Test
	public void testSendNotificationMail() throws Exception {
		System.out.println("sendNotificationMail");
		EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
		MailManagerLocal instance = (MailManagerLocal)container.getContext().lookup("java:global/classes/MailManager");
		instance.sendNotificationMail();
		container.close();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of sendForgotPasswordMail method, of class MailManager.
	 */
	@Test
	public void testSendForgotPasswordMail() throws Exception {
		System.out.println("sendForgotPasswordMail");
		EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
		MailManagerLocal instance = (MailManagerLocal)container.getContext().lookup("java:global/classes/MailManager");
		instance.sendForgotPasswordMail();
		container.close();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
}
