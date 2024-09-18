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
import com.project.entities.doctor;
import com.project.entities.transplant;
import com.project.entities.users;
import com.project.service.DoctorService;
import com.project.service.RoleService;
import com.project.service.UserService;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

	
    private DoctorService doctorService;
	private UserService userService;
	private RoleService roleService;
    
    @Autowired
	public DoctorController(DoctorService doctorService, UserService userService, RoleService roleService)
	{
		this.doctorService = doctorService;
		this.userService = userService;
		this.roleService = roleService;
	}
  
    
    // get all doctors
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllDoctorP() {
        List<doctor> doctors = doctorService.getAllDoctor();
        List<Map<String, Object>> resultList = new ArrayList<>();

        doctors.forEach(doctor -> {
            Map<String, Object> doctorMap = new HashMap<>();
            doctorMap.put("doctorId", doctor.getDoctorId());
            doctorMap.put("doctorName", doctor.getDoctorName());
            doctorMap.put("qualification", doctor.getQualification());
            doctorMap.put("phoneNo", doctor.getPhoneNo());
            doctorMap.put("email", doctor.getEmail());
            doctorMap.put("since", doctor.getSince());

            List<Integer> transplantIds = new ArrayList<>();
            if (doctor.getTransplantList() != null) {
                for (transplant transplant : doctor.getTransplantList()) {
                    transplantIds.add(transplant.getTransId());
                }
            }

            doctorMap.put("transplantList", transplantIds);

            resultList.add(doctorMap);
        });

        return ResponseEntity.ok(resultList);
    }

    
    // insert the doctor with password in the request parameter
    @PostMapping
    public ResponseEntity<String> addDoctorP(@RequestBody doctor doctor, @RequestParam("password") String passwordFromUser) {
        doctorService.addDoctor(doctor);

        users user = new users();
        user.setUsername(doctor.getDoctorName());
        user.setPassword("{noop}" + passwordFromUser);
        user.setActive(true);
        userService.addUser(user);

        authorities role = new authorities();
        role.setRole("ROLE_DOCTOR");
        role.setUser(user);
        roleService.addRole(role);

        return ResponseEntity.ok("Doctor added");
    }

    // get doctor by doctorId
    @GetMapping("/{doctorId}")
    public ResponseEntity<Map<String, Object>> getDoctorByIdP(@PathVariable int doctorId) {
        doctor doctor = doctorService.getDoctorById(doctorId);
        Map<String, Object> response = new HashMap<>();

        if (doctor != null) {
            response.put("doctorId", doctor.getDoctorId());
            response.put("doctorName", doctor.getDoctorName());
            response.put("qualification", doctor.getQualification());
            response.put("phoneNo", doctor.getPhoneNo());
            response.put("email", doctor.getEmail());
            response.put("since", doctor.getSince());

            List<Integer> transplantIds = new ArrayList<>();
            if (doctor.getTransplantList() != null) {
                for (transplant transplant : doctor.getTransplantList()) {
                    transplantIds.add(transplant.getTransId());
                }
            }

            response.put("transplantList", transplantIds);

            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    
  
    // delete all doctors
    @DeleteMapping
    public ResponseEntity<String> deleteAllDoctorP()
    {
        boolean delete = doctorService.deleteAllDoctor();
        
        
        if (delete) {
            return ResponseEntity.ok("Doctor's data is deleted successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor's data is not found.");
    }
    
    
    
    // delete doctor by doctorId
    @DeleteMapping("/{doctorId}") 
    public ResponseEntity<String> deleteDoctorByIdP(@PathVariable int doctorId)
    {
    	doctor doc = doctorService.getDoctorById(doctorId);
        List<String> patientNames = doctorService.deleteDoctorById(doctorId);
        
        if (!patientNames.isEmpty() && doc != null) {
            StringBuilder message = new StringBuilder("Doctor data of ID ")
                    .append(doctorId)
                    .append(" has been deleted successfully.\nAffected patients: \n");
            
            for (String patientName : patientNames) {
                message.append(patientName).append("\n");
            }
            
            return ResponseEntity.ok(message.toString());
        }
        else if(doc != null)
        {
        	return ResponseEntity.status(HttpStatus.FOUND).body("Doctor details of " + doctorId + " is deleted succefully.");
        }
        else
        {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor details of " + doctorId + " is not found.");
        }
      
     }
    
    
    // postman - update doctor by doctorId
    @PutMapping("/{doctorId}")
    public ResponseEntity<String> updateDoctorByIdP(@PathVariable int doctorId, @RequestBody doctor doctor)
    {
        boolean update = doctorService.updateDoctorById(doctorId, doctor);
        if (update) {
            return ResponseEntity.ok("Doctor details of " + doctorId + " is updated.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor details of " + doctorId + " is not found.");
    }
    
   
}
