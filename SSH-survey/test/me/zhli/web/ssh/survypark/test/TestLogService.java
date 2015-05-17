package me.zhli.web.ssh.survypark.test;

import java.sql.SQLException;

import me.zhli.web.surveypark.service.LogService;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestLogService {

	private static LogService logService;
	
	@SuppressWarnings("resource")
	@BeforeClass
	public static void initUserService() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		logService = (LogService) ac.getBean("logService");
	}
	
	@Test
	public void test() throws SQLException {
		logService.findNearestLogs(3);
	}
	
}
