package de.fhb.autobday.exception.connector;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ConnectorCouldNotLoginExceptionTest {

	public ConnectorCouldNotLoginExceptionTest() {
	}

	@Test
	public void testDefaultKonstruktor(){
		assertEquals(null,new ConnectorCouldNotLoginException().getMessage());		
	}
	
	@Test
	public void testKonstruktorWithParameter(){
		String message = "Dies ist eine Testmessage!";
		assertEquals(message,new ConnectorCouldNotLoginException(message).getMessage());		
	}
	
}
