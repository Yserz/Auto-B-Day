package de.fhb.autobday.exception.account;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class AccountNotFoundExceptionTest {

	public AccountNotFoundExceptionTest() {
	}
	
	@Test
	public void testDefaultKonstruktor(){
		assertEquals(null,new AccountNotFoundException().getMessage());		
	}
	
	@Test
	public void testKonstruktorWithParameter(){
		String message = "Dies ist eine Testmessage!";
		assertEquals(message,new AccountNotFoundException(message).getMessage());		
	}
	
}
