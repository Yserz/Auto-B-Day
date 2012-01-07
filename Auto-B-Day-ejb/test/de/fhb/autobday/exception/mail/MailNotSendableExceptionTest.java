package de.fhb.autobday.exception.mail;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class MailNotSendableExceptionTest {
	
	public MailNotSendableExceptionTest() {
	}

	@Test
	public void testDefaultKonstruktor(){
		assertEquals(null,new MailNotSendableException().getMessage());		
	}
	
	@Test
	public void testKonstruktorWithParameter(){
		String message = "Dies ist eine Testmessage!";
		assertEquals(message,new MailNotSendableException(message).getMessage());		
	}

}
