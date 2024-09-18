package com.project.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.entities.doctor;
import com.project.entities.organs;
import com.project.entities.patient;
import com.project.entities.transplant;
import com.project.service.DoctorService;
import com.project.service.OrganService;
import com.project.service.PatientService;
import com.project.service.TransplantService;

@RestController
@RequestMapping("/transplants")
public class TransplantController {
	
	
	private TransplantService transplantService;
	private OrganService organService;
	private PatientService patientService;
	private DoctorService doctorService;
	
	public TransplantController(@Autowired TransplantService transplantService, @Autowired OrganService organService,
			@Autowired PatientService patientService, @Autowired DoctorService doctorService)
	{
		this.transplantService = transplantService;
		this.organService = organService;
		this.patientService = patientService;
		this.doctorService = doctorService;
	}


	// get all transplants
	@GetMapping
	public ResponseEntity<List<Map<String, Object>>> getAllTransplantP() {
	    List<transplant> transplants = transplantService.getAllTransplant();
	    List<Map<String, Object>> resultList = new ArrayList<>();

	    transplants.forEach(transplant -> {
	        Map<String, Object> transplantMap = new HashMap<>();
	        transplantMap.put("transId", transplant.getTransId());
	        transplantMap.put("dateOfTransplantation", transplant.getDateOfTransplantation());
	        transplantMap.put("success", transplant.isSuccess());
	        transplantMap.put("organ", (transplant.getOrgan() != null) ? transplant.getOrgan().getOrganId() : null);
	        transplantMap.put("donorId", (transplant.getDoctor() != null) ? transplant.getDoctor().getDoctorId() : null);
	        transplantMap.put("organ", (transplant.getPatient() != null) ? transplant.getPatient().getPatientId() : null);
	        resultList.add(transplantMap);
	    });

	    return ResponseEntity.ok(resultList);
	}

    
    
    // update transplant by transId
    @PutMapping("/{transId}")
    public ResponseEntity<String> updateTransplantByIdP(@PathVariable int transId, @RequestBody transplant transplant )
    {
        boolean update = transplantService.updateTransplantById(transId, transplant);
        if (update) {
            return ResponseEntity.ok("transplant details of " + transId + " is updated.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("transplant details of " + transId + " is not found.");
    }
    
    

    // delete all transplants
    @DeleteMapping
    public ResponseEntity<String> deleteAllTransplantP()
    {
        boolean delete = transplantService.deleteAllTransplant();
        if (delete) {
            return ResponseEntity.ok("transplant's data is deleted successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("transplant's data is not found.");
    }
    
    
    // get transplant by transId
    @GetMapping("/{transId}")
    public ResponseEntity<Map<String, Object>> getTransplantByIdP(@PathVariable int transId) {
        transplant transplant = transplantService.getTransplantById(transId);
        Map<String, Object> response = new HashMap<>();
        
        if (transplant != null) {
            response.put("transId", transplant.getTransId());
            response.put("dateOfTransplantation", transplant.getDateOfTransplantation());
            response.put("success", transplant.isSuccess());
            response.put("organId", (transplant.getOrgan() != null) ? transplant.getOrgan().getOrganId() : null);
            response.put("donorId", (transplant.getDoctor() != null) ? transplant.getDoctor().getDoctorId() : null);
            response.put("patientId", (transplant.getPatient() != null) ? transplant.getPatient().getPatientId() : null);
            
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    
    // insert transplant
    @PostMapping
    public ResponseEntity<String> addTransplantP(@RequestBody transplant transplant) {
    	
    	int organId = transplant.getOrgan().getOrganId(); 
    	organs organ = organService.getOrganById(organId);

		boolean available = organService.checkForOrganP(organ);
		if(available)
		{
			int patientId = transplant.getPatient().getPatientId();
	    	patient patient = patientService.getPatientById(patientId);
	    	
	    	
	    	int doctorId = transplant.getDoctor().getDoctorId();
	    	doctor doctor = doctorService.getDoctorById(doctorId);
	    	
	    	if(organ.getOrganName().equals(patient.getOrganRequired()))
	    	{
	    		transplant.setDoctor(doctor);
		    	transplant.setOrgan(organ);
		    	transplant.setPatient(patient);
	    		transplantService.addTransplant(transplant);
	    	}
	    	else
	    	{
	    		return ResponseEntity.ok("organ is not the required organ\n" + patient.getPatientName() + " needs " + patient.getOrganRequired());
	    	}
	    	
	    	
	    	return ResponseEntity.ok("transplant added");
		}
		else {
			return ResponseEntity.ok("organ is not available ");
		}
		
    }
    
    
    // get no of success transplants by doctor name
    @GetMapping("succTransplants/{doctorName}")
    public ResponseEntity<Long> getSuccessTransplants(@PathVariable String doctorName) {
    	long num = transplantService.getSuccTransNo(doctorName);	
    	return ResponseEntity.ok(num);
    }
   
    
    // get patient by doctor name 
    @GetMapping("patientsUnderDoc/{doctorName}")
    public ResponseEntity<List<Map<String, Object>>> getPatientsUnderDoc(@PathVariable String doctorName) {
    	
    	List<Object[]> patientNames = transplantService.getPatientsUnderDoc(doctorName);
    	
    	List<Map<String, Object>> resultList = new ArrayList<>();
        patientNames.forEach(patient -> {
            Map<String, Object> patientMap = new HashMap<>();
            patientMap.put("patientName", patient[0]);
            patientMap.put("transplantSuccess", patient[1]);
            resultList.add(patientMap);
        });

        
        resultList.forEach(patientMap -> {
            System.out.println("Patient Name: " + patientMap.get("patientName") + ", Transplant Success: " + patientMap.get("transplantSuccess"));
        });

        
        return ResponseEntity.ok(resultList);
    	
    }
    
    
    // delete transplant by transId
    @DeleteMapping("/{transId}") 
    public ResponseEntity<String> deleteTransplantByIdP(@PathVariable int transId)
    {
        boolean delete = transplantService.deleteTransplantById(transId);
        if (delete) {
            return ResponseEntity.ok("transplant data of id " + transId + " is deleted successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("transplant details of " + transId + " is not found.");
    }
}
