package me.zhli.web.ssh.survypark.test;

import java.sql.SQLException;

import me.zhli.web.surveypark.service.StatisticsService;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestStatisticsService {

	private static StatisticsService ss;
	
	@SuppressWarnings("resource")
	@BeforeClass
	public static void initUserService() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		ss = (StatisticsService) ac.getBean("statisticsService");
	}
	
	@Test
	public void insertUser() throws SQLException {
		ss.statistics(51);
	}
	
}
