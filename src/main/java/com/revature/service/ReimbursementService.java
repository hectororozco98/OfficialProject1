package com.revature.service;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import com.revature.dao.IReimbursementDao;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementTypeEnum;

public class ReimbursementService {

	private IReimbursementDao rdao;

	// For Review
	public ReimbursementService(IReimbursementDao rdao) {

		this.rdao = rdao;
	}

	public Reimbursement createReimbursement(Reimbursement r) {

		// Might have to make some type of check to see if there is a reimbursement with
		// the same
		// information in the DB already\
		try {

			int pk = rdao.insert(r);
			
			r.setId(pk);
		} catch (ConstraintViolationException e) {
			e.printStackTrace();
		}

		return r;
	}
	
	public Reimbursement findById(int id) {
		
		return rdao.findById(id);
	}

	public List<Reimbursement> getUserReimbursements(int id) {

		return rdao.findByAuthorId(id);
	}
	
	public List<Reimbursement> findReimbursementsByType(ReimbursementTypeEnum type) {
		
		return rdao.findByType(type);
	}
	
	public List<Reimbursement> findReimbursementsByStatus(ReimbursementStatus status) {
		
		return rdao.findByStatus(status);
	}

	public List<Reimbursement> getAll() {

		return rdao.findAll();
	}
	
	public boolean updateReimbursement(Reimbursement r) {
		
		if (rdao.update(r)) {
			return true;
		}
		return false;
	}
	
	public boolean deleteReimbursement(Reimbursement r) {
		
		if (rdao.delete(r)) {
			return true;
		}
		return false;
		
	}

}
