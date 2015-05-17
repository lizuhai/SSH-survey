package me.zhli.web.surveypark.service;

import java.util.List;
import java.util.Set;

import me.zhli.web.surveypark.model.security.Role;

/**
 * 角色 service
 */
public interface RoleService extends BaseService<Role> {

	void saveOrUpdateRole(Role model, Integer [] ownRightIds);

	List<Role> findRolesNotInRange(Set<Role> roles);

	List<Role> findRolesInRange(Integer[] roleIds);
	
}
