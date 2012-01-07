package de.fhb.autobday.exception.connector;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class ConnectorNoConnectionExceptionTest {

	public ConnectorNoConnectionExceptionTest() {
	}

	@Test
	public void testDefaultKonstruktor(){
		assertEquals(null,new ConnectorNoConnectionException().getMessage());		
	}
	
	@Test
	public void testKonstruktorWithParameter(){
		String message = "Dies ist eine Testmessage!";
		assertEquals(message,new ConnectorNoConnectionException(message).getMessage());		
	}
	
}
