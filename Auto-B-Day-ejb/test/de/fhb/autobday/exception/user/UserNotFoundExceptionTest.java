package de.fhb.autobday.exception.user;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UserNotFoundExceptionTest {
	
	public UserNotFoundExceptionTest() {
	}
	
	@Test
	public void testDefaultKonstruktor(){
		assertEquals(null,new UserNotFoundException().getMessage());		
	}
	
	@Test
	public void testKonstruktorWithParameter(){
		String message = "Dies ist eine Testmessage!";
		assertEquals(message,new UserNotFoundException(message).getMessage());		
	}

}
