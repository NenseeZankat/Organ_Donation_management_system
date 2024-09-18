package com.project.repository;



import java.util.List;


import com.project.entities.donor;
import com.project.entities.organs;
import com.project.entities.patient;

//@Repository
//public interface organDAO extends JpaRepository<organs, Integer> {
//
//	@Query("SELECT o.available FROM organs o WHERE o.organName = :name AND o.organId = :id")
//	boolean checkForOrgan(@RequestParam String name, @RequestParam int id);
//
//	@Query("SELECT p.patientName FROM organs o, patient p WHERE o.organName = :organName AND o.organName = p.organRequired")
//	List<String> findPatientsNameByOrgan(@Param("organName") String organName);
//
//}



public interface organDAO{
	
    public void addOrgan(organs organ);
	
	public List<organs> getAllOrgan();
	
	public organs getOrganById(int organId);
	
	public boolean updateOrganById(int organId, organs organ);
	
	public String deleteOrganById(int organId);
	
	public boolean deleteAllOrgan();

	public boolean checkForOrgan(String name, int id);

	public List<patient> findPatientsNameByOrgan(String organName);

	public List<donor> findDonorsNameByOrgan(String organName);

	public List<Object[]> getAvailableOrgans();
}