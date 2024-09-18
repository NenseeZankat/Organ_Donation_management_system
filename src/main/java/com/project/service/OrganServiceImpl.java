package com.project.service;



import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.entities.donor;
import com.project.entities.organs;
import com.project.entities.patient;
import com.project.repository.organDAO;


@Service
public class OrganServiceImpl implements OrganService {
	
	
	private organDAO organRepo;
	
	@Autowired
	public OrganServiceImpl(@Qualifier("organDAOImpl") organDAO organRepo)
	{
		this.organRepo = organRepo;
	}

	@Transactional
	public void addOrgan(organs organ) {
		
	    organRepo.addOrgan(organ);
	}


	@Transactional
	public List<organs> getAllOrgan()
	{
		List<organs> organs = organRepo.getAllOrgan();
		return organs;
	}
	
	@Transactional
	public organs getOrganById(int organId)
	{
		organs organ = organRepo.getOrganById(organId);
		if(organ != null)
		{
			return organ;
		}
		return null;
	}
	
	@Transactional
	public boolean updateOrganById(int organId, organs organ)
	{
		boolean res = organRepo.updateOrganById(organId, organ);
		if(res)
		{
			return true;
		}
		
		return false;
	}

	
	@Transactional
	public String deleteOrganById(int organId)
	{
		String patientName = organRepo.deleteOrganById(organId);
		if(patientName != null)
		{
			return patientName;
		}
		return "";
	}
	
	

	@Transactional
	public boolean deleteAllOrgan()
	{
		boolean res = organRepo.deleteAllOrgan();
		if(res)
		{
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public boolean checkForOrganP(organs organ) {
		String organName = organ.getOrganName();
		int organId = organ.getOrganId();
		boolean availability = organRepo.checkForOrgan(organName, organId);
		if(availability) {
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public List<String> getPatientsNameByOrgan(String organName) {
		List<patient> Patients = organRepo.findPatientsNameByOrgan(organName);
		List<String> PatientNames = new ArrayList<>();
		
		for(patient p : Patients) {
			PatientNames.add(p.getPatientName());
		}
		return PatientNames;
	}

	@Override
	public List<String> getDonorsNameByOrgan(String organName) {
		
		List<donor> donors = organRepo.findDonorsNameByOrgan(organName);
		List<String> DonorNames = new ArrayList<>();
		
		for(donor d : donors) {
			DonorNames.add(d.getDonorName());
		}
		return DonorNames;
		
	}

	@Override
	public List<Object[]> getAvailableOrgans() {
		
		List<Object[]> organs = organRepo.getAvailableOrgans();
		return organs;
	}


	
}
