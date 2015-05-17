package me.zhli.web.surveypark.struts2.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import me.zhli.web.surveypark.model.User;
import me.zhli.web.surveypark.service.RightService;
import me.zhli.web.surveypark.service.UserService;
import me.zhli.web.surveypark.util.DataUtil;

/**
 * 登陆 action
 */
@Controller
@Scope("prototype")
public class LoginAction extends BaseAction<User> implements SessionAware {
	
	private static final long serialVersionUID = 7549008851509272285L;
	
	/**
	 * 到达登陆页面
	 */
	public String toLoginPage() {
		return "loginPage";
	}
	
	// 不加注解的话会报空指针异常
	@Resource
	private UserService userService;
	private Map<String , Object> sessionMap;
	@Resource
	private RightService rightService;
	
	public void validateDoLogin() {	// 这样的命名方式表示只校验 doLogin 方法
		User user = userService.validateLoginInfo(model.getEmail(), DataUtil.md5(model.getPassword()));
		if(user == null) {
			addActionError("email OR password 错误！");
		} else {
			// 初始化权限总和数组
			int maxPos = rightService.getMaxRightPos();
			user.setRightSum(new long[maxPos + 1]);
			// 计算用户的权限总和
			user.calculateRightSum();
			
//			ServletActionContext.getRequest().getSession().setAttribute("user", user);
			// 不用上面的方法，耦合度太高，实现 sessionAware 接口
			sessionMap.put("user", user);
		}
	}
	
	public String doLogin() {
		return SUCCESS;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.sessionMap = arg0;
	}
	
}
