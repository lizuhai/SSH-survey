package me.zhli.web.surveypark.datasource;

import me.zhli.web.surveypark.model.Survey;

public class SurveyToken {

	private static ThreadLocal<SurveyToken> l = new ThreadLocal<>();
	
	private Survey survey;
	public Survey getSurvey() {
		return survey;
	}
	public void setSurvey(Survey survey) {
		this.survey = survey;
	}
	
	/**
	 * 将指定的令牌绑定到当前线程
	 */
	public static void bindToken(SurveyToken token) {
		l.set(token);
	}
	
	/**
	 * 解除当前线程绑定的令牌
	 */
	public static void unBindToken(SurveyToken token) {
		l.remove();
	}
	
	/**
	 *  从当前线程获得绑定的令牌
	 */
	public static SurveyToken getCurrentToken() {
		return l.get();
	}
}
