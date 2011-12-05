/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.autobday.manager.group;

import de.fhb.autobday.data.AbdContact;
import de.fhb.autobday.data.AbdGroup;
import javax.ejb.embeddable.EJBContainer;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MacYser
 */
public class GroupManagerTest {
	private EJBContainer container;
	
	private AbdGroup mock;
	private GroupManager managerTest;
	
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
		container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
		
		mock = EasyMock.createMock(AbdGroup.class);
		managerTest = new GroupManager();
	}
	
	@After
	public void tearDown() {
		container.close();
	}

	/**
	 * Test of getGroup method, of class GroupManager.
	 */
	@Test
	public void testGetGroup() throws Exception {
		System.out.println("getGroup");
		int groupid = 0;
		
		GroupManagerLocal instance = (GroupManagerLocal)container.getContext().lookup("java:global/classes/GroupManager");
		AbdGroup expResult = null;
		AbdGroup result = instance.getGroup(groupid);
		assertEquals(expResult, result);
		
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setTemplate method, of class GroupManager.
	 */
	@Test
	public void testSetTemplate() throws Exception {
		System.out.println("setTemplate");
		GroupManagerLocal instance = (GroupManagerLocal)container.getContext().lookup("java:global/classes/GroupManager");
		instance.setTemplate(0, null);
		
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getTemplate method, of class GroupManager.
	 */
	@Test
	public void testGetTemplate() throws Exception {
		System.out.println("getTemplate");
		GroupManagerLocal instance = (GroupManagerLocal)container.getContext().lookup("java:global/classes/GroupManager");
		instance.getTemplate(0);
		
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of testTemplate method, of class GroupManager.
	 */
	@Test
	public void testTestTemplate() throws Exception {
		System.out.println("testTemplate");
		GroupManagerLocal instance = (GroupManagerLocal)container.getContext().lookup("java:global/classes/GroupManager");
		instance.testTemplate(0, null);
		
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setActive method, of class GroupManager.
	 */
	@Test
	public void testSetActive() throws Exception {
		System.out.println("setActive");
		GroupManagerLocal instance = (GroupManagerLocal)container.getContext().lookup("java:global/classes/GroupManager");
		managerTest.setActive(1, false);
		boolean expResult = false;
		boolean result = mock.getActive();
		EasyMock.expect(mock.getActive()).andReturn(false).times(1);
		EasyMock.replay(mock);
		//assertEquals(false, result);
		EasyMock.verify(mock);
		//
	}

	/**
	 * Test of parseTemplate method, of class GroupManager.
	 */
	@Test
	public void testParseTemplate() throws Exception {
		System.out.println("parseTemplate");
		String template = "";
		AbdContact contact = null;
		GroupManagerLocal instance = (GroupManagerLocal)container.getContext().lookup("java:global/classes/GroupManager");
		String expResult = "";
		String result = instance.parseTemplate(template, contact);
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
		
		GroupManagerLocal instance = (GroupManagerLocal)container.getContext().lookup("java:global/classes/GroupManager");
		String expResult = "she";
		
		String result = instance.parseSlashExpression(expression, sex);
		
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
		GroupManagerLocal instance = (GroupManagerLocal)container.getContext().lookup("java:global/classes/GroupManager");
		String expResult = "he";
		String result = instance.parseSlashExpression(expression, sex);
		assertEquals(expResult, result);
		//
	}
}
