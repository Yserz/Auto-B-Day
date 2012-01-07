package de.fhb.autobday.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CanNotConvertGoogleBirthdayExceptionTest {
	
	public CanNotConvertGoogleBirthdayExceptionTest() {
	}

	@Test
	public void testDefaultKonstruktor(){
		assertEquals(null,new CanNotConvetGoogleBirthdayException().getMessage());		
	}
	
	@Test
	public void testKonstruktorWithParameter(){
		String message = "Dies ist eine Testmessage!";
		assertEquals(message,new CanNotConvetGoogleBirthdayException(message).getMessage());		
	}

}
