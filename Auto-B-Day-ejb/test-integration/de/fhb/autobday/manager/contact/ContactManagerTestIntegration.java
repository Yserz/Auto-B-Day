package de.fhb.autobday.manager.contact;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.stvconsultants.easygloss.javaee.JavaEEGloss;

import de.fhb.autobday.dao.AbdAccountFacade;
import de.fhb.autobday.dao.AbdUserFacade;
import de.fhb.autobday.manager.account.AccountManager;

public class ContactManagerTestIntegration {
	
	private JavaEEGloss gloss;
	
	private AccountManager managerUnderTest;
	
	private Connection con;
	
	@Before
	public void setUp() {

	}
	
	/**
	 * Test of addAccount method, of class AccountManager.
	 */
	@Test
	public void testAddAccount() throws Exception {
		
		

	}
	
}
