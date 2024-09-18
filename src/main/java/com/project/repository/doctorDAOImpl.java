package com.project.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.entities.doctor;
import com.project.entities.transplant;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;


@Repository	
public class doctorDAOImpl implements doctorDAO {

	private EntityManager entityManager;
	
	@Autowired
	public doctorDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	
	@Override
	public void addDoctor(doctor doctor) {
		
		entityManager.persist(doctor);
		
	}

	@Override
	public List<doctor> getAllDoctor() {
		
		TypedQuery<doctor> theQuery = entityManager.createQuery("from doctor", doctor.class);
		
		List<doctor> doctors = theQuery.getResultList();
				
		return doctors;
	}

	@Override
	public doctor getDoctorById(int doctorId) {
		
		doctor doctor = entityManager.find(doctor.class, doctorId);
		
		return doctor;
	}

	@Override
	public boolean updateDoctorById(int doctorId, doctor doctor) {
	
		
		doctor existDoc = entityManager.find(doctor.class, doctorId);
		
		
		if(existDoc != null)
		{
			entityManager.merge(doctor);
			return true;
		}
		return false;
	}

	@Override
	public List<String> deleteDoctorById(int doctorId) {
		
		doctor doctor = entityManager.find(doctor.class, doctorId);
		List<transplant> transplants = doctor.getTransplantList();
		List<String> patientNames = new ArrayList<>();
		
		for(transplant t : transplants)
		{
			if(t.isSuccess() == false)
			{
				t.getOrgan().setAvailable(true);
			}
		}

	    for (transplant t : transplants) {
	        String patientName = t.getPatient().getPatientName();
	        patientNames.add(patientName);
	        entityManager.remove(doctor);
	    }
	    
	    entityManager.remove(doctor);
		return patientNames;
	}

	@Override
	public boolean deleteAllDoctor() {
			
		int rows = entityManager.createQuery("DELETE FROM doctor").executeUpdate();
		if(rows != 0)
		{
			return true;
		}
		return false;
	}


	
	
	
}
