package me.zhli.web.surveypark.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import me.zhli.web.surveypark.model.security.Right;
import me.zhli.web.surveypark.service.RightService;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

/**
 * 初始化权限监听器，在 spring 容器初始化完成后立即调用
 */
@Component
@SuppressWarnings("rawtypes")
public class IniRightListener implements ApplicationListener, ServletContextAware {

	@Resource
	private RightService rightService;
	// 接收 servletContext
	private ServletContext sc;
	
	@Override
	public void onApplicationEvent(ApplicationEvent arg0) {
		// 上下文刷新事件
		if(arg0 instanceof ContextRefreshedEvent) {
			// 查询
			List<Right> rights = rightService.findAllEntities();
			Map<String, Right> map = new HashMap<>();
			for (Right right : rights) {
				map.put(right.getRightUrl(), right);
			}
			if(sc != null) {
				sc.setAttribute("all_rights_map", map);
				System.out.println("初始化所有权限到 application 中。");
			}
		}
	}

	/**
	 * 注入所有 ServletContext
	 */
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.sc = servletContext;
	}

}
