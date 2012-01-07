package de.fhb.autobday.exception.connector;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ConnectorExceptionTest {

	public ConnectorExceptionTest() {
	}

	@Test
	public void testDefaultKonstruktor(){
		assertEquals(null,new ConnectorException().getMessage());		
	}
	
	@Test
	public void testKonstruktorWithParameter(){
		String message = "Dies ist eine Testmessage!";
		assertEquals(message,new ConnectorException(message).getMessage());		
	}
	
}
