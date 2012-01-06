package de.fhb.autobday.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import static org.easymock.EasyMock.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.powermock.api.easymock.PowerMock;

import de.fhb.autobday.data.AbdContact;

public class AbdContactDAOTest {

	private static AbdContactFacade contactDAOunderTest;
	private EntityManager emMock;
	private AbdContact contactEntity;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		contactDAOunderTest = new AbdContactFacade();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		emMock = createMock(EntityManager.class);
		contactDAOunderTest.setEntityManager(emMock);
		contactEntity = new AbdContact("11", "maja@haus.com", new Date(100, 5, 10), "hashid");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testFindContactByBday(){
		Date bday = new Date(100, 5, 10);
		List<AbdContact> collectionExpected = new ArrayList<AbdContact>();
		collectionExpected.add(contactEntity);
		collectionExpected.add(new AbdContact("11"));
		Query queryMock = createMock(Query.class);
		expect(queryMock.setParameter("bday", bday)).andReturn(queryMock);
		expect(queryMock.getResultList()).andReturn(collectionExpected);
		expect(emMock.createNamedQuery("Contact.findByBday")).andReturn(queryMock);
		replay(emMock);
		replay(queryMock);
		Collection<AbdContact> actual = contactDAOunderTest.findContactByBday(bday);
		assertEquals(collectionExpected, actual);
		verify(emMock);
		verify(queryMock);
	}
	
	@Test
	public void testContains() {
		expect(emMock.contains(contactEntity)).andReturn(true);
		replay(emMock);
		assertEquals(true, contactDAOunderTest.contains(contactEntity));
		verify(emMock);
	}

	@Test
	public void testRefresh() {
		emMock.refresh(contactEntity);
		replay(emMock);
		contactDAOunderTest.refresh(contactEntity);
		verify(emMock);
	}
	
	@Test
	public void testCreate(){
		emMock.persist(contactEntity);
		replay(emMock);
		contactDAOunderTest.create(contactEntity);
		verify(emMock);
	}
	
	@Test
	public void testEdit() {
		expect(emMock.merge(contactEntity)).andReturn(contactEntity);
		replay(emMock);
		contactDAOunderTest.edit(contactEntity);
		verify(emMock);
	}

	@Test
	public void testRemove() {
		expect(emMock.merge(contactEntity)).andStubReturn(contactEntity);
		emMock.remove(contactEntity);
		replay(emMock);
		contactDAOunderTest.remove(contactEntity);
		verify(emMock);
	}

	@Test
	public void testFind() {
		expect(emMock.find(AbdContact.class, contactEntity.getId())).andReturn(contactEntity);
		replay(emMock);
		AbdContact aktual = contactDAOunderTest.find(contactEntity.getId());
		assertEquals(contactEntity, aktual);
		verify(emMock);
	}

	@Test
	@Ignore
	public void testFindAll(){
		CriteriaQuery criteriaQueryMock = createMock(CriteriaQuery.class);
		CriteriaBuilder criteriaBuilderMock = createMock(CriteriaBuilder.class);
		Root rootMock = createMock(Root.class);
		TypedQuery typedQueryMock = createMock(TypedQuery.class);
		List<AbdContact> resultList = new ArrayList<AbdContact>();
		resultList.add(contactEntity);
		resultList.add(new AbdContact("22"));
		expect(emMock.getCriteriaBuilder()).andReturn(criteriaBuilderMock);
		expect(criteriaBuilderMock.createQuery()).andReturn(criteriaQueryMock);
		expect(criteriaQueryMock.from(AbdContact.class)).andReturn(rootMock);
		expect(criteriaQueryMock.select(rootMock)).andReturn(criteriaQueryMock);
		expect(emMock.createQuery(criteriaQueryMock)).andReturn(typedQueryMock);
		expect(typedQueryMock.getResultList()).andReturn(resultList);
		assertEquals(resultList, contactDAOunderTest.findAll());
		replay(emMock);
		replay(criteriaBuilderMock);
		replay(criteriaQueryMock);
		replay(rootMock);
		replay(typedQueryMock);
		
		
		contactDAOunderTest.findAll();
		verify(criteriaBuilderMock);
		verify(criteriaQueryMock);
		verify(rootMock);
		verify(typedQueryMock);
		verify(emMock);
		

	}

	@Test
	@Ignore
	public void testFindRange() {

	}

	@Test
	@Ignore
	public void testCount() {

	}

}
