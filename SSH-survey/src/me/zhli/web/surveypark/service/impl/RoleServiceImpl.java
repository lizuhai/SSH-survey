package me.zhli.web.surveypark.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import me.zhli.web.surveypark.dao.BaseDao;
import me.zhli.web.surveypark.model.security.Right;
import me.zhli.web.surveypark.model.security.Role;
import me.zhli.web.surveypark.service.RightService;
import me.zhli.web.surveypark.service.RoleService;
import me.zhli.web.surveypark.util.DataUtil;
import me.zhli.web.surveypark.util.StringUtil;
import me.zhli.web.surveypark.util.ValidateUtil;

@Service("roleService")
public class RoleServiceImpl<T> extends BaseServiceImpl<Role> implements RoleService {
	
	@Resource
	private RightService rightService;
	
	@Resource(name="roleDao")
	public void setDao(BaseDao<Role> dao) {
		super.setDao(dao);
	}

	/**
	 * 保存/更新角色
	 */
	@Override
	public void saveOrUpdateRole(Role model, Integer[] ids) {
		// 没有为角色授予任何权限
		if(!ValidateUtil.isValidate(ids)) {
			model.getRights().clear();
		} else {
			List<Right> rights = rightService.findRightsInPage(ids);
			model.setRights(new HashSet<Right>(rights));
		}
		this.saveOrUpdateEntity(model);
	}

	/**
	 * 查询不在指定集合的角色集合
	 */
	@Override
	public List<Role> findRolesNotInRange(Set<Role> roles) {
		if(!ValidateUtil.isValidate(roles)) {
			return this.findAllEntities();
		} else {
//			System.out.println(roles);
			String hql = "from Role r where r.id not in(" + DataUtil.extractEntityIds(roles) + ")";
			return this.findEntityByHQL(hql);
		}
	}
	
	/**
	 * 查询指定范围内的角色集合
	 */
	@Override
	public List<Role> findRolesInRange(Integer[] ids) {
		if(ValidateUtil.isValidate(ids)) {
			String hql = "from Role r where r.id in (" + StringUtil.arr2Str(ids) + ")";
			return findEntityByHQL(hql);
		}
		return null;
	}
}
