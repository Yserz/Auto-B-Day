package de.fhb.autobday.commons;

import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

import java.io.BufferedInputStream;
import java.util.HashMap;
import java.util.Properties;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class AccountPropertiesFileTest {
	
	public AccountPropertiesFileTest(){
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
		assertEquals(true, new AccountPropertiesFile() instanceof AccountPropertiesFile);
	}
	
	@Test
	@Ignore
	public void testReadPropertiesFile(){
		
		BufferedInputStream stream = createMock (BufferedInputStream.class);
		Properties properties = createMock (Properties.class);
		expect(properties.getProperty("loginname")).andReturn("testname");
		expect(properties.getProperty("password")).andReturn("testpw");
		HashMap<String, String> props = new HashMap<String, String>();
		props.put("loginname", "testname");
		props.put("password", "testpw");
		
		replay(stream);
		replay(properties);
		
		assertEquals(props, AccountPropertiesFile.getProperties("test"));
	}

}
