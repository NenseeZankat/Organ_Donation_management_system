package com.project.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class authorities {

	
	@Column(name="role")
    private String role;
	
	@Id
	@ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
	private users user;


	public authorities( String role, users user) {
		super();
		
		this.role = role;
		this.user = user;
	}


	public authorities() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public users getUser() {
		return user;
	}


	public void setUser(users user) {
		this.user = user;
	}
	
	
	


}
