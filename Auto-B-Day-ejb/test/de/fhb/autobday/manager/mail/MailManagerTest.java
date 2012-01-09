package de.fhb.autobday.manager.mail;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import javax.ejb.EJB;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.stvconsultants.easygloss.javaee.JavaEEGloss;

import de.fhb.autobday.dao.AbdUserFacade;
import de.fhb.autobday.data.AbdUser;


/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Session.class, Transport.class})
@Ignore
public class MailManagerTest {
	
	private JavaEEGloss gloss;
	
	private Session mailSessionMock;
	
	private AbdUserFacade userDAOMock;
	
	private MailManager mailManagerUnderTest;
	
	private AbdUser user;
	
	
	@Before
	public void setUp() {
		gloss= new JavaEEGloss();
		
		//create Mocks
		userDAOMock = createMock(AbdUserFacade.class);
		mailSessionMock = PowerMock.createMock(Session.class);
		PowerMock.mockStatic(Transport.class);
		
		//set Objekts to inject
		gloss.addEJB(userDAOMock);
		
		mailManagerUnderTest = gloss.make(MailManager.class);
		mailManagerUnderTest.setMailSession(mailSessionMock);
		
		user = new AbdUser(1, "bienemaja", "1234abcd", "salt", "mustermann", "max");
		user.setMail("bienemaja@googlemail.com");
	}
	
	//TODO Tests implementieren...TINO
	
	/**
	 * Test of sendBdayMail method, of class MailManager.
	 */
	@Test
	public void testSendBdayMail() throws Exception {
		System.out.println("testSendBdayMail");
		
		
		Transport.send((Message)anyObject());
		
		PowerMock.replay(Transport.class);
		
		mailManagerUnderTest.sendBdayMail("test@aol.de","test@fhb.de" , "betreff", "der text");
	}
	
	@Test
	public void testSendForgotPasswordMail() throws Exception{
		expect(userDAOMock.find(user.getId())).andReturn(user);
		userDAOMock.edit(user);
		Transport.send((Message)anyObject());
		replay(userDAOMock);
		PowerMock.replay(Transport.class);
		mailManagerUnderTest.sendForgotPasswordMail(user.getId());
		verify(userDAOMock);
	}

}
