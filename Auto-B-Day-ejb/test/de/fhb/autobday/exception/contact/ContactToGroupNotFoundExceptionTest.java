package de.fhb.autobday.exception.contact;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class ContactToGroupNotFoundExceptionTest {
	
	public ContactToGroupNotFoundExceptionTest() {
	}

	@Test
	public void testDefaultKonstruktor(){
		assertEquals(null,new ContactToGroupNotFoundException().getMessage());		
	}
	
	@Test
	public void testKonstruktorWithParameter(){
		String message = "Dies ist eine Testmessage!";
		assertEquals(message,new ContactToGroupNotFoundException(message).getMessage());		
	}

}
