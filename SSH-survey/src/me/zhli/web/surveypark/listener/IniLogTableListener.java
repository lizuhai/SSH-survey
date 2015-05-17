package me.zhli.web.surveypark.listener;

import javax.annotation.Resource;

import me.zhli.web.surveypark.service.LogService;
import me.zhli.web.surveypark.util.LogUtil;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 初始化日志表监听器，在 spring 容器初始化完成后立即调用
 */
@Component
@SuppressWarnings("rawtypes")
public class IniLogTableListener implements ApplicationListener {

	@Resource
	private LogService logService;
	
	@Override
	public void onApplicationEvent(ApplicationEvent arg0) {
		// 上下文刷新事件
		if(arg0 instanceof ContextRefreshedEvent) {
			System.out.println("初始化日志表...");
			String tableName = LogUtil.generateLogTableName(0);
			logService.createLogTable(tableName);
			
			tableName = LogUtil.generateLogTableName(1);
			logService.createLogTable(tableName);
			
			tableName = LogUtil.generateLogTableName(2);
			logService.createLogTable(tableName);
			System.out.println("初始化日志表完成");
		}
	}

}
