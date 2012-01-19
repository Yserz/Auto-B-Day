package de.fhb.autobday.manager.mail;

import de.fhb.autobday.commons.PropertyLoader;
import de.fhb.autobday.data.AbdAccount;
import java.util.Properties;
import static org.easymock.EasyMock.*;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Christoph <ott@fh-brandenburg.de>
 */
@Ignore
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
	 * Test of sendSystemMail method, of class MailManager.
	 */
	@Test
	public void testSendSystemMail() throws Exception {
		System.out.println("testSendBdayMail");

		PropertyLoader propLoader = createMock(PropertyLoader.class);
		mailManagerUnderTest.setPropLoader(propLoader);

		Properties accountProps = new Properties();
		accountProps.setProperty("mail.smtp.user", "fhbtestacc@googlemail.com");
		accountProps.setProperty("mail.smtp.password", "TestGoogle123");


		expect(propLoader.loadSystemProperty("/SystemMailAccount.properties")).andReturn(accountProps);

		replay(propLoader);

		mailManagerUnderTest.sendSystemMail("It´s a test", "A test", "fhbtestacc@googlemail.com");

		verify(propLoader);
	}

	/**
	 * Test of sendUserMail method, of class MailManager.
	 */
	@Test
	public void testSendUserMail() throws Exception {
		System.out.println("testSendUserMail");
		PropertyLoader propLoader = createMock(PropertyLoader.class);
		mailManagerUnderTest.setPropLoader(propLoader);

		Properties props = new Properties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.port", "587");
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.starttls.enable", "true");
		props.setProperty("mail.smtp.debug", "true");

		expect(propLoader.loadSystemProperty("/SystemMail.properties")).andReturn(props);
		replay(propLoader);

		mailManagerUnderTest.sendUserMail(account, "It´s a testSendUserMail", "A test", "fhbtestacc@googlemail.com");

		verify(propLoader);
	}
}
