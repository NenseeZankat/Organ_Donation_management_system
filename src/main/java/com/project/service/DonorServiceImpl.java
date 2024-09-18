package com.project.service;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.entities.donor;
import com.project.entities.organs;
import com.project.repository.donorDAO;


@Service
public class DonorServiceImpl implements DonorService {
	
	
	private donorDAO donorRepo;
	
	@Autowired
	public DonorServiceImpl(@Qualifier("donorDAOImpl") donorDAO donorRepo)
	{
		this.donorRepo = donorRepo;
	}
	
	@Transactional
	public void addDonor(donor donor)
	{
		for(organs organ : donor.getOrgansList())
		{
			organ.setDonor(donor);
		}
		donor.setOrgansList(donor.getOrgansList());
		donorRepo.addDonor(donor);
	}
	
	@Transactional
	public List<donor> getAllDonor()
	{
		List<donor> donors = donorRepo.getAllDonor();
		return donors;
	}
	
	@Transactional
	public donor getDonorById(int donorId)
	{
		donor donor = donorRepo.getDonorById(donorId);
		if(donor != null)
		{
			return donor;
		}
		return null;
	}
	
	@Transactional
	public boolean updateDonorById(int donorId, donor donor)
	{
		
		boolean res = donorRepo.updateDonorById(donorId, donor);
		if(res)
		{
			return true;
		}
		return false;
	}
	

	@Transactional
    public boolean deleteDonorById(int donorId) {
		boolean res = donorRepo.deleteDonorById(donorId);
		if(res)
		{
			return true;
		}
		return false;
    }
	
	@Transactional
	public boolean deleteAllDonor()
	{
		boolean res = donorRepo.deleteAllDonor();
		if(res)
		{
			return true;
		}
		return false;
	}

}
