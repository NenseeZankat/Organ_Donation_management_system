package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.project.entities.authorities;

import jakarta.transaction.Transactional;
import com.project.repository.roleDAO;

@Service
public class RoleServiceImpl implements RoleService{

	private roleDAO roleRepo;

	@Autowired
	public RoleServiceImpl(@Qualifier("roleDAOImpl") roleDAO roleRepo)
	{
		this.roleRepo = roleRepo;
	}
	
	@Override
	@Transactional
	public List<authorities> getAllRoles() {
		List<authorities> roles = roleRepo.getAllRoles();
		return roles;
	}

	@Override
	@Transactional
	public void addRole(authorities role) {
		roleRepo.addRole(role);     
		
	}
	
	
	@Override
	@Transactional
	public boolean deleteAllRole() {
		boolean res = roleRepo.deleteAllRole();
		if(res)
		{
			return true;
		}
		return false;
	}

	
	@Override
	@Transactional
	public boolean updateRoleById(int user_id, authorities role) {
	
		boolean res = roleRepo.updateRoleById(user_id, role);
		if(res)
		{
			return true;
		}

	    return false;
	}

	 
	
	@Override
	@Transactional
	public boolean deleteRoleById(int user_id)
	{
		boolean res = roleRepo.deleteRoleById(user_id);
		if(res)
		{
			return true;
		}
		
		return false;
	}


}
