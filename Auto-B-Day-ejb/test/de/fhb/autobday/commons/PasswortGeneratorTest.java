package de.fhb.autobday.commons;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PasswortGeneratorTest {
	
	
	public PasswortGeneratorTest(){
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPasswortGenerator() {
		System.out.println("TestPasswortGenerator");
		Pattern pattern = Pattern.compile("[a-z]{8}");
		String password = PasswortGenerator.generatePassword();
		Matcher matcher = pattern.matcher(password);
		assertTrue(matcher.matches());
		
	}

}
