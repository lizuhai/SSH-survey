package me.zhli.web.surveypark.service;

import java.util.List;

import me.zhli.web.surveypark.model.Log;

/**
 * LogService
 */
public interface LogService extends BaseService<Log> {

	void createLogTable(String tableName);

	/*
	 * 查询最近指定月份数的日志，默认值为2
	 */
	List<Log> findNearestLogs(int i);

	
}
