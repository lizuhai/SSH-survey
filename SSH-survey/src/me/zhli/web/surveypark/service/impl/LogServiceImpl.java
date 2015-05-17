package me.zhli.web.surveypark.service.impl;

import java.util.List;

import javax.annotation.Resource;

import me.zhli.web.surveypark.dao.BaseDao;
import me.zhli.web.surveypark.model.Log;
import me.zhli.web.surveypark.service.LogService;
import me.zhli.web.surveypark.util.LogUtil;

import org.hibernate.id.UUIDHexGenerator;
import org.springframework.stereotype.Service;

@Service("logService")
public class LogServiceImpl<T> extends BaseServiceImpl<Log> implements LogService {
	
	@Resource(name="logDao")
	public void setDao(BaseDao<Log> dao) {
		super.setDao(dao);
	}

	/**
	 * 通过表名创建日志表
	 */
	@Override
	public void createLogTable(String tableName) {
		String sql = "create table if not exists " + tableName + " like logs";
		this.executeSQL(sql);
	}

	/**
	 * 重写该方法，动态插入日志记录（动态表）
	 */
	@Override
	public void saveEntity(Log t) {
		String sql = "insert into " + LogUtil.generateLogTableName(0) + 
				"(id, operator,operName,operParams,operResult,resultMsg,operTime) values(?,?,?,?,?,?,?)";
		UUIDHexGenerator uuid = new UUIDHexGenerator();
		String id = (String) uuid.generate(null, null);
		this.executeSQL(sql, id, t.getOperator(), t.getOperator(), t.getOperParams(), t.getOperResult(), t.getResultMsg(), t.getOperTime());
	}

	/*
	 * 查询最近指定月份数的日志，默认值为2
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Log> findNearestLogs(int n) {
		String tableName = LogUtil.generateLogTableName(0);
		// 查出最近的日志表名称
		String sql = "select table_name from information_schema.tables "
				+ "where table_schema='surveypark' and table_name like 'logs_%' and table_name <= '" + 
				tableName + "' order by table_name desc limit 0," + n;
		List<?> list = this.executeSQLQuery(null, sql);
		// 查询最近若干月的日志
		String logSql = "";
		String logTableName = null;
		for (int i = 0; i < list.size(); i++) {
			logTableName = (String) list.get(i);
			if(i == list.size() - 1) {
				logSql = logSql + "select * from " + logTableName;
			} else {
				logSql = logSql + "select * from " + logTableName + " union ";
			}
		}
		//指定 Log 实体类
		return (List<Log>) this.executeSQLQuery(Log.class, logSql);
	}
}
