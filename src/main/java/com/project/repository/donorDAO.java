package com.project.repository;

import java.util.List;

import com.project.entities.donor;

//@Repository
//public interface donorDAO extends JpaRepository<donor, Integer> {
//    
////    @Query("SELECT d FROM donor d WHERE d.donorName = :name")
////    public donor findByDonorName(String name);
//}

public interface donorDAO{
	
	public void addDonor(donor donor);
	 
	public List<donor> getAllDonor();
	
	public donor getDonorById(int donorId);
	
	public boolean updateDonorById(int donorId, donor donor);
	
	public boolean deleteDonorById(int donorId);
	
	public boolean deleteAllDonor();
}
