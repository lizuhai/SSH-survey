package me.zhli.web.surveypark.service;

import me.zhli.web.surveypark.model.User;

/**
 * 
 */
public interface UserService extends BaseService<User> {

	/**
	 * 验证注册信息，判断 email 是否被占用
	 * @param 
	 *    		验证字符串是否已存在于数据库中	
	 * @return 
	 * 			true if already used
	 */
	public boolean isRegisted(String email);

	/**
	 * 验证登陆信息
	 */
	public User validateLoginInfo(String email, String md5);

	public void updateAuthorize(User model, Integer[] ownRoleIds);

	public void clearAuthorize(Integer userId);
	
	
}
