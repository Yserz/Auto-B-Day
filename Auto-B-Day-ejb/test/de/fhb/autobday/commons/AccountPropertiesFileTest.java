package de.fhb.autobday.commons;

import static org.junit.Assert.assertEquals;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({BufferedInputStream.class, Properties.class})
public class AccountPropertiesFileTest {
	
	public AccountPropertiesFileTest(){
	}

	@Before
	public void setUp() throws Exception {
		Properties properties = new Properties();
		properties.put("loginname", "testname");
		properties.put("password", "testpw");
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream("test.txt"));
		properties.store(stream, "Properties");
		stream.close();
	}

	@After
	public void tearDown() throws Exception {
		File file = new  File("test.txt");
		file.delete();
	}

	/**
	 * Test the default konstruktor
	 */
	@Test
	public void testKonstruktor(){
		assertEquals(true, new AccountPropertiesFile() instanceof AccountPropertiesFile);
	}
	
	@Test
	public void testReadPropertiesFile() throws Exception{
		
		HashMap<String, String> props = new HashMap<String, String>();
		props.put("loginname", "testname");
		props.put("password", "testpw");

		assertEquals(props, AccountPropertiesFile.getProperties("test.txt"));
	}

}
