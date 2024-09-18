package com.project.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.entities.transplant;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;


@Repository
public class transplantDAOImpl implements transplantDAO{

private EntityManager entityManager;
	
	@Autowired
	public transplantDAOImpl(EntityManager theEntityManager) {
		this.entityManager = theEntityManager;
	}
	
	@Override
	public void addTransplant(transplant transplant) {
		
		entityManager.persist(transplant);
		
	}

	@Override
	public List<transplant> getAllTransplant() {
		
		TypedQuery<transplant> theQuery = entityManager.createQuery("from transplant", transplant.class);
		
		List<transplant> transplants = theQuery.getResultList();
				
		return transplants;
	}

	@Override
	public transplant getTransplantById(int transId) {
	
		transplant transplant = entityManager.find(transplant.class, transId);
		
		return transplant;
	}

	@Override
	public boolean updateTransplantById(int transId, transplant transplant) {
		
		transplant existTrans = entityManager.find(transplant.class, transId);
		
		
		if(existTrans != null)
		{
			entityManager.merge(transplant);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteTransplantById(int transId) {
	
		transplant transplant = entityManager.find(transplant.class, transId);
		if(transplant != null)
		{
			boolean res = transplant.isSuccess();
			if(res != true)
			{
				transplant.getOrgan().setAvailable(true);
			}
			
//			entityManager.remove(transplant);
			
			int rowsAffected = entityManager.createQuery("DELETE FROM transplant t WHERE t.transId = :transId")
                    .setParameter("transId", transId)
                    .executeUpdate();
			return true;
		}
		
		return false;
	}

	@Override
	public boolean deleteAllTransplant() {
	
		int rows = entityManager.createQuery("DELETE FROM transplant").executeUpdate();
		if(rows != 0)
		{
			return true;
		}
		return false;
	}

	@Override
	public long getSuccTransNo(String doctorName) {
		
		String Q = "SELECT COUNT(t) FROM transplant t WHERE t.Doctor.doctorName = :doctorName AND t.success = true";
        Query query = entityManager.createQuery(Q);
        query.setParameter("doctorName", doctorName);
		
       long res = (long)query.getSingleResult();
        return res;
	}

	@Override
	public List<Object[]> getPatientUnderDoc(String doctorName) {
		
		String Q = "SELECT DISTINCT p.patientName, t.success FROM transplant t JOIN t.Doctor d JOIN t.Patient p WHERE d.doctorName = :doctorName";
        Query query = entityManager.createQuery(Q);
        query.setParameter("doctorName", doctorName);
        
        List<Object[]> resultList = query.getResultList();
        
		return resultList;
	}

}
