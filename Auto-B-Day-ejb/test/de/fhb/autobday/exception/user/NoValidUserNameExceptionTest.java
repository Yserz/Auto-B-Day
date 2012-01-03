package de.fhb.autobday.exception.user;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class NoValidUserNameExceptionTest {
	
	public NoValidUserNameExceptionTest() {
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
		assertEquals(null,new NoValidUserNameException().getMessage());		
	}
	
	@Test
	public void testKonstruktorWithParameter(){
		String message = "Dies ist eine Testmessage!";
		assertEquals(message,new NoValidUserNameException(message).getMessage());		
	}

}