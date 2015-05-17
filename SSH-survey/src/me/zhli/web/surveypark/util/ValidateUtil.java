package me.zhli.web.surveypark.util;

import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import me.zhli.web.surveypark.model.User;
import me.zhli.web.surveypark.model.security.Right;
import me.zhli.web.surveypark.struts2.UserAware;
import me.zhli.web.surveypark.struts2.action.BaseAction;

/**
 * 校验工具类
 */
public class ValidateUtil {

	/**
	 * @param src 
	 * 			待检验的字符串
	 * @return
	 * 			如果有效返回 true
	 */
	public static boolean isValidate(String src) {
		return !(src == null || "".equals(src.trim()));
	}
	
	/**
	 * 判断集合的有效性
	 * @param col 
	 * 			要判断的集合
	 * @return
	 * 			集合存在并且不为空返回 true
	 */
	public static boolean isValidate(Collection<?> col) {
		if(col == null || col.isEmpty())
			return false;
		return true;
	}
	
	/**
	 * 判断数组的有效性
	 * @param arr 
	 * 			要判断的数组
	 * @return
	 * 			数组存在并且不为空返回 true
	 */
	public static boolean isValidate(Object [] arr) {
		if(arr == null || arr.length == 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * 判断是否有权限
	 */
	@SuppressWarnings("unchecked")
	public static boolean hasRight(String namespace, String actionName, HttpServletRequest request, BaseAction<?> action) {
		if(!ValidateUtil.isValidate(namespace) || "/".equals(namespace)) {
			namespace = "";
		} 
		// 将超链接所带的参数滤掉
		if(ValidateUtil.isValidate(actionName) && actionName.contains("?")) {
			actionName = actionName.substring(0, actionName.indexOf("?"));
		}
		String url = namespace + "/" + actionName;
		HttpSession session = request.getSession();
		ServletContext sc = session.getServletContext();
		Map<String, Right> map = (Map<String, Right>) sc.getAttribute("all_rights_map");
		Right r = map.get(url);
		// r == null || r.isCommon() 不能写反了，否则会有异常
		if( r == null || r.isCommon()) {
			return true;
		} else {
			User user = (User) session.getAttribute("user");
			// 登陆 ？
			if(user == null) {
				return false;
			} else {
				// userAware 处理
				if(action != null && action instanceof UserAware) {
					((UserAware) action).setUser(user);
				}
				// superAdmin
				if(user.isSuperAdmin()) {
					return true;
				} else {
					// 有权限？
					if(user.hasRight(r)) {
						return true;
					} else {
						return false;
					}
				}
			}
		}
	}
}
