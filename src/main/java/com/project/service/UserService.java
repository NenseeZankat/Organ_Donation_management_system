package com.project.service;

import java.util.List;

import com.project.entities.users;

public interface UserService {

	List<users> getAllUser();

	void addUser(users user);

	users getUserById(int user_id);

	boolean deleteAllUser();

	boolean deleteUserById(int user_id);

	boolean updateUserById(int user_id, users user);

}
