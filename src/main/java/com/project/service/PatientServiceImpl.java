package com.project.service;



import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.project.entities.patient;
import com.project.repository.patientDAO;

import jakarta.transaction.Transactional;



@Service
public class PatientServiceImpl implements PatientService {
	

	private patientDAO patientRepo;

	@Autowired
	public PatientServiceImpl(@Qualifier("patientDAOImpl") patientDAO patientRepo)
	{
		this.patientRepo = patientRepo;
	}
	
	
	@Transactional
	public void addPatient(patient patient)
	{
		 patientRepo.addPatient(patient);       
	}
	
	@Transactional
	public List<patient> getAllPatient()
	{
		List<patient> patients = patientRepo.getAllPatient();
		return patients;
	}
	
	@Transactional
	public patient getPatientById(int patientId)
	{
		patient patient = patientRepo.getPatientById(patientId);
		if(patient != null)
		{
			return patient;
		}
		return null;
	}
	
	@Transactional
	public boolean updatePatientById(int patientId, patient patient) {
		
		boolean res = patientRepo.updatePatientById(patientId, patient);
		if(res)
		{
			return true;
		}

	    return false;
	}

	@Transactional
	public boolean deletePatientById(int patientId)
	{
		boolean res = patientRepo.deletePatientById(patientId);
		if(res)
		{
			return true;
		}
		return false;
	}
	
	@Transactional
	public boolean deleteAllPatient()
	{
		boolean res = patientRepo.deleteAllPatient();
		if(res)
		{
			return true;
		}
		return false;
	}


	    
}
