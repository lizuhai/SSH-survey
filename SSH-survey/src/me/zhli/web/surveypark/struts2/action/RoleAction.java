package me.zhli.web.surveypark.struts2.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import me.zhli.web.surveypark.model.security.Right;
import me.zhli.web.surveypark.model.security.Role;
import me.zhli.web.surveypark.service.RightService;
import me.zhli.web.surveypark.service.RoleService;

/**
 * 角色管理
 */
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

	private static final long serialVersionUID = 8594301964616474999L;

	private List<Role> allRoles;
	private List<Right> noOwnRights;
	private Integer roleId;
	private Integer[] ownRightIds;
	
	@Resource
	private RoleService roleService;
	@Resource
	private RightService rightService;

	public Integer[] getOwnRightIds() {
		return ownRightIds;
	}
	public void setOwnRightIds(Integer [] ownRightIds) {
		this.ownRightIds = ownRightIds;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public List<Role> getAllRoles() {
		return allRoles;
	}
	public void setAllRoles(List<Role> allRoles) {
		this.allRoles = allRoles;
	}
	public List<Right> getNoOwnRights() {
		return noOwnRights;
	}
	public void setNoOwnRights(List<Right> noOwnRights) {
		this.noOwnRights = noOwnRights;
	}
	/**
	 * 查询所有的 Role
	 */
	public String findAllRoles() {
		this.allRoles = roleService.findAllEntities();
		return "roleListPage";
	}
	
	/**
	 * 添加角色
	 */
	public String toAddRolePage() {
		this.noOwnRights = rightService.findAllEntities();
		return "addRolePage";
	}
	
	/**
	 * 保存更新角色
	 */
	public String saveOrUpdateRole() {
//		System.out.println(model);
//		for (int i = 0; i < ownRightIds.length; i++) {
//			System.out.println(ownRightIds[i]);
//		}
		roleService.saveOrUpdateRole(model, ownRightIds);
		return "findAllRolesAction";
	}
	
	/**
	 * 修改角色
	 */
	public String editRole() {
		// roleId
		this.model = roleService.getEntity(roleId);
		this.noOwnRights = rightService.findRightsNotInRange(model.getRights());
		return "editRolePage";
	}
	
	/**
	 * delete 角色
	 */
	public String deleteRole() {
		// roleId
		Role r = new Role();
		r.setId(roleId);
		roleService.deleteEntity(r);
		return "findAllRolesAction";
	}
}
