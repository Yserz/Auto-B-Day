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
import de.fhb.autobday.exception.contact.ContactNotFoundException;
import de.fhb.autobday.exception.contact.NoContactGivenException;
import de.fhb.autobday.exception.group.GroupNotFoundException;

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
		
		//call method to test
		managerUnderTest.getGroup(groupId);
		
		// verify		
		EasyMock.verify(groupDAOMock);
	}
	
	/**
	 * Test fail of getGroup method, of class GroupManager.
	 * This test provokes a GroupNotFoundException!
	 */
	@Test(expected = GroupNotFoundException.class)
	public void testGetGroupShouldThrowGroupNotFoundException() throws Exception {
		
		System.out.println("testGetGroupShouldThrowGroupNotFoundException");

		//test variables
		String groupId="friends";	
		String template="template";
		AbdGroup group=new AbdGroup();
		group.setId(groupId);
		group.setTemplate(template);
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(groupDAOMock.find(groupId)).andReturn(null).times(1);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(groupDAOMock);
		
		//call method to test
		managerUnderTest.getGroup(groupId);
		
		// verify		
		EasyMock.verify(groupDAOMock);
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
		
		//call method to test
		managerUnderTest.setTemplate(groupId, template);
		
		// verify
		EasyMock.verify(groupDAOMock);
	}
	
	/**
	 * Test fail of setTemplate method, of class GroupManager.
	 * This test provokes a GroupNotFoundException!
	 */
	@Test(expected = GroupNotFoundException.class)
	public void testSetTemplateShouldThrowGroupNotFoundException() throws Exception {
		
		System.out.println("testSetTemplateShouldThrowGroupNotFoundException");

		//test variables
		String groupId="friends";	
		String template="template";
		AbdGroup group=new AbdGroup();
		group.setId(groupId);
		group.setTemplate(template);
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(groupDAOMock.find(groupId)).andReturn(null).times(1);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(groupDAOMock);
		
		//call method to test
		managerUnderTest.setTemplate(groupId, template);
		
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
		
		//call method to test
		output=managerUnderTest.getTemplate(groupId);
		
		// verify
		assertEquals("Template test", template, output);	
		EasyMock.verify(groupDAOMock);
	}
	
	/**
	 * Test fail of getTemplate method, of class GroupManager.
	 * This test provokes a GroupNotFoundException!
	 */
	@Test(expected = GroupNotFoundException.class)
	public void testGetTemplateShouldThrowGroupNotFoundException() throws Exception {
		
		System.out.println("testGetTemplateShouldThrowGroupNotFoundException");
		
		//test variables
		String groupId="friends";	
		String template="template";
		AbdGroup group=new AbdGroup();
		group.setId(groupId);
		group.setTemplate(template);
		String output="";
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(groupDAOMock.find(groupId)).andReturn(null).times(1);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(groupDAOMock);
		
		//call method to test
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
		String template="Hello ${name} ${firstname} ${id} ${mail} ${bday} ${e/er} ${sex}";
		
		//setting group
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
		
		String expectedOutput="Hello " + contact.getName() + " " + contact.getFirstname() +  " " + contact.getId() + " " + contact.getMail() + " " + contact.getBday() + " er "+ contact.getSex();
		
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(contactDAOMock.find(contactId)).andReturn(contact).times(1);
		EasyMock.expect(groupDAOMock.find(groupId)).andReturn(group).times(1);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(groupDAOMock);
		EasyMock.replay(contactDAOMock);
		
		//call method to test
		output=managerUnderTest.testTemplate(groupId, contactId);
		
		// verify
		assertEquals("Template test", expectedOutput, output);	
		EasyMock.verify(groupDAOMock);
		
	}
	
	/**
	 * Test fail of testTemplate method, of class GroupManager.
	 * This test provokes a GroupNotFoundException!
	 */
	@Test(expected = GroupNotFoundException.class)
	public void testTestTemplateShouldThrowGroupNotFoundException() throws Exception {
		
		System.out.println("TestTemplateShouldThrowGroupNotFoundException");
		
		//test variables
		String groupId="friends";	
		String template="Hello ${name} ${e/er} ${sex}";
		
		//setting group
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
		EasyMock.expect(groupDAOMock.find(groupId)).andReturn(null).times(1);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(groupDAOMock);
		EasyMock.replay(contactDAOMock);
		
		//call method to test
		output=managerUnderTest.testTemplate(groupId, contactId);
		
		// verify
		assertEquals("Template test", expectedOutput, output);	
		EasyMock.verify(groupDAOMock);
		
	}
	
	/**
	 * Test fail of testTemplate method, of class GroupManager.
	 * This test provokes a ContactNotFoundException!
	 */
	@Test(expected = ContactNotFoundException.class)
	public void testTestTemplateShouldThrowContactNotFoundException() throws Exception {
		
		System.out.println("testTestTemplateShouldThrowContactNotFoundException");
		
		//test variables
		String groupId="friends";	
		String template="Hello ${name} ${e/er} ${sex}";
		
		//setting group
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
		EasyMock.expect(contactDAOMock.find(contactId)).andReturn(null).times(1);
		EasyMock.expect(groupDAOMock.find(groupId)).andReturn(group).times(1);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(groupDAOMock);
		EasyMock.replay(contactDAOMock);
		
		//call method to test
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
		
		//call method to test
		managerUnderTest.setActive(groupId, isActive);
		
		// verify
		EasyMock.verify(groupDAOMock);
	}

	/**
	 * Test fail of setActive method, of class GroupManager.
	 * This test provokes a GroupNotFoundException!
	 */
	@Test(expected = GroupNotFoundException.class)
	public void testSetActiveShouldThrowContactNotFoundException() throws Exception {
		
		System.out.println("setActive");
		
		//test variables
		String groupId="family";
		boolean isActive=true;		
		AbdGroup group=new AbdGroup();
		group.setId(groupId);
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(groupDAOMock.find(groupId)).andReturn(null).times(1);
		groupDAOMock.edit(group);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(groupDAOMock);
		
		//call method to test
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
		
		//test variables
		String groupId="friends";	
		String template="Hello ${name} ${e/er} ${sex}";
		
		//prepare a contact object
		AbdContact contact=new AbdContact();
		String contactId="Test";
		contact.setId(contactId);
		contact.setFirstname("Testman");
		contact.setSex('m');
		contact.setName("Musterman");
		contact.setMail("m");
		contact.setBday(new Date(27,04,1988));
		
		//prepare expected variables
		String expResult="Hello " + contact.getName() + " er "+ contact.getSex();
		
		//call method to test
		String result = managerUnderTest.parseTemplate(template, contact);
		
		// verify
		assertEquals(expResult, result);
	}
	
	/**
	 * Test fail of parseTemplate method, of class GroupManager.
	 * This test provokes a NoContactGivenException!
	 */
	@Test(expected = NoContactGivenException.class)
	public void testParseTemplateShouldThrowNoContactGivenException() throws Exception {
		
		System.out.println("testParseTemplateShouldThrowNoContactGivenException");
		
		//prepare
		String template="Hello ${name} ${e/er} ${sex}";
		AbdContact contact=null;
		
		//call method to test
		managerUnderTest.parseTemplate(template, contact);
	}

	/**
	 * Test of parseSlashExpression method, of class GroupManager.
	 */
	@Test
	public void testParseSlashExpressionW() throws Exception {
		System.out.println("parseSlashExpression");
		
		//prepare test variables
		String expression = "she/he";
		char sex = 'w';
		String expResult = "she";
		String result = null;
		
		//call method to test
		result = managerUnderTest.parseSlashExpression(expression, sex);
		
		// verify
		assertEquals(expResult, result);
	}
	
	/**
	 * Test of parseSlashExpression method, of class GroupManager.
	 */
	@Test
	public void testParseSlashExpressionM() throws Exception {
		System.out.println("parseSlashExpression");
		
		//prepare test variables
		String expression = "she/he";
		char sex = 'm';
		String expResult = "he";
		String result=null;
		
		//call method to test
		result = managerUnderTest.parseSlashExpression(expression, sex);
		
		// verify
		assertEquals(expResult, result);
	}
}
