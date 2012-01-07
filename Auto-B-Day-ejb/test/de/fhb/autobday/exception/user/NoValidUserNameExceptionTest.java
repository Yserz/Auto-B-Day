package de.fhb.autobday.exception.user;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NoValidUserNameExceptionTest {
	
	public NoValidUserNameExceptionTest() {
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
