package de.fhb.autobday.exception.contact;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class ContactExceptionTest {
	
	public ContactExceptionTest() {
	}

	@Test
	public void testDefaultKonstruktor(){
		assertEquals(null,new ContactException().getMessage());		
	}
	
	@Test
	public void testKonstruktorWithParameter(){
		String message = "Dies ist eine Testmessage!";
		assertEquals(message,new ContactException(message).getMessage());		
	}

}
