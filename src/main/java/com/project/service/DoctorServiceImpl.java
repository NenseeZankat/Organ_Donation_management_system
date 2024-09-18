package com.project.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.entities.doctor;
import com.project.entities.transplant;
import com.project.repository.doctorDAO;


@Service
public class DoctorServiceImpl implements DoctorService {
	
	
	private doctorDAO doctorRepo;
	
	@Autowired 
	public DoctorServiceImpl(@Qualifier("doctorDAOImpl") doctorDAO doctorRepo)
	{
		this.doctorRepo = doctorRepo;
	}
	
	@Transactional
	public void addDoctor(doctor doctor)
	{
		for(transplant transplant : doctor.getTransplantList())
		{
			transplant.setDoctor(doctor);
		}
		doctor.setTransplantList(doctor.getTransplantList());
		
		doctorRepo.addDoctor(doctor);
	}
	
	@Transactional
	public List<doctor> getAllDoctor()
	{
		List<doctor> doctors = doctorRepo.getAllDoctor();
		return doctors;
	}
	
	@Transactional
	public doctor getDoctorById(int doctorId)
	{
		doctor doctor = doctorRepo.getDoctorById(doctorId);
		if(doctor != null)
		{
			return doctor;
		}
		return null;
	}
	
	@Transactional
	public boolean updateDoctorById(int doctorId, doctor doctor)
	{
		for(transplant transplant : doctor.getTransplantList())
		{
			transplant.setDoctor(doctor);
		}
		
		boolean res = doctorRepo.updateDoctorById(doctorId, doctor);
		if(res)
		{
			return true;
		}
		return false;
	}
	
	
	@Transactional
	public List<String> deleteDoctorById(int doctorId)
	{
		
		List<String> patientNames = doctorRepo.deleteDoctorById(doctorId);
		
		return patientNames;
	}
	
	@Transactional
	public boolean deleteAllDoctor()
	{
		boolean res = doctorRepo.deleteAllDoctor();
		if(res)
		{
			return true;
		}
		return false;
	}

	

}
