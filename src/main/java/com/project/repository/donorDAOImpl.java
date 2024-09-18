package com.project.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.entities.donor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;


@Repository
public class donorDAOImpl implements donorDAO {

	private EntityManager entityManager;
	
	@Autowired
	public donorDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public void addDonor(donor donor) {
		
		entityManager.persist(donor);
		
	}

	@Override
	public List<donor> getAllDonor() {
	
		TypedQuery<donor> theQuery = entityManager.createQuery("from donor", donor.class);
		
		List<donor> donors = theQuery.getResultList();
				
		return donors;
	}

	@Override
	public donor getDonorById(int donorId) {
		
		donor donor = entityManager.find(donor.class, donorId);
		
		return donor;
	}

	@Override
	public boolean updateDonorById(int donorId, donor donor) {
		
		donor existDon = entityManager.find(donor.class, donorId);
		
		
		
		if(existDon != null)
		{         
			entityManager.merge(donor);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteDonorById(int donorId) {
		
		donor donor = entityManager.find(donor.class, donorId);
		if(donor != null)
		{
			entityManager.remove(donor);
			return true;
		}
		
		return false;
	}

	@Override
	public boolean deleteAllDonor() {
	
		int rows = entityManager.createQuery("DELETE FROM donor").executeUpdate();
		if(rows != 0)
		{
			return true;
		}
		return false;
	}

}
