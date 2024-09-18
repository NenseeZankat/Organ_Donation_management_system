package com.project.repository;

import java.util.List;


import com.project.entities.doctor;

//@Repository
//public interface doctorDAO extends JpaRepository<doctor, Integer>{
//
//}


public interface doctorDAO {
	
	public void addDoctor(doctor doctor);
	
	public List<doctor> getAllDoctor();
	
	public doctor getDoctorById(int doctorId);
	
	public boolean updateDoctorById(int doctorId, doctor doctor);
	
	public List<String> deleteDoctorById(int doctorId);
	
	public boolean deleteAllDoctor();

	
}
