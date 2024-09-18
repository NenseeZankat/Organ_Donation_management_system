package com.project.service;

import java.util.List;

import com.project.entities.doctor;

public interface DoctorService {

	public void addDoctor(doctor doctor);
	
	public List<doctor> getAllDoctor();
	
	public doctor getDoctorById(int doctorId);
	
	public boolean updateDoctorById(int doctorId, doctor doctor);
	
	public List<String> deleteDoctorById(int doctorId);
	
	public boolean deleteAllDoctor();
}
