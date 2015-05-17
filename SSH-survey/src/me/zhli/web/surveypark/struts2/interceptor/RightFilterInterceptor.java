package me.zhli.web.surveypark.struts2.interceptor;

import org.apache.struts2.ServletActionContext;

import me.zhli.web.surveypark.struts2.action.BaseAction;
import me.zhli.web.surveypark.util.ValidateUtil;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class RightFilterInterceptor implements Interceptor {
	private static final long serialVersionUID = -3325615090139345137L;

	@Override
	public void destroy() {
		
	}

	@Override
	public void init() {
		
	}

	/**
	 * 	i. 用户点击链接 --> 拦截器中
	 *	ii. url -- 查询 --> Right
	 *	iii. 对 Right 对象进行判断
	 *		if(是不是公共资源) {
	 *			// 放行
	 *		} else {
	 *			if(登陆？) {
	 *				if(有权限没) {
	 *					// 放行
	 *				} else {
	 *					// error page
	 *				}
	 *			} else {
	 *				// login
	 *			}
	 *		}
	 */
	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		ActionProxy proxy = arg0.getProxy();
		String namespace = proxy.getNamespace();
		String actionName = proxy.getActionName();
		BaseAction<?> action = (BaseAction<?>) arg0.getAction();
		
		if(ValidateUtil.hasRight(namespace, actionName, ServletActionContext.getRequest(), action)) {
			return arg0.invoke();
		} else {
			return "login";
		}
	}

}
