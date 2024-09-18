package com.project.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.entities.users;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class userDAOImpl implements  userDAO{
	
	private EntityManager entityManager;
	
	@Autowired
	public userDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<users> getAllUser() {
		
		TypedQuery<users> theQuery = entityManager.createQuery("from users", users.class);
		
		List<users> users = theQuery.getResultList();
				
		return users;
	}

	@Override
	public void addUser(users user) {
		
		entityManager.persist(user);
	}

	@Override
	public users getUserById(int user_id) {
		
		users user = entityManager.find(users.class, user_id);
		
		return user;
	}

	@Override
	public boolean deleteAllUser() {
		
		int rows = entityManager.createQuery("DELETE FROM users").executeUpdate();
		if(rows != 0)
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteUserById(int user_id) {
		
		users user = entityManager.find(users.class, user_id);
		if(user != null)
		{
			entityManager.remove(user);
			return true;
		}
		
		return false;
	}

	@Override
	public boolean updateUserById(int user_id, users user) {
		
		users existUser = entityManager.find(users.class, user_id);
		
		if(existUser != null)
		{
			entityManager.merge(user);
			return true;
		}
		return false;
	}

}
