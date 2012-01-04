package de.fhb.autobday.manager.mail;

import static org.easymock.EasyMock.*;

import javax.mail.Transport;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;


/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */

public class MailManagerTest {
	
	private MailManager managerUnderTest;

	
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
		managerUnderTest = new MailManager();
	}
	
	@After
	public void tearDown() {
		
	}

	/**
	 * Test of sendBdayMail method, of class MailManager.
	 */
	@Test
	public void testSendBdayMail() throws Exception {
		System.out.println("testSendBdayMail");
		
		Transport transport = createMock(Transport.class);
		
		replay(transport);
		
		/*
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", "mymail.server.org");
		props.setProperty("mail.user", "fhbtestacc@googlemail.com");
		props.setProperty("mail.password", "TestGoogle123");

		Session mailSession = Session.getDefaultInstance(props, null);
		*/
		
		managerUnderTest.sendBdayMail("test@aol.de","test@fhb.de" , "betreff", "der text");
	}


}
