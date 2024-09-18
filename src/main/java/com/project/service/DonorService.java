package com.project.service;

import java.util.List;

import com.project.entities.donor;

public interface DonorService {

	public void addDonor(donor donor);
	 
	public List<donor> getAllDonor();
	
	public donor getDonorById(int donorId);
	
	public boolean updateDonorById(int donorId, donor donor);
	
	public boolean deleteDonorById(int donorId);
	
	public boolean deleteAllDonor();
}
