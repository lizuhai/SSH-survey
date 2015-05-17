package me.zhli.web.surveypark.scheduler;

import me.zhli.web.surveypark.service.LogService;
import me.zhli.web.surveypark.util.LogUtil;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 创建日志表的石英任务
 */
public class CreateLogTablesTask extends QuartzJobBean {

	// 注入 logService
	private LogService logService;
	
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	// 生成日志表
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// 一般生成 3 个月的日志表
		String tableName = LogUtil.generateLogTableName(1);
		logService.createLogTable(tableName);
		
		tableName = LogUtil.generateLogTableName(2);
		logService.createLogTable(tableName);
		
		tableName = LogUtil.generateLogTableName(3);
		logService.createLogTable(tableName);
		
	}

}
