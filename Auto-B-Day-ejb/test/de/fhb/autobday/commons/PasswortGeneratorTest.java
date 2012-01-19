package de.fhb.autobday.commons;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Test the Password Generator
 *
 * @author Andy Klay <klay@fh-brandenburg.de>
 *
 */
public class PasswortGeneratorTest {

	public PasswortGeneratorTest() {
	}

	/**
	 * Test the default konstruktor
	 */
	@Test
	public void testKonstruktor() {
		assertEquals(true, new PasswordGenerator() instanceof PasswordGenerator);
	}

	@Test
	public void testGeneratePassword() {
		System.out.println("testGeneratePassword");

		//prepare objects to test
		Pattern pattern = Pattern.compile("([A-Z]|[a-z]){" + PasswordGenerator.PASSWORD_LENGTH + "}");
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
		Pattern pattern = Pattern.compile("[0-9]{" + PasswordGenerator.SALT_LENGTH + "}");
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
		Pattern pattern = Pattern.compile("([A-Z]|[a-z]|[0-9]){" + PasswordGenerator.SALT_LENGTH + "}");
		Matcher matcher;
		String generatedContent;

		//call method to test
		generatedContent = PasswordGenerator.generateSalt();

		//verify		
		matcher = pattern.matcher(generatedContent);
		assertTrue(matcher.matches());

	}
}
