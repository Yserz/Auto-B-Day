package de.fhb.autobday.manager.group;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.stvconsultants.easygloss.javaee.JavaEEGloss;

import de.fhb.autobday.dao.AbdContactFacade;
import de.fhb.autobday.dao.AbdGroupFacade;
import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.data.AbdGroup;
import de.fhb.autobday.data.AbdGroupToContact;
import de.fhb.autobday.exception.contact.ContactNotFoundException;
import de.fhb.autobday.exception.contact.NoContactGivenException;
import de.fhb.autobday.exception.group.GroupNotFoundException;
import de.fhb.autobday.exception.group.NoGroupGivenException;

/**
 * Test the GroupManager
 * 
 * @author 
 * Andy Klay <klay@fh-brandenburg.de>
 * Christoph Ott
 */
public class GroupManagerTest {
	
	private JavaEEGloss gloss;
	
	private GroupManager managerUnderTest;
	
	private AbdGroupFacade groupDAOMock;
	private AbdContactFacade contactDAOMock;
	
	private AbdContact testContact;
	
	public GroupManagerTest() {
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
		
		//Setting up a Contact
		testContact=new AbdContact();
		testContact.setId("Test");
		testContact.setFirstname("Testman");
		testContact.setSex('m');
		testContact.setName("Musterman");
		testContact.setMail("m");
		testContact.setBday(new Date(27,04,1988));
	}

	/**
	 * Test of getGroup method, of class GroupManager.
	 */
	@Test
	public void testGetGroup() throws Exception {
		
		System.out.println("testGetGroup");

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
	 * Test the setTemplate Method when a Group given
	 */
	@Test
	public void testSetTemplateWithAGivenGroup() throws Exception{
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
		managerUnderTest.setTemplate(group, template);
		
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
		
		String expectedOutput="Hello " + testContact.getName() + " " + testContact.getFirstname() +  " " + testContact.getId() + " " + testContact.getMail() + " " + testContact.getBday() + " er "+ testContact.getSex();
		
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(contactDAOMock.find(testContact.getId())).andReturn(testContact).times(1);
		EasyMock.expect(groupDAOMock.find(groupId)).andReturn(group).times(1);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(groupDAOMock);
		EasyMock.replay(contactDAOMock);
		
		//call method to test
		output=managerUnderTest.testTemplate(groupId, testContact.getId());
		
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
		
		String expectedOutput="Hello " + testContact.getName() + " er "+ testContact.getSex();
		
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(contactDAOMock.find(testContact.getId())).andReturn(testContact).times(1);
		EasyMock.expect(groupDAOMock.find(groupId)).andReturn(null).times(1);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(groupDAOMock);
		EasyMock.replay(contactDAOMock);
		
		//call method to test
		output=managerUnderTest.testTemplate(groupId, testContact.getId());
		
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
		
		String expectedOutput="Hello " + testContact.getName() + " er "+ testContact.getSex();
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(contactDAOMock.find(testContact.getId())).andReturn(null).times(1);
		EasyMock.expect(groupDAOMock.find(groupId)).andReturn(group).times(1);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(groupDAOMock);
		EasyMock.replay(contactDAOMock);
		
		//call method to test
		output=managerUnderTest.testTemplate(groupId, testContact.getId());
		
		// verify
		assertEquals("Template test", expectedOutput, output);	
		EasyMock.verify(groupDAOMock);
		
	}
	
	/**
	 * Test of setActive method when a Group given
	 */
	@Test
	public void testSetActiveWithAGivenGroup() throws Exception {
		
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
		managerUnderTest.setActive(group, isActive);
		
		// verify
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
		String template="Hello ${name} ${e/er} ${sex}";
		
		//prepare expected variables
		String expResult="Hello " + testContact.getName() + " er "+ testContact.getSex();
		
		//call method to test
		String result = managerUnderTest.parseTemplate(template, testContact);
		
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
	 * Test of getAllContactsFromGroup method when a Group is given.
	 */
	@Test
	public void testGetAllContactsFromGroupWithAGivenGroup() throws Exception {
		System.out.println("testGetAllContactsFromGroup");
		
		//prepare test variables
		AbdContact contactIch = new AbdContact("1");
		AbdContact contactDu = new AbdContact("2");
		AbdGroupToContact gContactIch = new AbdGroupToContact("meineGruppe", "ich");
		AbdGroupToContact gContactDu = new AbdGroupToContact("meineGruppe", "du");
		gContactIch.setAbdContact(contactIch);
		gContactDu.setAbdContact(contactDu);
		
		ArrayList<AbdContact> outputCollection = new ArrayList<AbdContact>();
		outputCollection.add(contactIch);
		outputCollection.add(contactDu);
		
		ArrayList<AbdGroupToContact> abdGroupToContactCollection = new ArrayList<AbdGroupToContact>();
		abdGroupToContactCollection.add(gContactIch);
		abdGroupToContactCollection.add(gContactDu);
		
		AbdGroup group = new AbdGroup("2");
		group.setAbdGroupToContactCollection(abdGroupToContactCollection);
		

		EasyMock.expect(groupDAOMock.find(group.getId())).andStubReturn(group);
		
		// Setup is finished need to activate the mock
		EasyMock.replay(groupDAOMock);
		
		//call method to test
		assertEquals(outputCollection, managerUnderTest.getAllContactsFromGroup(group));
		
		// verify
		EasyMock.verify(groupDAOMock);
	}
	
	
	/**
	 * Test of getAllContactsFromGroup method when a Group is given.
	 * This test provokes a NoGroupGivenException!
	 */
	@Test(expected = NoGroupGivenException.class)
	public void testGetAllContactsFromGroupWithAGivenGroupThrowNoGroupGivenException() throws Exception {
		
		System.out.println("testGetAllContactsFromGroupWithAGivenGroupThrowNoGroupGivenException");
		
		//prepare test variables
		AbdGroup group = null;
		
		//call method to test
		managerUnderTest.getAllContactsFromGroup(group);
	}
	
	/**
	 * Test of getAllContactsFromGroup method, of class GroupManager.
	 */
	@Test
	public void testGetAllContactsFromGroup() throws Exception {
		System.out.println("testGetAllContactsFromGroup");
		
		//prepare test variables
		AbdContact contactIch = new AbdContact("1");
		AbdContact contactDu = new AbdContact("2");
		AbdGroupToContact gContactIch = new AbdGroupToContact("meineGruppe", "ich");
		AbdGroupToContact gContactDu = new AbdGroupToContact("meineGruppe", "du");
		gContactIch.setAbdContact(contactIch);
		gContactDu.setAbdContact(contactDu);
		
		ArrayList<AbdContact> outputCollection = new ArrayList<AbdContact>();
		outputCollection.add(contactIch);
		outputCollection.add(contactDu);
		
		ArrayList<AbdGroupToContact> abdGroupToContactCollection = new ArrayList<AbdGroupToContact>();
		abdGroupToContactCollection.add(gContactIch);
		abdGroupToContactCollection.add(gContactDu);
		
		AbdGroup group = new AbdGroup("2");
		group.setAbdGroupToContactCollection(abdGroupToContactCollection);
		
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(groupDAOMock.find(group.getId())).andStubReturn(group);

		// Setup is finished need to activate the mock
		EasyMock.replay(groupDAOMock);
		
		//call method to test
		assertEquals(outputCollection, managerUnderTest.getAllContactsFromGroup(group.getId()));
		
		// verify
		EasyMock.verify(groupDAOMock);
	}
	
	/**
	 * Test of getAllContactsFromGroup method, of class GroupManager.
	 */
	@Test(expected = GroupNotFoundException.class)
	public void testGetAllContactsFromGroupShouldThrowGroupNotFoundException() throws Exception {
		System.out.println("testGetAllContactsFromGroupShouldThrowGroupNotFoundException");

		//prepare test variables
		AbdGroup group = new AbdGroup("2");

		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(groupDAOMock.find(group.getId())).andStubReturn(null);

		// Setup is finished need to activate the mock
		EasyMock.replay(groupDAOMock);
		
		//call method to test
		managerUnderTest.getAllContactsFromGroup(group.getId());
		
		// verify
		EasyMock.verify(groupDAOMock);
	}
}
