package me.zhli.web.surveypark.service;

import java.util.List;
import java.util.Set;

import me.zhli.web.surveypark.model.security.Right;

/**
 * 权限 service
 */
public interface RightService extends BaseService<Right> {

	/**
	 * save/update right
	 */
	void sageOrUpdateRight(Right model);

	/**
	 * 按照 url 追加权限
	 */
	void appendRightByUrl(String url);

	/**
	 * 批量更新权限
	 */
	void batchUpdateRights(List<Right> allRights);

	/**
	 * 查询在指定范围的权限
	 */
	List<Right> findRightsInPage(Integer[] ids);

	/**
	 * 查询不再指定范围的权限
	 */
	List<Right> findRightsNotInRange(Set<Right> rights);

	/**
	 * 查询最大权限位
	 */
	int getMaxRightPos();

}
