package me.zhli.web.surveypark.service.impl;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import me.zhli.web.surveypark.dao.BaseDao;
import me.zhli.web.surveypark.model.User;
import me.zhli.web.surveypark.model.security.Role;
import me.zhli.web.surveypark.service.RoleService;
import me.zhli.web.surveypark.service.UserService;
import me.zhli.web.surveypark.util.ValidateUtil;

@Service("userService")
public class UserServiceImpl<T> extends BaseServiceImpl<User> implements UserService {
	
	@Resource
	private RoleService roleService;

	@Resource(name="userDao")
	public void setDao(BaseDao<User> dao) {
		super.setDao(dao);
	}
	
	/**
	 * 判断 email 是否被占用
	 */
	public boolean isRegisted(String email) {
		String hql = "from User u where u.email = ?";
		List<User> list = this.findEntityByHQL(hql, email);
		return ValidateUtil.isValidate(list);
	}

	/**
	 * 验证登陆信息
	 */
	@Override
	public User validateLoginInfo(String email, String md5) {
		String hql = "from User u where u.email = ? and u.password = ?";
		List<User> list = this.findEntityByHQL(hql, email, md5);
		return ValidateUtil.isValidate(list) ? list.get(0) : null;
	}

	@Override
	public void updateAuthorize(User user, Integer [] roleIds) {
		// 任何人没有权限修改用户数据。一定要查询在db中的新用户，否则会将用户的信息改掉
		User newUser = this.getEntity(user.getId());
		if(!ValidateUtil.isValidate(roleIds)) {
			newUser.getRoles().clear();
		} else {
			List<Role> roles = roleService.findRolesInRange(roleIds);
			newUser.setRoles(new HashSet<Role>(roles));
		}
	}

	@Override
	public void clearAuthorize(Integer userId) {
		this.getEntity(userId).getRoles().clear();
	}

}
