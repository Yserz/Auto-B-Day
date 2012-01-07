package de.fhb.autobday.exception.contact;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class NoContactGivenExceptionTest {
	
	public NoContactGivenExceptionTest() {
	}

	@Test
	public void testDefaultKonstruktor(){
		assertEquals(null,new NoContactGivenException().getMessage());		
	}
	
	@Test
	public void testKonstruktorWithParameter(){
		String message = "Dies ist eine Testmessage!";
		assertEquals(message,new NoContactGivenException(message).getMessage());		
	}

}
