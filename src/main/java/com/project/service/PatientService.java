package com.project.service;

import java.util.List;

import com.project.entities.patient;

public interface PatientService {

	public void addPatient(patient patient);
	
	public List<patient> getAllPatient();
	
	public patient getPatientById(int patientId);
	
	public boolean updatePatientById(int patientId, patient patient);
	
	public boolean deletePatientById(int patientId);
	
	public boolean deleteAllPatient();
}
