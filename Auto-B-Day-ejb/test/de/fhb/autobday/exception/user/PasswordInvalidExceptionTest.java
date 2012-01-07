package de.fhb.autobday.exception.user;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class PasswordInvalidExceptionTest {
	
	public PasswordInvalidExceptionTest() {
	}

	@Test
	public void testDefaultKonstruktor(){
		assertEquals(null,new PasswordInvalidException().getMessage());		
	}
	
	@Test
	public void testKonstruktorWithParameter(){
		String message = "Dies ist eine Testmessage!";
		assertEquals(message,new PasswordInvalidException(message).getMessage());		
	}

}
