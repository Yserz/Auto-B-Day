package de.fhb.autobday.exception.contact;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class ContactNotFoundExceptionTest {
	
	public ContactNotFoundExceptionTest() {
	}

	@Test
	public void testDefaultKonstruktor(){
		assertEquals(null,new ContactNotFoundException().getMessage());		
	}
	
	@Test
	public void testKonstruktorWithParameter(){
		String message = "Dies ist eine Testmessage!";
		assertEquals(message,new ContactNotFoundException(message).getMessage());		
	}

}
