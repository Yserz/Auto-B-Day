package de.fhb.autobday.manager.mail;


import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import de.fhb.autobday.data.AbdAccount;



/**
 *
 * @author Christoph <ott@fh-brandenburg.de>
 */
public class GoogleMailManagerTestIntegration {
		
	private static GoogleMailManager mailManagerUnderTest;
	
	private static AbdAccount account;
	
	
	@BeforeClass
	public static void setUpClass() {		
		//set Objekts to inject
		mailManagerUnderTest = new GoogleMailManager();
		account = new AbdAccount(2, "fhbtestacc@googlemail.com", "TestGoogle123", "type");
	}
	
	/**
	 * Test of sendBdayMail method, of class MailManager.
	 */
	@Test
	@Ignore
	public void testSendSystemMail() throws Exception {
		System.out.println("testSendBdayMail");
		//Funktioniert nur mit gesetzten Properties
		mailManagerUnderTest.sendSystemMail("It´s a test", "A test", "fhbtestacc@googlemail.com");
	}
	
	@Test
	@Ignore
	public void testSendUserMail() throws Exception{
		System.out.println("testSendUserMail");
		//Funktioniert nur mit gesetzten Properties
		mailManagerUnderTest.sendUserMail(account,"It´s a testSendUserMail", "A test", "fhbtestacc@googlemail.com");
	}
}

