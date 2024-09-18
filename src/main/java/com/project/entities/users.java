package com.project.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class users {
	

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="active")	
	private boolean active;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<authorities> authorityList = new ArrayList<>() ;

	public users() {
		super();
		
	}

	public users(int user_id, String username, String password, boolean active, List<authorities> authorityList) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.active = active;
		this.authorityList = authorityList;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<authorities> getAuthorityList() {
		return authorityList;
	}

	public void setAuthorityList(List<authorities> authorityList) {
		this.authorityList = authorityList;
	}
	
	
	
}
