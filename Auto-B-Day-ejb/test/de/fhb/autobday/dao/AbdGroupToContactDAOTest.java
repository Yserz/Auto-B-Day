package de.fhb.autobday.dao;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import de.fhb.autobday.data.AbdGroupToContact;

/**
 * Test of AbdGroupToContact
 * 
 * @author 
 * Christoph Ott
 */
public class AbdGroupToContactDAOTest {

	private static AbdGroupToContactFacade groupToContactDAOunderTest;
	private EntityManager emMock;
	private AbdGroupToContact abdGroupToContactEntity;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		groupToContactDAOunderTest = new AbdGroupToContactFacade();
	}

	@Before
	public void setUp() throws Exception {
		emMock = createMock(EntityManager.class);
		groupToContactDAOunderTest.setEntityManager(emMock);
		abdGroupToContactEntity = new AbdGroupToContact("friends", "11");
	}
	
	@Test
	public void testfindGroupByContact(){
		List<AbdGroupToContact> collectionExpected = new ArrayList<AbdGroupToContact>();
		collectionExpected.add(abdGroupToContactEntity);
		collectionExpected.add(new AbdGroupToContact("family", "11"));
		Query queryMock = createMock(Query.class);
		expect(queryMock.setParameter("contact", "11")).andReturn(queryMock);
		expect(queryMock.getResultList()).andReturn(collectionExpected);
		expect(emMock.createNamedQuery("Contact.findByContact")).andReturn(queryMock);
		replay(emMock);
		replay(queryMock);
		Collection<AbdGroupToContact> actual = groupToContactDAOunderTest.findGroupByContact("11");
		assertEquals(collectionExpected, actual);
		verify(emMock);
		verify(queryMock);
	}
	
	@Test
	public void testContains() {
		expect(emMock.contains(abdGroupToContactEntity)).andReturn(true);
		replay(emMock);
		assertEquals(true, groupToContactDAOunderTest.contains(abdGroupToContactEntity));
		verify(emMock);
	}

	@Test
	public void testRefresh() {
		emMock.refresh(abdGroupToContactEntity);
		replay(emMock);
		groupToContactDAOunderTest.refresh(abdGroupToContactEntity);
		verify(emMock);
	}
	
	@Test
	public void testCreate(){
		emMock.persist(abdGroupToContactEntity);
		replay(emMock);
		groupToContactDAOunderTest.create(abdGroupToContactEntity);
		verify(emMock);
	}
	
	@Test
	public void testEdit() {
		expect(emMock.merge(abdGroupToContactEntity)).andReturn(abdGroupToContactEntity);
		replay(emMock);
		groupToContactDAOunderTest.edit(abdGroupToContactEntity);
		verify(emMock);
	}

	@Test
	public void testRemove() {
		expect(emMock.merge(abdGroupToContactEntity)).andStubReturn(abdGroupToContactEntity);
		emMock.remove(abdGroupToContactEntity);
		replay(emMock);
		groupToContactDAOunderTest.remove(abdGroupToContactEntity);
		verify(emMock);
	}

	@Test
	public void testFind() {
		expect(emMock.find(AbdGroupToContact.class, abdGroupToContactEntity.getAbdGroupToContactPK())).andReturn(abdGroupToContactEntity);
		replay(emMock);
		AbdGroupToContact aktual = groupToContactDAOunderTest.find(abdGroupToContactEntity.getAbdGroupToContactPK());
		assertEquals(abdGroupToContactEntity, aktual);
		verify(emMock);
	}

	@Test
	@Ignore
	public void testFindAll(){
		CriteriaQuery criteriaQueryMock = createMock(CriteriaQuery.class);
		CriteriaBuilder criteriaBuilderMock = createMock(CriteriaBuilder.class);
		Root rootMock = createMock(Root.class);
		TypedQuery typedQueryMock = createMock(TypedQuery.class);
		List<AbdGroupToContact> resultList = new ArrayList<AbdGroupToContact>();
		resultList.add(abdGroupToContactEntity);
		resultList.add(new AbdGroupToContact("family","22"));
		
		expect(emMock.getCriteriaBuilder()).andReturn(criteriaBuilderMock);
		expect(criteriaBuilderMock.createQuery()).andReturn(criteriaQueryMock);
		expect(criteriaQueryMock.from(AbdGroupToContact.class)).andReturn(rootMock);
		expect(criteriaQueryMock.select(rootMock)).andReturn(criteriaQueryMock);
		expect(emMock.createQuery(criteriaQueryMock)).andReturn(typedQueryMock);
		expect(typedQueryMock.getResultList()).andReturn(resultList);
		assertEquals(resultList, groupToContactDAOunderTest.findAll());
		
		replay(emMock);
		replay(criteriaBuilderMock);
		replay(criteriaQueryMock);
		replay(rootMock);
		replay(typedQueryMock);
		
		groupToContactDAOunderTest.findAll();
		
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
