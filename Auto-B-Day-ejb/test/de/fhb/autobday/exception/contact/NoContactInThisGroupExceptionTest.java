package de.fhb.autobday.exception.contact;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class NoContactInThisGroupExceptionTest {
	
	public NoContactInThisGroupExceptionTest() {
	}
	
	@Test
	public void testDefaultKonstruktor(){
		assertEquals(null,new NoContactInThisGroupException().getMessage());		
	}
	
	@Test
	public void testKonstruktorWithParameter(){
		String message = "Dies ist eine Testmessage!";
		assertEquals(message,new NoContactInThisGroupException(message).getMessage());		
	}

}
