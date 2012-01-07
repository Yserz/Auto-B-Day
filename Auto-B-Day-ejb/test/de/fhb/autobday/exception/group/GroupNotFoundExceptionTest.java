package de.fhb.autobday.exception.group;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class GroupNotFoundExceptionTest {
	
	public GroupNotFoundExceptionTest() {
	}
	
	@Test
	public void testDefaultKonstruktor(){
		assertEquals(null,new GroupNotFoundException().getMessage());		
	}
	
	@Test
	public void testKonstruktorWithParameter(){
		String message = "Dies ist eine Testmessage!";
		assertEquals(message,new GroupNotFoundException(message).getMessage());		
	}

}
