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
import com.project.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

private UserService userService;
    
    @Autowired
	public UserController(UserService userService)
	{
		this.userService = userService;
	}
    
    // get all users
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllUserP() {
        List<users> users = userService.getAllUser();
        List<Map<String, Object>> resultList = new ArrayList<>();

        users.forEach(user -> {
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("userId", user.getUser_id());
            userMap.put("username", user.getUsername());
            userMap.put("password", user.getPassword());
            userMap.put("active", user.isActive());

            List<String> authorityIds = new ArrayList<>();
            if (user.getAuthorityList() != null) {
                for (authorities authority : user.getAuthorityList()) {
                    authorityIds.add(authority.getRole());
                }
            }
            userMap.put("authorityList", authorityIds);

            resultList.add(userMap);
        });

        return ResponseEntity.ok(resultList);
    }

    // insert the user
    @PostMapping
    public ResponseEntity<String> addUserP(@RequestBody users user) {
    	
    	userService.addUser(user);
        return ResponseEntity.ok("User added");
    }
    
    
    // get user by userId
    @GetMapping("/{user_id}")
    public ResponseEntity<Map<String, Object>> getUserByIdP(@PathVariable int user_id) {
        users user = userService.getUserById(user_id);
        Map<String, Object> response = new HashMap<>();
        if (user != null) {
            response.put("userId", user.getUser_id());
            response.put("username", user.getUsername());
            response.put("password", user.getPassword());
            response.put("active", user.isActive());
            
            List<String> authorityIds = new ArrayList<>();
            if (user.getAuthorityList() != null) {
                for (authorities authority : user.getAuthorityList()) {
                    authorityIds.add(authority.getRole());
                }
            }
            
            response.put("authorityList", authorityIds);
            
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    
    // delete all users
    @DeleteMapping
    public ResponseEntity<String> deleteAllUserP()
    {
        boolean delete = userService.deleteAllUser();
        if (delete) {
            return ResponseEntity.ok("User's data is deleted successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User's data is not found.");
    }
    
    
    
    // delete user by userId
    @DeleteMapping("/{user_id}") 
    public ResponseEntity<String> deleteUserByIdP(@PathVariable int user_id)
    {
        boolean delete = userService.deleteUserById(user_id);
        if (delete) {
            return ResponseEntity.ok("User data of id " + user_id + " is deleted successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User details of " + user_id + " is not found.");
    }
    
    
    // update user by userId
    @PutMapping("/{user_id}")
    public ResponseEntity<String> updateUserByIdP(@PathVariable int user_id, @RequestBody users user)
    {
        boolean update = userService.updateUserById(user_id, user);
        if (update) {
            return ResponseEntity.ok("User details of " + user_id + " is updated.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User details of " + user_id + " is not found.");
    }
}
