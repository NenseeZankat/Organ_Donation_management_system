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

import com.project.entities.donor;
import com.project.entities.organs;
import com.project.service.DonorService;
import com.project.service.OrganService;



@RestController
@RequestMapping("/organs")
public class OrganController {
	
	
    private OrganService organService;
	private DonorService donorService;
	
	
    
    public OrganController(@Autowired OrganService organService, @Autowired DonorService donorService)
    {
    	this.organService = organService;
    	this.donorService = donorService;
    }

    
    // get patient's names by organ name
    @GetMapping("PatientName/{organName}")
    public ResponseEntity<List<String>> getPatientsNameByOrgan(@PathVariable String organName)
    {
    	List<String> PatientNames = organService.getPatientsNameByOrgan(organName);
    	return ResponseEntity.ok(PatientNames);
    }
    
    
    // get donor's name by organ name
    @GetMapping("donorNames/{organName}")
    public ResponseEntity<List<String>> getDonorsNameByOrgan(@PathVariable String organName)
    {
    	List<String> DonorNames = organService.getDonorsNameByOrgan(organName);
    	return ResponseEntity.ok(DonorNames);
    }
    
    
    // get all available organ's name
    @GetMapping("availableOrgans")
    public ResponseEntity<List<Map<String, Object>>> getAvailableOrgans() {
        List<Object[]> organs = organService.getAvailableOrgans();

        List<Map<String, Object>> resultList = new ArrayList<>();
        organs.forEach(organ -> {
            Map<String, Object> organMap = new HashMap<>();
            organMap.put("organName", organ[0]);
            organMap.put("quantity", organ[1]);
            resultList.add(organMap);
        });

        resultList.forEach(organMap -> {
            System.out.println("Organ Name: " + organMap.get("organName") + ", Quantity: " + organMap.get("quantity"));
        });

        return ResponseEntity.ok(resultList);
    }

    
   
    
    // get all organ's details
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllOrganP() {
        List<organs> organsList = organService.getAllOrgan();
        
        List<Map<String, Object>> resultList = new ArrayList<>();

        organsList.forEach(organ -> {
            Map<String, Object> organMap = new HashMap<>();
            organMap.put("organId", organ.getOrganId());
            organMap.put("organName", organ.getOrganName());
            organMap.put("available", organ.isAvailable());
            organMap.put("reasonOfDonation", organ.getReasonOfDonation());
            organMap.put("dateOfDonation", organ.getDateOfDonation());
            organMap.put("transId", (organ.getTransplant() != null) ? organ.getTransplant().getTransId() : null);
            organMap.put("donorId", organ.getDonor().getDonorId());
            

            resultList.add(organMap);
        });

        return ResponseEntity.ok(resultList);
    }

    
    // insert the organ 
    @PostMapping
    public ResponseEntity<String> addOrganP(@RequestBody organs organ) {
    	
    	int donorId = organ.getDonor().getDonorId(); 
    	donor Donor = donorService.getDonorById(donorId);
    	
    	organ.setDonor(Donor);
    	
        organService.addOrgan(organ);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("organ details is inserted");
    }
    
    
    // get organ details by organId
    @GetMapping("/{organId}")
    public ResponseEntity<Map<String, Object>> getOrganByIdP(@PathVariable int organId) {
        organs organ = organService.getOrganById(organId);
        Map<String, Object> response = new HashMap<>();

        if (organ != null) {
            response.put("organId", organ.getOrganId());
            response.put("organName", organ.getOrganName());
            response.put("available", organ.isAvailable());
            response.put("reasonOfDonation", organ.getReasonOfDonation());
            response.put("dateOfDonation", organ.getDateOfDonation());
            response.put("transId", (organ.getTransplant() != null) ? organ.getTransplant().getTransId() : null);
            response.put("donorId", organ.getDonor().getDonorId());

            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    

    // update the organ by organId
    @PutMapping("/{organId}")
    public ResponseEntity<String> updateOrganByIdP(@PathVariable int organId, @RequestBody organs organ)
    {
        boolean update = organService.updateOrganById(organId, organ);
        if (update) {
            return ResponseEntity.ok("organ details of " + organId + " is updated.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("organ details of " + organId + " is not found.");
    }
    
    
    // delete organ by organId
    @DeleteMapping("/{organId}") 
    public ResponseEntity<String> deleteOrganByIdP(@PathVariable int organId)
    {
    	organs org = organService.getOrganById(organId);
        String patientName = organService.deleteOrganById(organId);
        if (patientName != "" && org != null) {
            return ResponseEntity.ok("organ data of id " + organId + " is deleted successfully. \n" + patientName + "'s transplant affected" );
            
        }
        else if(org != null)
        {
        	return ResponseEntity.status(HttpStatus.FOUND).body("organ details of " + organId + " is deleted successfully.");
        	  
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("organ details of " + organId + " is not found.");
    }
    
 
    // delete all organs 
    @DeleteMapping
    public ResponseEntity<String> deleteAllOrganP()
    {
        boolean delete = organService.deleteAllOrgan();
        if (delete) {
            return ResponseEntity.ok("organ's data is deleted successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("organ's data is not found.");
    }
    
}

