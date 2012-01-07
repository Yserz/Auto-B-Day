package de.fhb.autobday.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class AbdExceptionTest {
	
	public AbdExceptionTest() {
	}
	
	@Test
	public void testDefaultKonstruktor(){
		assertEquals(null,new AbdException().getMessage());		
	}
	
	@Test
	public void testKonstruktorWithParameter(){
		String message = "Dies ist eine Testmessage!";
		assertEquals(message,new AbdException(message).getMessage());		
	}
	
}
