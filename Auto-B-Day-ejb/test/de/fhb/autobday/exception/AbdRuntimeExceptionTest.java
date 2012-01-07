package de.fhb.autobday.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AbdRuntimeExceptionTest {
	
	public AbdRuntimeExceptionTest() {
	}

	@Test
	public void testDefaultKonstruktor(){
		assertEquals(null,new AbdRuntimeException().getMessage());		
	}
	
	@Test
	public void testKonstruktorWithParameter(){
		String message = "Dies ist eine Testmessage!";
		assertEquals(message,new AbdRuntimeException(message).getMessage());		
	}

}
