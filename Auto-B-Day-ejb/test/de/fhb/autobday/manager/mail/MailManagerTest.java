package de.fhb.autobday.manager.mail;

import static org.easymock.EasyMock.*;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.stvconsultants.easygloss.javaee.JavaEEGloss;

import de.fhb.autobday.dao.AbdUserFacade;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@Ignore
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
	@Ignore
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

	/**
	 * Test of sendNotificationMail method, of class MailManager.
	 */
	@Test
	@Ignore
	public void testSendNotificationMail() throws Exception {
		System.out.println("sendNotificationMail");
	}

	/**
	 * Test of sendForgotPasswordMail method, of class MailManager.
	 */
	@Test
	@Ignore
	public void testSendForgotPasswordMail() throws Exception {
		System.out.println("sendForgotPasswordMail");
	}
}
