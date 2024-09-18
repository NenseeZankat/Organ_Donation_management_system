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
import com.project.entities.patient;
import com.project.entities.users;
import com.project.service.PatientService;
import com.project.service.RoleService;
import com.project.service.UserService;

@RestController
@RequestMapping("/patients")
public class PatientController 
{
	
	
    private PatientService patientService;
    private UserService userService;
	private RoleService roleService;
	
    @Autowired
    public PatientController(PatientService patientService, UserService userService, RoleService roleService)
    {
    	this.patientService = patientService;
    	this.userService = userService;
		this.roleService = roleService;
    }
    
    // get all patients
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllPatientP() {
    	
        List<patient> patientList = patientService.getAllPatient();
        
        List<Map<String, Object>> resultList = new ArrayList<>();

        patientList.forEach(patient -> {
            Map<String, Object> patientMap = new HashMap<>();
            patientMap.put("patientId", patient.getPatientId());
            patientMap.put("patientName", patient.getPatientName());
            patientMap.put("dob", patient.getDob());
            patientMap.put("gender", patient.getGender());
            patientMap.put("bloodType", patient.getBloodType());
            patientMap.put("email", patient.getEmail());
            patientMap.put("phoneNo", patient.getPhoneNo());
            patientMap.put("city", patient.getCity());
            patientMap.put("state", patient.getState());
            patientMap.put("street", patient.getStreet());
            patientMap.put("organRequired", patient.getOrganRequired());
            patientMap.put("reasonOfProcurement", patient.getReasonOfProcurement());
            patientMap.put("requestDate", patient.getRequestDate());
            patientMap.put("transId", (patient.getTransplant() != null) ? patient.getTransplant().getTransId() : null);
                        

            resultList.add(patientMap);
        });

        return ResponseEntity.ok(resultList);
    }
    
    
    // update patient by patientId
    @PutMapping("/{patientId}")
    public ResponseEntity<String> updatePatientByIdP(@PathVariable int patientId, @RequestBody patient patient )
    {
        boolean update = patientService.updatePatientById(patientId, patient);
        if (update) {
            return ResponseEntity.ok("patient details of " + patientId + " is updated.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("patient details of " + patientId + " is not found.");
    }
    
    
    // delete all patients
    @DeleteMapping
    public ResponseEntity<String> deleteAllPatientP()
    {
        boolean delete = patientService.deleteAllPatient();
        if (delete) {
            return ResponseEntity.ok("patient's data is deleted successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("patient's data is not found.");
    }
    
    
    // get patient by patientId
    @GetMapping("/{patientId}")
    public ResponseEntity<Map<String, Object>> getPatientByIdP(@PathVariable int patientId) {
        patient patient = patientService.getPatientById(patientId);
        Map<String, Object> response = new HashMap<>();

        if (patient != null) {
            response.put("patientId", patient.getPatientId());
            response.put("patientName", patient.getPatientName());
            response.put("dob", patient.getDob());
            response.put("gender", patient.getGender());
            response.put("bloodType", patient.getBloodType());
            response.put("email", patient.getEmail());
            response.put("phoneNo", patient.getPhoneNo());
            response.put("city", patient.getCity());
            response.put("state", patient.getState());
            response.put("street", patient.getStreet());
            response.put("organRequired", patient.getOrganRequired());
            response.put("reasonOfProcurement", patient.getReasonOfProcurement());
            response.put("requestDate", patient.getRequestDate());
            response.put("transId", (patient.getTransplant() != null) ? patient.getTransplant().getTransId() : null);

            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    
    // insert the patient
    @PostMapping
    public ResponseEntity<String> addPatientP(@RequestBody patient patient, @RequestParam("password") String passwordFromUser) {
    	patientService.addPatient(patient);
    	
    	users user = new users();
        user.setUsername(patient.getPatientName());
        user.setPassword("{noop}" + passwordFromUser);
        user.setActive(true);
        userService.addUser(user);

        authorities role = new authorities();
        role.setRole("ROLE_PATIENT");
        role.setUser(user);
        roleService.addRole(role);
    	
        return ResponseEntity.ok("patient added");
    }
    
    
    // delete patient by patientId
    @DeleteMapping("/{patientId}") 
    public ResponseEntity<String> deletePatientByIdP(@PathVariable int patientId)
    {
        boolean delete = patientService.deletePatientById(patientId);
        if (delete) {
            return ResponseEntity.ok("patient data of id " + patientId + " is deleted successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("patient details of " + patientId + " is not found.");
    }
  
}
