package me.zhli.web.surveypark.service;

import me.zhli.web.surveypark.model.statistics.QuestionStatisticsModel;

/**
 * 统计服务
 */
public interface StatisticsService {

	public QuestionStatisticsModel statistics(Integer qid);
	
}
