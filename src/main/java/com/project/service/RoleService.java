package com.project.service;

import java.util.List;

import com.project.entities.authorities;

public interface RoleService {
	List<authorities> getAllRoles();

	void addRole(authorities role);


	boolean deleteAllRole();

	boolean updateRoleById(int user_id, authorities role);

	boolean deleteRoleById(int user_id);

}
