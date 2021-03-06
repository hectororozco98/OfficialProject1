package com.revature.tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.hibernate.exception.ConstraintViolationException;

import com.revature.dao.ReimbursementDaoImpl;
import com.revature.dao.UserDaoImpl;
import com.revature.models.Reimbursement;
import com.revature.service.ReimbursementService;
import com.revature.service.UserService;

public class ReimbursementServiceTests {
	
	private ReimbursementService rserv;
	
	private ReimbursementDaoImpl mockDao;
	
	private Reimbursement dummyReimbursement;
	
	@Before
	public void setup() {
		mockDao = mock(ReimbursementDaoImpl.class);
		
		rserv = new ReimbursementService(mockDao);
		
		
	}
	
	@After
	public void tearDown() {
		rserv = null;
		mockDao = null;
		dummyReimbursement = null;
	}
	/**
	 * ReimbursementService createReimbursement
	 */
	
	@Test
	public void testCreateReimbursement_Success() {
		dummyReimbursement = new Reimbursement(50.0, Instant.now().truncatedTo(ChronoUnit.SECONDS), null, "test", null, null, null, null);
		
		int newPk = 6;
		
		when(mockDao.insert(dummyReimbursement)).thenReturn(newPk);
		
		int actualPk = rserv.createReimbursement(dummyReimbursement).getId();
		int expectedPk = 6;
		
		assertEquals(actualPk, expectedPk);
	}
	
	@Test
	public void testCreateReimbursement_returnNewPkAsId() {
		dummyReimbursement = new Reimbursement(0, 50.0, Instant.now().truncatedTo(ChronoUnit.SECONDS), null, null, null, null, null, null);
		
		Random r = new Random();
		
		int expectedId = r.nextInt(100);
		
		when(mockDao.insert(dummyReimbursement)).thenReturn(expectedId);
		
		int actualId = rserv.createReimbursement(dummyReimbursement).getId();
		
		assertEquals(expectedId, actualId);
	}
	
	/**
	 * ReimbursementService getUserReimbursement
	 */
	
	/**
	 * ReimbursementService getAll
	 */
}
