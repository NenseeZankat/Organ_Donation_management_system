package com.project.service;

import java.util.List;

import com.project.entities.transplant;

public interface TransplantService {

	public void addTransplant(transplant transplant);
	
	public List<transplant> getAllTransplant();
	
	public transplant getTransplantById(int transId);
	
	public boolean updateTransplantById(int transId, transplant transplant);
	
	public boolean deleteTransplantById(int transId);
	
	public boolean deleteAllTransplant();

	public long getSuccTransNo(String doctorName);

	public List<Object[]> getPatientsUnderDoc(String doctorName);
}
