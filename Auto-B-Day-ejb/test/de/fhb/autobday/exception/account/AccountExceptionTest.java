package de.fhb.autobday.exception.account;

import static org.junit.Assert.assertEquals;

import org.junit.Test;



public class AccountExceptionTest {

	public AccountExceptionTest() {
	}

	@Test
	public void testDefaultKonstruktor(){
		assertEquals(null,new AccountException().getMessage());		
	}
	
	@Test
	public void testKonstruktorWithParameter(){
		String message = "Dies ist eine Testmessage!";
		assertEquals(message,new AccountException(message).getMessage());		
	}
	
}
