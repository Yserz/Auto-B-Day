package de.fhb.autobday.exception;

import de.fhb.autobday.exception.commons.HashFailException;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HashFailExceptionTest {
	
	public HashFailExceptionTest() {
	}

	@Test
	public void testDefaultKonstruktor(){
		assertEquals(null,new HashFailException().getMessage());		
	}
	
	@Test
	public void testKonstruktorWithParameter(){
		String message = "Dies ist eine Testmessage!";
		assertEquals(message,new HashFailException(message).getMessage());		
	}

}
