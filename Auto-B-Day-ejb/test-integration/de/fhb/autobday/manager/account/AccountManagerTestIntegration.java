package de.fhb.autobday.manager.account;

import org.junit.Before;
import org.junit.Test;

import com.stvconsultants.easygloss.javaee.JavaEEGloss;

import de.fhb.autobday.dao.AbdAccountFacade;
import de.fhb.autobday.dao.AbdUserFacade;

public class AccountManagerTestIntegration {
	
	private JavaEEGloss gloss;
	
	private AccountManager managerUnderTest;
	
	@Before
	public void setUp() {
		gloss= new JavaEEGloss();
		gloss.addEJB(AbdUserFacade.class);
		gloss.addEJB(AbdAccountFacade.class);
		managerUnderTest=gloss.make(AccountManager.class);
		//set Objekts to inject
		managerUnderTest.setUserDAO(new AbdUserFacade());
		managerUnderTest.setAccountDAO(new AbdAccountFacade());
	}
	/**
	 * Test of addAccount method, of class AccountManager.
	 */
	@Test
	public void testAddAccount() throws Exception {
		
		System.out.println("addAccount");
		
		//prepare test variables
		String password="password";
		String userName="mustermann";
		String type="type";
		
		
		// testing Methodcall
		managerUnderTest.addAccount(1, password, userName, type);
		

	}
	
}
