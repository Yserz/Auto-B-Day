package de.fhb.autobday.exception.user;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IncompleteLoginDataExceptionTest {
	
	public IncompleteLoginDataExceptionTest() {
	}

	@Test
	public void testDefaultKonstruktor(){
		assertEquals(null,new IncompleteLoginDataException().getMessage());		
	}
	
	@Test
	public void testKonstruktorWithParameter(){
		String message = "Dies ist eine Testmessage!";
		assertEquals(message,new IncompleteLoginDataException(message).getMessage());		
	}

}
