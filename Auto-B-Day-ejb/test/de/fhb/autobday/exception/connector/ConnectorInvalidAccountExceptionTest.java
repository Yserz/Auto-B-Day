package de.fhb.autobday.exception.connector;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ConnectorInvalidAccountExceptionTest {

	public ConnectorInvalidAccountExceptionTest() {
	}

	@Test
	public void testDefaultKonstruktor(){
		assertEquals(null,new ConnectorInvalidAccountException().getMessage());		
	}
	
	@Test
	public void testKonstruktorWithParameter(){
		String message = "Dies ist eine Testmessage!";
		assertEquals(message,new ConnectorInvalidAccountException(message).getMessage());		
	}
	
}
