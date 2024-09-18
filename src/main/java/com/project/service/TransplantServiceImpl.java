package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import com.project.entities.transplant;
import com.project.repository.transplantDAO;

import jakarta.transaction.Transactional;

@Service
public class TransplantServiceImpl implements TransplantService {

	
	private transplantDAO transplantRepo;
	
	@Autowired
	public TransplantServiceImpl(@Qualifier("transplantDAOImpl") transplantDAO transplantRepo) {
		this.transplantRepo = transplantRepo;
	}

	@Transactional
	public void addTransplant(transplant transplant)
	{
		transplantRepo.addTransplant(transplant);
		transplant.getOrgan().setAvailable(false);
	}
	
	@Transactional
	public List<transplant> getAllTransplant()
	{
		List<transplant> transplants = transplantRepo.getAllTransplant();
		return transplants;
	}
	
	@Transactional
	public transplant getTransplantById(int transId)
	{
		transplant transplant = transplantRepo.getTransplantById(transId);
		if(transplant != null)
		{
			return transplant;
		}
		return null;
	}
	
	@Transactional
	public boolean updateTransplantById(int transId, transplant transplant) {
		
		boolean res = transplantRepo.updateTransplantById(transId, transplant);
		if(res)
		{
			return true;
		}
	    return false;
	}

	@Transactional
	public boolean deleteTransplantById(int transId)
	{
		boolean res = transplantRepo.deleteTransplantById(transId);
		if(res)
		{
			return true;
		}
		
		return false;
	}
	
	@Transactional	
	public boolean deleteAllTransplant()
	{
		boolean res = transplantRepo.deleteAllTransplant();
		if(res)
		{
			return true;
		}
		return false;
	}

	
	@Override
	@Transactional
	public long getSuccTransNo(String doctorName) {
		
		long num = transplantRepo.getSuccTransNo(doctorName);
		return num;
	}

	@Override
	@Transactional
	public List<Object[]> getPatientsUnderDoc(String doctorName) {
		
		List<Object[]> patients = transplantRepo.getPatientUnderDoc(doctorName); 
		return patients;
	}
	
	

	 

}
