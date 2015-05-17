package me.zhli.web.surveypark.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 自定义数据源路由器，用于分布式数据库
 */
public class DataSourceRouter extends AbstractRoutingDataSource {

	/**
	 * 检测当前 key
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		// 得到当前线程绑定的令牌
		SurveyToken token = SurveyToken.getCurrentToken();
		if(token != null) {
			int id = token.getSurvey().getId();
			
			// 解除令牌的绑定
			SurveyToken.unBindToken(token);

			return ((id & 1) != 1) ? "even" : "odd"; 
		}
		return null;
	}

}
