package de.fhb.autobday.commons;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test the Password Generator
 * 
 * @author
 * Andy Klay <klay@fh-brandenburg.de>
 *
 */
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

	/**
	 * Test the default konstruktor
	 */
	@Test
	public void testKonstruktor(){
		assertEquals(true, new PasswordGenerator() instanceof PasswordGenerator);
	}
	
	@Test
	public void testGeneratePassword() {
		System.out.println("testGeneratePassword");
		
		//prepare objects to test
		Pattern pattern = Pattern.compile("([A-Z]|[a-z]){" + PasswordGenerator.PASSWORD_LENGTH +"}");
		Matcher matcher;
		String generatedContent;
		
		//call method to test
		generatedContent = PasswordGenerator.generatePassword();
		
		//verify		
		matcher = pattern.matcher(generatedContent);
		assertTrue(matcher.matches());
		
	}
	
	@Test
	public void testGenerateNumbers() {
		System.out.println("testGenerateNumbers");
		
		//prepare objects to test
		Pattern pattern = Pattern.compile("[0-9]{" + PasswordGenerator.SALT_LENGTH +"}");
		Matcher matcher;
		String generatedContent;
		
		//call method to test
		generatedContent = PasswordGenerator.generateNumbers();
		
		//verify		
		matcher = pattern.matcher(generatedContent);
		assertTrue(matcher.matches());
		
	}
	
	@Test
	public void testGenerateSalt() {
		System.out.println("testGenerateSalt");
		
		//prepare objects to test
		Pattern pattern = Pattern.compile("([A-Z]|[a-z]|[0-9]){" + PasswordGenerator.SALT_LENGTH +"}");
		Matcher matcher;
		String generatedContent;
		
		//call method to test
		generatedContent = PasswordGenerator.generateSalt();
		
		//verify		
		matcher = pattern.matcher(generatedContent);
		assertTrue(matcher.matches());
		
	}

}
