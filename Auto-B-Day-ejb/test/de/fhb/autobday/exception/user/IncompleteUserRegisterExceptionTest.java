package de.fhb.autobday.exception.user;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class IncompleteUserRegisterExceptionTest {
	
	public IncompleteUserRegisterExceptionTest() {
	}

	@Test
	public void testDefaultKonstruktor(){
		assertEquals(null,new IncompleteUserRegisterException().getMessage());		
	}
	
	@Test
	public void testKonstruktorWithParameter(){
		String message = "Dies ist eine Testmessage!";
		assertEquals(message,new IncompleteUserRegisterException(message).getMessage());		
	}

}
