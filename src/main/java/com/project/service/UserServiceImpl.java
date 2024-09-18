package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.project.entities.authorities;
import com.project.entities.users;
import com.project.repository.userDAO;

@Service
public class UserServiceImpl implements UserService{

	
	private userDAO userRepo;

	@Autowired 
	public UserServiceImpl(@Qualifier("userDAOImpl") userDAO userRepo)
	{
		this.userRepo = userRepo;
	}
	
	@Override
	@Transactional
	public List<users> getAllUser() {
		
		List<users> users = userRepo.getAllUser();
		return users;
	}

	@Override
	@Transactional
	public void addUser(users user) {
		
		for(authorities role : user.getAuthorityList())
		{
			role.setUser(user);
		}
		user.setAuthorityList(user.getAuthorityList());
		userRepo.addUser(user);
		
	}

	@Override
	@Transactional
	public users getUserById(int user_id) {
		users user = userRepo.getUserById(user_id);
		if(user != null)
		{
			return user;
		}
		return null;
	}

	@Override
	@Transactional
	public boolean deleteAllUser() {
		
		boolean res = userRepo.deleteAllUser();
		if(res)
		{
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public boolean deleteUserById(int user_id) {
		boolean res = userRepo.deleteUserById(user_id);
		if(res)
		{
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public boolean updateUserById(int user_id, users user) {
	
		boolean res = userRepo.updateUserById(user_id, user);
		if(res)
		{
			return true;
		}

	    return false;
	}

}
