package de.fhb.autobday.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

import de.fhb.autobday.data.AbdUser;

public class AbdUserDAOTest {

	private static AbdUserFacade userDAOunderTest;
	private EntityManager emMock;
	private AbdUser userEntity;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		userDAOunderTest = new AbdUserFacade();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		emMock = createMock(EntityManager.class);
		userDAOunderTest.setEntityManager(emMock);
		userEntity = new AbdUser(11, "maja", "1234", "salt", "maja", "biene");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testFindUserByUsername(){
		Query queryMock = createMock(Query.class);
		expect(queryMock.setParameter("username", userEntity.getUsername())).andReturn(queryMock);
		expect(queryMock.getSingleResult()).andReturn(userEntity);
		expect(emMock.createNamedQuery("AbdUser.findByUsername")).andReturn(queryMock);
		replay(emMock);
		replay(queryMock);
		AbdUser actual = userDAOunderTest.findUserByUsername(userEntity.getUsername());
		assertEquals(userEntity, actual);
		verify(emMock);
		verify(queryMock);
	}
	
	@Test(expected = NoResultException.class)
	@Ignore
	public void testFindUserByUsernameShouldThrowNoResultException() throws Exception{
		Query queryMock = createMock(Query.class);
		expect(queryMock.setParameter("username", userEntity.getUsername())).andReturn(queryMock);
		expect(queryMock.getSingleResult()).andReturn(null);
		expect(emMock.createNamedQuery("AbdUser.findByUsername")).andReturn(queryMock);
		replay(emMock);
		replay(queryMock);
		AbdUser actual = userDAOunderTest.findUserByUsername(userEntity.getUsername());
		verify(emMock);
		verify(queryMock);
	}

	@Test
	public void testRefresh() {
		emMock.refresh(userEntity);
		replay(emMock);
		userDAOunderTest.refresh(userEntity);
		verify(emMock);
	}
	
	@Test
	public void testCreate(){
		emMock.persist(userEntity);
		replay(emMock);
		userDAOunderTest.create(userEntity);
		verify(emMock);
	}
	
	@Test
	public void testEdit() {
		expect(emMock.merge(userEntity)).andReturn(userEntity);
		replay(emMock);
		userDAOunderTest.edit(userEntity);
		verify(emMock);
	}

	@Test
	public void testRemove() {
		expect(emMock.merge(userEntity)).andStubReturn(userEntity);
		emMock.remove(userEntity);
		replay(emMock);
		userDAOunderTest.remove(userEntity);
		verify(emMock);
	}

	@Test
	public void testFind() {
		expect(emMock.find(AbdUser.class, userEntity.getId())).andReturn(userEntity);
		replay(emMock);
		assertEquals(userEntity, userDAOunderTest.find(userEntity.getId()));
		verify(emMock);
	}

	@Test
	@Ignore
	public void testFindAll(){
		CriteriaQuery criteriaQueryMock = createMock(CriteriaQuery.class);
		CriteriaBuilder criteriaBuilderMock = createMock(CriteriaBuilder.class);
		Root rootMock = createMock(Root.class);
		TypedQuery typedQueryMock = createMock(TypedQuery.class);
		List<AbdUser> resultList = new ArrayList<AbdUser>();
		resultList.add(userEntity);
		resultList.add(new AbdUser(22));
		expect(emMock.getCriteriaBuilder()).andReturn(criteriaBuilderMock);
		expect(criteriaBuilderMock.createQuery()).andReturn(criteriaQueryMock);
		expect(criteriaQueryMock.from(AbdUser.class)).andReturn(rootMock);
		expect(criteriaQueryMock.select(rootMock)).andReturn(criteriaQueryMock);
		expect(emMock.createQuery(criteriaQueryMock)).andReturn(typedQueryMock);
		expect(typedQueryMock.getResultList()).andReturn(resultList);
		assertEquals(resultList, userDAOunderTest.findAll());
		replay(emMock);
		replay(criteriaBuilderMock);
		replay(criteriaQueryMock);
		replay(rootMock);
		replay(typedQueryMock);
		
		
		userDAOunderTest.findAll();
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
