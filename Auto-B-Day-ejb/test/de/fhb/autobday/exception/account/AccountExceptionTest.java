package de.fhb.autobday.exception.account;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



public class AccountExceptionTest {

	public AccountExceptionTest() {
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

	@Test
	public void testDefaultKonstruktor(){
		assertEquals(null,new AccountException().getMessage());		
	}
	
	@Test
	public void testKonstruktorWithParameter(){
		String message = "Dies ist eine Testmessage!";
		assertEquals(message,new AccountException(message).getMessage());		
	}
	
}