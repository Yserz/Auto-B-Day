package de.fhb.autobday.manager.mail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class MailManagerTest {
	//private EJBContainer container;
	
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
		//container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
	}
	
	@After
	public void tearDown() {
		//container.close();
	}

	/**
	 * Test of sendBdayMail method, of class MailManager.
	 */
	@Test
	public void testSendBdayMail() throws Exception {
		System.out.println("sendBdayMail");
		//MailManagerLocal instance = (MailManagerLocal)container.getContext().lookup("java:global/classes/MailManager");
		//TODO parameterliste hat sich geändert
		//instance.sendBdayMail();
		
		// TODO review the generated test code and remove the default call to fail.
		//fail("The test case is a prototype.");
	}

	/**
	 * Test of sendNotificationMail method, of class MailManager.
	 */
	@Test
	public void testSendNotificationMail() throws Exception {
		System.out.println("sendNotificationMail");
		//MailManagerLocal instance = (MailManagerLocal)container.getContext().lookup("java:global/classes/MailManager");
		//instance.sendNotificationMail();
		
		// TODO review the generated test code and remove the default call to fail.
		//fail("The test case is a prototype.");
	}

	/**
	 * Test of sendForgotPasswordMail method, of class MailManager.
	 */
	@Test
	public void testSendForgotPasswordMail() throws Exception {
		System.out.println("sendForgotPasswordMail");
		//MailManagerLocal instance = (MailManagerLocal)container.getContext().lookup("java:global/classes/MailManager");
//		instance.sendForgotPasswordMail();
		
		// TODO review the generated test code and remove the default call to fail.
		//fail("The test case is a prototype.");
	}
}
