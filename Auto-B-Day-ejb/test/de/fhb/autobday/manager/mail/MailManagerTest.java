package de.fhb.autobday.manager.mail;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import javax.mail.Message;
import javax.mail.Transport;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.stvconsultants.easygloss.javaee.JavaEEGloss;

import de.fhb.autobday.dao.AbdUserFacade;
import de.fhb.autobday.data.AbdUser;
import de.fhb.autobday.exception.user.UserNotFoundException;


/**
 * Test the MailManager
 *
 * @author
 * Tino Reuschel <reuschel@fh-brandenburg.de>
 * Christoph Ott
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ Transport.class })
public class MailManagerTest {
	
	private JavaEEGloss gloss;
	
	private AbdUserFacade userDAOMock;
	
	private MailManager mailManagerUnderTest;
	
	private AbdUser user;
	
	
	@Before
	public void setUp() {
		
		gloss= new JavaEEGloss();
		
		//create Mocks
		userDAOMock = createMock(AbdUserFacade.class);

		PowerMock.mockStatic(Transport.class);
		
		//set Objekts to inject
		gloss.addEJB(userDAOMock);
		
		//create Manager with Mocks
		mailManagerUnderTest = gloss.make(MailManager.class);
		
		//create Objects needed for tests
		user = new AbdUser(1, "bienemaja", "1234abcd", "salt", "mustermann", "max");
		user.setMail("bienemaja@googlemail.com");
	}
	
	/**
	 * Test of sendBdayMail method, of class MailManager.
	 */
	@Test
	public void testSendBdayMail() throws Exception {
		System.out.println("testSendBdayMail");
		
		// Setting up the expected value of the method call of Mockobject
		Transport.send((Message)anyObject());
		
		// Setup is finished need to activate the mock
		PowerMock.replay(Transport.class);
		
		//call method to test
		mailManagerUnderTest.sendBdayMail("test@aol.de","test@fhb.de" , "betreff", "der text");
	}
	
	@Test
	public void testSendForgotPasswordMail() throws Exception{
		System.out.println("testSendForgotPasswordMail");
		
		// Setting up the expected value of the method call of Mockobject
		expect(userDAOMock.find(user.getId())).andReturn(user);
		userDAOMock.edit(user);
		Transport.send((Message)anyObject());
		
		// Setup is finished need to activate the mock
		replay(userDAOMock);
		PowerMock.replay(Transport.class);
		
		//call method to test
		mailManagerUnderTest.sendForgotPasswordMail(user.getId());
		
		// verify
		verify(userDAOMock);
	}

	@Test (expected = UserNotFoundException.class)
	public void testSendForgotPasswordMailShouldThrowUserNotFoundException() throws Exception{
		System.out.println("testSendForgotPasswordMailShouldThrowUserNotFoundException");
		
		// Setting up the expected value of the method call of Mockobject
		expect(userDAOMock.find(user.getId())).andReturn(null);
		userDAOMock.edit(user);
		Transport.send((Message)anyObject());
		
		// Setup is finished need to activate the mock
		replay(userDAOMock);
		PowerMock.replay(Transport.class);
		
		//call method to test
		mailManagerUnderTest.sendForgotPasswordMail(user.getId());
		
		// verify
		verify(userDAOMock);
	}
	
}

