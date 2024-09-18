package com.project.repository;

import java.util.List;

import com.project.entities.users;

public interface userDAO {
	
	List<users> getAllUser();

	void addUser(users user);

	users getUserById(int user_id);

	boolean deleteAllUser();

	boolean deleteUserById(int user_id);

	boolean updateUserById(int user_id, users user);
}
