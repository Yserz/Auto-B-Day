package de.fhb.autobday.exception;

import static org.junit.Assert.assertEquals;

import org.junit.*;


public class AbdExceptionTest {
	
	public AbdExceptionTest() {
	}
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
	}
	
	@Before
	public void setUp() {

	}
	
	@After
	public void tearDown() {
	}

	@Ignore
	public void testDefaultKonstruktor(){
		assertEquals(null,new AbdException().getMessage());		
	}
	
	@Ignore
	public void testKonstruktorWithParameter(){
		String message = "Dies ist eine Testmessage!";
		assertEquals(message,new AbdException(message).getMessage());		
	}
	
}
