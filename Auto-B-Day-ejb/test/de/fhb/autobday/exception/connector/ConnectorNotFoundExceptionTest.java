package de.fhb.autobday.exception.connector;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class ConnectorNotFoundExceptionTest {

	public ConnectorNotFoundExceptionTest() {
	}

	@Test
	public void testDefaultKonstruktor(){
		assertEquals(null,new ConnectorNotFoundException().getMessage());		
	}
	
	@Test
	public void testKonstruktorWithParameter(){
		String message = "Dies ist eine Testmessage!";
		assertEquals(message,new ConnectorNotFoundException(message).getMessage());		
	}
	
}
