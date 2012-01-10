package de.fhb.autobday.dao;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import de.fhb.autobday.data.AbdGroup;

/**
 * Test of AbdGroupDAO
 * 
 * @author 
 * Christoph Ott
 */
public class AbdGroupDAOTest {

	private static AbdGroupFacade groupDAOunderTest;
	private EntityManager emMock;
	private AbdGroup groupEntity;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		groupDAOunderTest = new AbdGroupFacade();
	}

	@Before
	public void setUp() throws Exception {
		emMock = createMock(EntityManager.class);
		groupDAOunderTest.setEntityManager(emMock);
		groupEntity = new AbdGroup("friends_id", "friends", "abc", true);
	}

	@Test
	public void testContains() {
		expect(emMock.contains(groupEntity)).andReturn(true);
		replay(emMock);
		assertEquals(true, groupDAOunderTest.contains(groupEntity));
		verify(emMock);
	}

	@Test
	public void testRefresh() {
		emMock.refresh(groupEntity);
		replay(emMock);
		groupDAOunderTest.refresh(groupEntity);
		verify(emMock);
	}
	
	@Test
	public void testCreate(){
		emMock.persist(groupEntity);
		replay(emMock);
		groupDAOunderTest.create(groupEntity);
		verify(emMock);
	}
	
	@Test
	public void testEdit() {
		expect(emMock.merge(groupEntity)).andReturn(groupEntity);
		replay(emMock);
		groupDAOunderTest.edit(groupEntity);
		verify(emMock);
	}

	@Test
	public void testRemove() {
		expect(emMock.merge(groupEntity)).andStubReturn(groupEntity);
		emMock.remove(groupEntity);
		replay(emMock);
		groupDAOunderTest.remove(groupEntity);
		verify(emMock);
	}

	@Test
	public void testFind() {
		expect(emMock.find(AbdGroup.class, groupEntity.getId())).andReturn(groupEntity);
		replay(emMock);
		assertEquals(groupEntity, groupDAOunderTest.find(groupEntity.getId()));
		verify(emMock);
	}

	@Test
	@Ignore
	public void testFindAll(){
		CriteriaQuery criteriaQueryMock = createMock(CriteriaQuery.class);
		CriteriaBuilder criteriaBuilderMock = createMock(CriteriaBuilder.class);
		Root rootMock = createMock(Root.class);
		TypedQuery typedQueryMock = createMock(TypedQuery.class);
		List<AbdGroup> resultList = new ArrayList<AbdGroup>();
		resultList.add(groupEntity);
		resultList.add(new AbdGroup("family"));
		
		expect(emMock.getCriteriaBuilder()).andReturn(criteriaBuilderMock);
		expect(criteriaBuilderMock.createQuery()).andReturn(criteriaQueryMock);
		expect(criteriaQueryMock.from(AbdGroup.class)).andReturn(rootMock);
		expect(criteriaQueryMock.select(rootMock)).andReturn(criteriaQueryMock);
		expect(emMock.createQuery(criteriaQueryMock)).andReturn(typedQueryMock);
		expect(typedQueryMock.getResultList()).andReturn(resultList);
		assertEquals(resultList, groupDAOunderTest.findAll());
		
		replay(emMock);
		replay(criteriaBuilderMock);
		replay(criteriaQueryMock);
		replay(rootMock);
		replay(typedQueryMock);
		
		
		groupDAOunderTest.findAll();
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
