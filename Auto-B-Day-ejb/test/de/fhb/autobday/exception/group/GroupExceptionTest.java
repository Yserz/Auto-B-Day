package de.fhb.autobday.exception.group;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class GroupExceptionTest {
	
	public GroupExceptionTest() {
	}
	
	@Test
	public void testDefaultKonstruktor(){
		assertEquals(null,new GroupException().getMessage());		
	}
	
	@Test
	public void testKonstruktorWithParameter(){
		String message = "Dies ist eine Testmessage!";
		assertEquals(message,new GroupException(message).getMessage());		
	}

}
