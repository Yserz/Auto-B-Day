package de.fhb.autobday.manager.group;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.stvconsultants.easygloss.javaee.JavaEEGloss;

import de.fhb.autobday.dao.AbdContactFacade;
import de.fhb.autobday.dao.AbdGroupFacade;
import de.fhb.autobday.dao.AbdGroupToContactFacade;
import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.data.AbdGroup;
import de.fhb.autobday.manager.contact.ContactManager;

/**
 * 
 * @author Andy Klay <klay@fh-brandenburg.de>
 */
public class GroupManagerTest {
	
	private JavaEEGloss gloss;
	
	private GroupManager managerUnderTest;
	
	private AbdGroupFacade groupDAOMock;
	private AbdContactFacade contactDAOMock;
	
	public GroupManagerTest() {
		
	}

	@BeforeClass
	public static void setUpClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		
	}
	
	@Before
	public void setUp() {
		
		gloss= new JavaEEGloss();
		
		//create Mocks
		contactDAOMock = EasyMock.createMock(AbdContactFacade.class);
		groupDAOMock = EasyMock.createMock(AbdGroupFacade.class);
		
		//set Objekts to inject
		gloss.addEJB(contactDAOMock);
		gloss.addEJB(groupDAOMock);
		
		//create Manager with Mocks
		managerUnderTest=gloss.make(GroupManager.class);
		
	}
	
	@After
	public void tearDown() {
		
	}

	/**
	 * Test of getGroup method, of class GroupManager.
	 */
	@Test
	public void testGetGroup() throws Exception {
		System.out.println("getGroup");

		
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setTemplate method, of class GroupManager.
	 */
	@Test
	public void testSetTemplate() throws Exception {
		System.out.println("setTemplate");

		
		
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getTemplate method, of class GroupManager.
	 */
	@Test
	public void testGetTemplate() throws Exception {
		System.out.println("getTemplate");
		
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of testTemplate method, of class GroupManager.
	 */
	@Test
	public void testTestTemplate() throws Exception {
		System.out.println("testTemplate");
		
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setActive method, of class GroupManager.
	 */
	@Test
	public void testSetActive() throws Exception {
		System.out.println("setActive");

	}

	/**
	 * Test of parseTemplate method, of class GroupManager.
	 */
	@Test
	public void testParseTemplate() throws Exception {
		System.out.println("parseTemplate");
		String template = "";
		AbdContact contact = null;
//		GroupManagerLocal instance = (GroupManagerLocal)container.getContext().lookup("java:global/classes/GroupManager");
		String expResult = "";
//		String result = instance.parseTemplate(template, contact);
//		assertEquals(expResult, result);
		

	}

	/**
	 * Test of parseSlashExpression method, of class GroupManager.
	 */
	@Test
	public void testParseSlashExpressionW() throws Exception {
		
		System.out.println("parseSlashExpression");
		
		String expression = "she/he";
		char sex = 'w';
		
//		GroupManagerLocal instance = (GroupManagerLocal)container.getContext().lookup("java:global/classes/GroupManager");
		String expResult = "she";
		
//		String result = instance.parseSlashExpression(expression, sex);
		
//		assertEquals(expResult, result);
	}
	
	/**
	 * Test of parseSlashExpression method, of class GroupManager.
	 */
	@Test
	public void testParseSlashExpressionM() throws Exception {
		System.out.println("parseSlashExpression");
		String expression = "she/he";
		char sex = 'm';
//		GroupManagerLocal instance = (GroupManagerLocal)container.getContext().lookup("java:global/classes/GroupManager");
		String expResult = "he";
//		String result = instance.parseSlashExpression(expression, sex);
//		assertEquals(expResult, result);
	}
}
