package com.project.service;

import java.util.List;

import com.project.entities.organs;

public interface OrganService {

	public void addOrgan(organs organ);
	
	public List<organs> getAllOrgan();
	
	public organs getOrganById(int organId);
	
	public boolean updateOrganById(int organId, organs organ);
	
	public String deleteOrganById(int organId);
	
	public boolean deleteAllOrgan();

	public boolean checkForOrganP(organs organ);

	public List<String> getPatientsNameByOrgan(String organName);

	public List<String> getDonorsNameByOrgan(String organName);

	public List<Object[]> getAvailableOrgans();

	
	
}


