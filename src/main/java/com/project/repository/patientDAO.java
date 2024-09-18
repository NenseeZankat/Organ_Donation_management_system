package com.project.repository;




import java.util.List;


import com.project.entities.patient;

//@Repository
//public interface patientDAO extends JpaRepository<patient, Integer> {
//
////    @Query("SELECT p FROM patient p WHERE p.patientName = :name")
////    public patient findByPatientName(String name);
////
////    @Query("SELECT p FROM patient p WHERE p.organRequired = :organ_name AND p.transplant IS NULL")
////    public List<patient> getAllPatientForOrgan(String organ_name);
//
//}



public interface patientDAO {
	
public void addPatient(patient patient);
	
	public List<patient> getAllPatient();
	
	public patient getPatientById(int patientId);
	
	public boolean updatePatientById(int patientId, patient patient);
	
	public boolean deletePatientById(int patientId);
	
	public boolean deleteAllPatient();
}