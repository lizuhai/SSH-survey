package me.zhli.web.surveypark.struts2.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import me.zhli.web.surveypark.model.User;
import me.zhli.web.surveypark.model.security.Role;
import me.zhli.web.surveypark.service.RoleService;
import me.zhli.web.surveypark.service.UserService;

@Controller
@Scope("prototype")
public class UserAuthorizeAction extends BaseAction<User> {

	private static final long serialVersionUID = -3116975967803253729L;

	private List<User> allUsers;
	private Integer userId;
	// 用户没有的角色集合
	private List<Role> noOwnRoles;
	// 用户有的角色 id 集合
	private Integer [] ownRoleIds;
	
	@Resource
	private RoleService roleService;
	@Resource
	private UserService userService;
	
	public Integer [] getOwnRoleIds() {
		return ownRoleIds;
	}
	public void setOwnRoleIds(Integer [] ownRoleIds) {
		this.ownRoleIds = ownRoleIds;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public List<Role> getNoOwnRoles() {
		return noOwnRoles;
	}
	public void setNoOwnRoles(List<Role> noOwnRoles) {
		this.noOwnRoles = noOwnRoles;
	}
	public List<User> getAllUsers() {
		return allUsers;
	}
	public void setAllUsers(List<User> allUsers) {
		this.allUsers = allUsers;
	}

	/**
	 * 查询所有用户
	 */
	public String findAllUsers() {
	 	this.allUsers = userService.findAllEntities();
	 	return "userAuthorizeListPage";
	}
	
	/**
	 * 修改角色授权
	 */
	public String editAuthorize() {
		// userId
		this.model = userService.getEntity(userId);
		this.noOwnRoles = roleService.findRolesNotInRange(model.getRoles());
			// 参考角色
		return "editAuthorizePage";
	}
	
	/**
	 * 更新用户授权
	 */
	public String updateAuthorize() {
		// model + ownRoleIds
 		userService.updateAuthorize(model, ownRoleIds);
 		return "findAllUsersAction";
	}
	
	/**
	 * 清除用户授权
	 */
	public String clearAuthorize() {
		// userId
		userService.clearAuthorize(userId);
		return "findAllUsersAction";
	}
}
