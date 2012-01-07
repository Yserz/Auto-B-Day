package de.fhb.autobday.exception.account;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AccountAlreadyExsistsExceptionTest {
	
	public AccountAlreadyExsistsExceptionTest() {
	}

	@Test
	public void testDefaultKonstruktor(){
		assertEquals(null,new AccountAlreadyExsistsException().getMessage());		
	}
	
	@Test
	public void testKonstruktorWithParameter(){
		String message = "Dies ist eine Testmessage!";
		assertEquals(message,new AccountAlreadyExsistsException(message).getMessage());		
	}

}
