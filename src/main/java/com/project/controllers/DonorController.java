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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.entities.authorities;
import com.project.entities.donor;
import com.project.entities.organs;
import com.project.entities.users;
import com.project.service.DonorService;
import com.project.service.RoleService;
import com.project.service.UserService;


@RestController
@RequestMapping("/donors")
public class DonorController {
	
	private DonorService donorService;
	private UserService userService;
	private RoleService roleService;
	
	@Autowired
	public DonorController(DonorService donorService, UserService userService, RoleService roleService)
	{
		this.donorService = donorService;
		this.userService = userService;
		this.roleService = roleService;
	}
	

    
    // get all donors 
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllDonorP() {
    	List<donor> donors = donorService.getAllDonor();	
    	
    	List<Map<String, Object>> resultList = new ArrayList<>();

        donors.forEach(donor -> {
            Map<String, Object> donorMap = new HashMap<>();
            donorMap.put("donorId", donor.getDonorId());
            donorMap.put("donorName", donor.getDonorName());
            donorMap.put("dob", donor.getDob());
            donorMap.put("gender", donor.getGender());
            donorMap.put("bloodType", donor.getBloodType());
            donorMap.put("email", donor.getEmail());
            donorMap.put("phoneNo", donor.getPhoneNo());
            donorMap.put("city", donor.getCity());
            donorMap.put("state", donor.getState());
            donorMap.put("street", donor.getStreet());

            List<Map<String, Object>> organsList = new ArrayList<>();
            if (donor.getOrgansList() != null) {
                for (organs organ : donor.getOrgansList()) {
                    Map<String, Object> organMap = new HashMap<>();
                    organMap.put("organId", organ.getOrganId());
                    organMap.put("organName", organ.getOrganName());
                    organsList.add(organMap);
                }
            }
            
            donorMap.put("organsList", organsList);
            

            resultList.add(donorMap);
        });

        return ResponseEntity.ok(resultList);
    }
    
    
    // update donor by donorId
    @PutMapping("/{donorId}")
    public ResponseEntity<String> updateDonorByIdP(@PathVariable int donorId, @RequestBody donor donor)
    {
        boolean update = donorService.updateDonorById(donorId, donor);
        if (update) {
            return ResponseEntity.ok("Donor details of " + donorId + " is updated.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Donor details of " + donorId + " is not found.");
    }
    
    
    // delete all donors
    @DeleteMapping
    public ResponseEntity<String> deleteAllDonorP()
    {
        boolean delete = donorService.deleteAllDonor();
        if (delete) {
            return ResponseEntity.ok("Donor's data is deleted successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Donor's data is not found.");
    }
    
    
    // get donor by donorId
    @GetMapping("/{donorId}")
    public ResponseEntity<Map<String, Object>> getDonorByIdP(@PathVariable int donorId) {
        donor donor = donorService.getDonorById(donorId);
        Map<String, Object> response = new HashMap<>();

        if (donor != null) {
            response.put("donorId", donor.getDonorId());
            response.put("donorName", donor.getDonorName());
            response.put("dob", donor.getDob());
            response.put("gender", donor.getGender());
            response.put("bloodType", donor.getBloodType());
            response.put("email", donor.getEmail());
            response.put("phoneNo", donor.getPhoneNo());
            response.put("city", donor.getCity());
            response.put("state", donor.getState());
            response.put("street", donor.getStreet());

            List<Map<String, Object>> organsList = new ArrayList<>();
            if (donor.getOrgansList() != null) {
                for (organs organ : donor.getOrgansList()) {
                    Map<String, Object> organMap = new HashMap<>();
                    organMap.put("organId", organ.getOrganId());
                    organMap.put("organName", organ.getOrganName());
                    organsList.add(organMap);
                }
            }
            
            response.put("organsList", organsList);

            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    
    // insert donor with password
    @PostMapping
    public ResponseEntity<String> addDonorP(@RequestBody donor donor, @RequestParam("password") String passwordFromUser) {
    	donorService.addDonor(donor);
    	
    	users user = new users();
        user.setUsername(donor.getDonorName());
        user.setPassword("{noop}" + passwordFromUser);
        user.setActive(true);
        userService.addUser(user);

        authorities role = new authorities();
        role.setRole("ROLE_DONOR");
        role.setUser(user);
        roleService.addRole(role);
        
        return ResponseEntity.ok("Donor added");
    }
    
    
    // delete donor by donorId
    @DeleteMapping("/{donorId}") 
    public ResponseEntity<String> deleteDonorByIdP(@PathVariable int donorId)
    {
        boolean delete = donorService.deleteDonorById(donorId);
        if (delete) {
            return ResponseEntity.ok("Donor data of id " + donorId + " is deleted successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Donor details of " + donorId + " is not found.");
    }
  
}
