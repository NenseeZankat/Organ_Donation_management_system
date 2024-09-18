package com.project.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.entities.donor;
import com.project.entities.organs;
import com.project.entities.patient;
import com.project.entities.transplant;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Repository
public class organDAOImpl implements organDAO{

	
private EntityManager entityManager;
	
	@Autowired
	public organDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public void addOrgan(organs organ) {
		
		entityManager.persist(organ);
	}

	@Override
	public List<organs> getAllOrgan() {
		
		TypedQuery<organs> theQuery = entityManager.createQuery("from organs", organs.class);
		
		List<organs> organs = theQuery.getResultList();
				
		return organs;
	}

	@Override
	public organs getOrganById(int organId) {
		
		organs organ = entityManager.find(organs.class, organId);
		
		return organ;
	}

	@Override
	public boolean updateOrganById(int organId, organs organ) {
		
		
		
		organs existorgan = entityManager.find(organs.class, organId);
		
		if(existorgan != null)
		{
			entityManager.merge(organ);
			return true;
		}
		return false;
	}

	@Override
	public String deleteOrganById(int organId) {
		
		organs organ = entityManager.find(organs.class, organId);
		transplant transplant = organ.getTransplant();
		
		
		if(transplant != null && organ != null)
		{
			String patientName = transplant.getPatient().getPatientName();
			entityManager.remove(organ);
			return patientName;
		}
		else if(organ != null)
		{
			entityManager.remove(organ);
		}
		
		return "";
	}

	@Override
	public boolean deleteAllOrgan() {
		
		int rows = entityManager.createQuery("DELETE FROM organs").executeUpdate();
		if(rows != 0)
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean checkForOrgan(String organName, int organId) {
		String Q = "SELECT o.available FROM organs o WHERE o.organName = :name AND o.organId = :id";
        Query query = entityManager.createQuery(Q);
        query.setParameter("name", organName);
        query.setParameter("id", organId);
		
        boolean res = (boolean) query.getSingleResult();
        return res;
	}

	@Override
	public List<patient> findPatientsNameByOrgan(String organName) {
		String Q = "SELECT p FROM organs o, patient p WHERE o.organName = :organName AND o.organName = p.organRequired";
		Query query = entityManager.createQuery(Q);
        query.setParameter("organName", organName);
        
        List<patient> res = query.getResultList();
        return res;
	}

	@Override
	public List<donor> findDonorsNameByOrgan(String organName) {
		
		String Q = "SELECT d FROM donor d JOIN d.organsList o WHERE o.organName = :organName";
        Query query = entityManager.createQuery(Q);
        query.setParameter("organName", organName);
        
        List<donor> res = query.getResultList();
        return res;
	}

	@Override
	public List<Object[]> getAvailableOrgans() {
		
		String Q = "SELECT o.organName, COUNT(o) FROM organs o WHERE o.available = true GROUP BY o.organName";

		Query query = entityManager.createQuery(Q);
		
		List<Object[]> res = query.getResultList();
		return res;
	}

}
