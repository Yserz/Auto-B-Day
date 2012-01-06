package de.fhb.autobday.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
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

import de.fhb.autobday.data.AbdAccount;

public class AbdAccountDAOTest {

	private static AbdAccountFacade accountDAOunderTest;
	private EntityManager emMock;
	private AbdAccount accountEntity;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		accountDAOunderTest = new AbdAccountFacade();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		emMock = createMock(EntityManager.class);
		accountDAOunderTest.setEntityManager(emMock);
		accountEntity = new AbdAccount(11, "maja", "1234", "type");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testContains() {
		expect(emMock.contains(accountEntity)).andReturn(true);
		replay(emMock);
		assertEquals(true, accountDAOunderTest.contains(accountEntity));
		verify(emMock);
	}

	@Test
	public void testRefresh() {
		emMock.refresh(accountEntity);
		replay(emMock);
		accountDAOunderTest.refresh(accountEntity);
		verify(emMock);
	}
	
	@Test
	public void testCreate(){
		emMock.persist(accountEntity);
		replay(emMock);
		accountDAOunderTest.create(accountEntity);
		verify(emMock);
	}
	
	@Test
	public void testEdit() {
		expect(emMock.merge(accountEntity)).andReturn(accountEntity);
		replay(emMock);
		accountDAOunderTest.edit(accountEntity);
		verify(emMock);
	}

	@Test
	public void testRemove() {
		expect(emMock.merge(accountEntity)).andStubReturn(accountEntity);
		emMock.remove(accountEntity);
		replay(emMock);
		accountDAOunderTest.remove(accountEntity);
		verify(emMock);
	}

	@Test
	public void testFind() {
		expect(emMock.find(AbdAccount.class, accountEntity.getId())).andReturn(accountEntity);
		replay(emMock);
		assertEquals(accountEntity, accountDAOunderTest.find(accountEntity.getId()));
		verify(emMock);
	}

	@Test
	@Ignore
	public void testFindAll(){
		CriteriaQuery criteriaQueryMock = createMock(CriteriaQuery.class);
		CriteriaBuilder criteriaBuilderMock = createMock(CriteriaBuilder.class);
		Root rootMock = createMock(Root.class);
		TypedQuery typedQueryMock = createMock(TypedQuery.class);
		List<AbdAccount> resultList = new ArrayList<AbdAccount>();
		resultList.add(accountEntity);
		resultList.add(new AbdAccount(22));
		expect(emMock.getCriteriaBuilder()).andReturn(criteriaBuilderMock);
		expect(criteriaBuilderMock.createQuery()).andReturn(criteriaQueryMock);
		expect(criteriaQueryMock.from(AbdAccount.class)).andReturn(rootMock);
		expect(criteriaQueryMock.select(rootMock)).andReturn(criteriaQueryMock);
		expect(emMock.createQuery(criteriaQueryMock)).andReturn(typedQueryMock);
		expect(typedQueryMock.getResultList()).andReturn(resultList);
		assertEquals(resultList, accountDAOunderTest.findAll());
		replay(emMock);
		replay(criteriaBuilderMock);
		replay(criteriaQueryMock);
		replay(rootMock);
		replay(typedQueryMock);
		
		
		accountDAOunderTest.findAll();
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
