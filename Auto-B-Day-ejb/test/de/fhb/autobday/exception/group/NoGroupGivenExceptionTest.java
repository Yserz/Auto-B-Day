package de.fhb.autobday.exception.group;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class NoGroupGivenExceptionTest {
	
	public NoGroupGivenExceptionTest() {
	}

	@Test
	public void testDefaultKonstruktor(){
		assertEquals(null,new NoGroupGivenException().getMessage());		
	}
	
	@Test
	public void testKonstruktorWithParameter(){
		String message = "Dies ist eine Testmessage!";
		assertEquals(message,new NoGroupGivenException(message).getMessage());		
	}

}
