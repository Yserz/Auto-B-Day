package de.fhb.autobday.exception.user;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class UserExceptionTest {
	
	public UserExceptionTest() {
	}

	@Test
	public void testDefaultKonstruktor(){
		assertEquals(null,new UserException().getMessage());		
	}
	
	@Test
	public void testKonstruktorWithParameter(){
		String message = "Dies ist eine Testmessage!";
		assertEquals(message,new UserException(message).getMessage());		
	}

}
