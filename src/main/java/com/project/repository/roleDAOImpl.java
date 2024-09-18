package com.project.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.entities.authorities;
import com.project.entities.users;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class roleDAOImpl implements roleDAO{

	private EntityManager entityManager;

	@Autowired
	public roleDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<authorities> getAllRoles() {
		TypedQuery<authorities> theQuery = entityManager.createQuery("from authorities", authorities.class);
		
		List<authorities> authorities = theQuery.getResultList();
				
		return authorities;
	}

	@Override
	public void addRole(authorities role) {
		entityManager.persist(role);
		
	}


	@Override
	public boolean deleteAllRole() {
		int rows = entityManager.createQuery("DELETE FROM authorities").executeUpdate();
		if(rows != 0)
		{
			return true;
		}
		return false;
	}


	
	@Override
	public boolean updateRoleById(int user_id, authorities role) {
	    
	    users user = entityManager.find(users.class, user_id);
	    
	    if(user != null) {
	       
	    	role.setUser(user);
	        entityManager.merge(role);
	        
	        return true;
	    }
	    return false;
	}

	@Override
	public boolean deleteRoleById(int user_id) {
	    
	    authorities role = entityManager.find(authorities.class, user_id);
	    
	    if (role != null) {
	      
	        entityManager.remove(role);
	        return true;
	    }
	    return false;
	}

	


}
