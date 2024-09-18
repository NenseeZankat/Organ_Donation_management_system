package com.project.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.entities.patient;
import com.project.entities.transplant;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class patientDAOImpl implements patientDAO {

	
private EntityManager entityManager;
	
	@Autowired
	public patientDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public void addPatient(patient patient) {
		
		entityManager.persist(patient);
		
	}

	@Override
	public List<patient> getAllPatient() {
		
		TypedQuery<patient> theQuery = entityManager.createQuery("from patient", patient.class);
		
		List<patient> patients = theQuery.getResultList();
				
		return patients;
	}

	@Override
	public patient getPatientById(int patientId) {
		
		patient patient = entityManager.find(patient.class, patientId);
		
		return patient;
	}

	@Override
	public boolean updatePatientById(int patientId, patient patient) {
		
		patient existPatient = entityManager.find(patient.class, patientId);
		
		if(existPatient != null)
		{
			entityManager.merge(patient);
			return true;
		}
		return false;
	}

	@Override
	public boolean deletePatientById(int patientId) {
		
		patient patient = entityManager.find(patient.class, patientId);
		
		if(patient != null)
		{
			transplant trans = patient.getTransplant();
			if(trans != null)
			{
				boolean res = trans.isSuccess();
				if(res != true)
				{
					trans.getOrgan().setAvailable(true);
				}
			}
			entityManager.remove(patient);
			return true;
		}
		
		return false;
	}

	@Override
	public boolean deleteAllPatient() {
		
		int rows = entityManager.createQuery("DELETE FROM patient").executeUpdate();
		if(rows != 0)
		{
			return true;
		}
		return false;
	}

}
