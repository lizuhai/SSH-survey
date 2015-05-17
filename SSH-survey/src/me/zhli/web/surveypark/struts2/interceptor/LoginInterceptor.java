package me.zhli.web.surveypark.struts2.interceptor;

import me.zhli.web.surveypark.model.User;
import me.zhli.web.surveypark.struts2.UserAware;
import me.zhli.web.surveypark.struts2.action.BaseAction;
import me.zhli.web.surveypark.struts2.action.LoginAction;
import me.zhli.web.surveypark.struts2.action.RegAction;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LoginInterceptor implements Interceptor {
	private static final long serialVersionUID = -3325615090139345137L;

	@Override
	public void destroy() {
		
	}

	@Override
	public void init() {
		
	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		BaseAction<?> action = (BaseAction<?>) arg0.getAction();
		if(action instanceof LoginAction || action instanceof RegAction) {
			// 放行
			return arg0.invoke();
		} else {
			User user = (User) arg0.getInvocationContext().getSession().get("user");
			if(user == null) {
				// 去登陆
				return "login";
			} else {
				// 放行
				if(action instanceof UserAware) {
					// 注入 user 给 action 
					((UserAware) action).setUser(user);
				}
				return arg0.invoke();
			}
		}
	}

}
