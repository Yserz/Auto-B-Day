package de.fhb.autobday.exception.mail;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MailExceptionTest {
	
	public MailExceptionTest() {
	}

	@Test
	public void testDefaultKonstruktor(){
		assertEquals(null,new MailException().getMessage());		
	}
	
	@Test
	public void testKonstruktorWithParameter(){
		String message = "Dies ist eine Testmessage!";
		assertEquals(message,new MailException(message).getMessage());		
	}

}
