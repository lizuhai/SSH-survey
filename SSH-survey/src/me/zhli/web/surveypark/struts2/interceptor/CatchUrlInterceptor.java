package me.zhli.web.surveypark.struts2.interceptor;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import me.zhli.web.surveypark.service.RightService;
import me.zhli.web.surveypark.util.ValidateUtil;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * 捕获 Url 拦截器
 *
 */
public class CatchUrlInterceptor implements Interceptor {

	private static final long serialVersionUID = 7216456208199124880L;

	@Override
	public void destroy() {
		
	}

	@Override
	public void init() {
		
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionProxy proxy = invocation.getProxy();
		String namespace = proxy.getNamespace();
		String actionName = proxy.getActionName();
		if(ValidateUtil.isValidate(namespace) || namespace.equals("/")) {
			namespace = "";
		}
		String url = namespace + "/" + actionName;
		
		// 取得在 application 中的 Spring 容器
		// 方法 1
//		ApplicationContext ac = (ApplicationContext) invocation.getInvocationContext().getApplication().get(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		
		// 方法 2
		ServletContext sc = ServletActionContext.getServletContext();
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sc);
		
		RightService rs = (RightService) ac.getBean("rightService");
		
		rs.appendRightByUrl(url);
		return invocation.invoke();
	}

}
