package de.fhb.autobday.manager.group;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Date;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.stvconsultants.easygloss.javaee.JavaEEGloss;

import de.fhb.autobday.dao.AbdContactFacade;
import de.fhb.autobday.dao.AbdGroupFacade;
import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.data.AbdGroup;

/**
 * 
 * @author Andy Klay <klay@fh-brandenburg.de>
 * @author Michael Koppen <koppen@fh-brandenburg.de>
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

		//test variables
		String groupId="friends";	
		String template="template";
		AbdGroup group=new AbdGroup();
		group.setId(groupId);
		group.setTemplate(template);
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(groupDAOMock.find(groupId)).andReturn(group).times(1);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(groupDAOMock);
		
		// testing Methodcall
		managerUnderTest.getGroup(groupId);
		
		// verify		
		EasyMock.verify(groupDAOMock);
	}

	/**
	 * Test of getTemplate method, of class GroupManager.
	 */
	@Test
	public void testGetTemplate() throws Exception {
		
		System.out.println("getTemplate");
		
		//test variables
		String groupId="friends";	
		String template="template";
		AbdGroup group=new AbdGroup();
		group.setId(groupId);
		group.setTemplate(template);
		String output="";
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(groupDAOMock.find(groupId)).andReturn(group).times(1);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(groupDAOMock);
		
		// testing Methodcall
		output=managerUnderTest.getTemplate(groupId);
		
		// verify
		assertEquals("Template test", template, output);	
		EasyMock.verify(groupDAOMock);
		
	}

	/**
	 * Test of testTemplate method, of class GroupManager.
	 */
	@Test
	public void testTestTemplate() throws Exception {
		
		System.out.println("TestTemplate");
		
		//test variables
		String groupId="friends";	
		String template="Hello ${name} ${e/er} ${sex}";
		
		//setting group Mock
		AbdGroup group=new AbdGroup();
		group.setId(groupId);
		group.setTemplate(template);
		String output="";
		
		AbdContact contact=new AbdContact();
		String contactId="Test";
		contact.setId(contactId);
		contact.setFirstname("Testman");
		contact.setSex('m');
		contact.setName("Musterman");
		contact.setMail("m");
		contact.setBday(new Date(27,04,1988));
		
		String expectedOutput="Hello " + contact.getName() + " er "+ contact.getSex();
		
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(contactDAOMock.find(contactId)).andReturn(contact).times(1);
		EasyMock.expect(groupDAOMock.find(groupId)).andReturn(group).times(1);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(groupDAOMock);
		
		// testing Methodcall
		output=managerUnderTest.testTemplate(groupId, contactId);
		
		// verify
		assertEquals("Template test", expectedOutput, output);	
		EasyMock.verify(groupDAOMock);
		
	}

	/**
	 * Test of setActive method, of class GroupManager.
	 */
	@Test
	public void testSetActive() throws Exception {
		
		System.out.println("setActive");
		
		//test variables
		String groupId="family";
		boolean isActive=true;		
		AbdGroup group=new AbdGroup();
		group.setId(groupId);
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(groupDAOMock.find(groupId)).andReturn(group).times(1);
		groupDAOMock.edit(group);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(groupDAOMock);
		
		// testing Methodcall
		managerUnderTest.setActive(groupId, isActive);
		
		// verify
		EasyMock.verify(groupDAOMock);
	}

	/**
	 * Test of parseTemplate method, of class GroupManager.
	 */
	@Test
	public void testParseTemplate() throws Exception {
		
		System.out.println("parseTemplate");
		
		String template = "";
		AbdContact contact = null;
		String expResult = "";
		
		String result = managerUnderTest.parseTemplate(template, contact);
		
		assertEquals(expResult, result);
		
	}

	/**
	 * Test of parseSlashExpression method, of class GroupManager.
	 */
	@Test
	public void testParseSlashExpressionW() throws Exception {
		
		System.out.println("parseSlashExpression");
		
		String expression = "she/he";
		char sex = 'w';
		String expResult = "she";
		String result = managerUnderTest.parseSlashExpression(expression, sex);
		
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of parseSlashExpression method, of class GroupManager.
	 */
	@Test
	public void testParseSlashExpressionM() throws Exception {
		System.out.println("parseSlashExpression");
		String expression = "she/he";
		char sex = 'm';
		String expResult = "he";
		
		String result = managerUnderTest.parseSlashExpression(expression, sex);
		assertEquals(expResult, result);
	}
}
