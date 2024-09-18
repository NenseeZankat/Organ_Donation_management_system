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

import com.project.entities.authorities;
import com.project.entities.users;
import com.project.service.RoleService;
import com.project.service.UserService;

@RestController
@RequestMapping("/roles")
public class RoleController {
	
	private RoleService roleService;
	private UserService userService;
	
	@Autowired
	public RoleController(RoleService roleService, UserService userService)
	{
		this.roleService = roleService;
		this.userService = userService;
	}
    
	
	// get all roles 
	@GetMapping
	public ResponseEntity<List<Map<String, Object>>> getAllRoleP() {
	    List<authorities> roles = roleService.getAllRoles();
	    List<Map<String, Object>> resultList = new ArrayList<>();

	    roles.forEach(role -> {
	        Map<String, Object> roleMap = new HashMap<>();
	        roleMap.put("role", role.getRole());
	        roleMap.put("userId", (role.getUser() != null) ? role.getUser().getUser_id() : null);
	        resultList.add(roleMap);
	    });

	    return ResponseEntity.ok(resultList);
	}
	
	// update role details by user id
	@PutMapping("/{user_id}")
    public ResponseEntity<String> updateRoleByIdP(@PathVariable int user_id, @RequestBody authorities role)
    {
        boolean update = roleService.updateRoleById(user_id, role);
        if (update) {
            return ResponseEntity.ok("Role details of " + user_id + " is updated.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role details of " + user_id + " is not found.");
    }

    
	// insert role
    @PostMapping
    public ResponseEntity<String> addRoleP(@RequestBody authorities role) {
    	
    	int user_id = role.getUser().getUser_id(); 
    	users user = userService.getUserById(user_id);
    	
    	role.setUser(user);
    	
        roleService.addRole(role);
        return ResponseEntity.ok("role added");
    }
    

    // delete all roles
    @DeleteMapping
    public ResponseEntity<String> deleteAllRoleP()
    {
        boolean delete = roleService.deleteAllRole();
        if (delete) {
            return ResponseEntity.ok("User's data is deleted successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User's data is not found.");
    }
    
    // delete role by userId
    @DeleteMapping("/{user_id}") 
    public ResponseEntity<String> deleteRoleByIdP(@PathVariable int user_id)
    {
        boolean delete = roleService.deleteRoleById(user_id);
        if (delete) {
            return ResponseEntity.ok("User data of id " + user_id + " is deleted successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User details of " + user_id + " is not found.");
    }
    

}
