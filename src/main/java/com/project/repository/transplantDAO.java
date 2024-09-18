package com.project.repository;



import java.util.List;


import com.project.entities.transplant;

//@Repository
//public interface transplantDAO extends JpaRepository<transplant, Integer>{
//
//	
//}


public interface transplantDAO {
	
public void addTransplant(transplant transplant);
	
	public List<transplant> getAllTransplant();
	
	public transplant getTransplantById(int transId);
	
	public boolean updateTransplantById(int transId, transplant transplant);
	
	public boolean deleteTransplantById(int transId);
	
	public boolean deleteAllTransplant();

	public long getSuccTransNo(String doctorName);

	public List<Object[]> getPatientUnderDoc(String doctorName);
	
}
