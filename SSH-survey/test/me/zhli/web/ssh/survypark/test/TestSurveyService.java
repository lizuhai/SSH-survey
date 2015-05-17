package me.zhli.web.ssh.survypark.test;

import java.sql.SQLException;

import me.zhli.web.surveypark.model.User;
import me.zhli.web.surveypark.service.SurveyService;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSurveyService {

	private static SurveyService ss;
	
	@SuppressWarnings("resource")
	@BeforeClass
	public static void initUserService() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		ss = (SurveyService) ac.getBean("surveyService");
	}
	
	@Test
	public void insertUser() throws SQLException {
		User user = new User();
		user.setId(4);
		ss.newSurvey(user);
	}
	
	/**
	 * 查询调查
	 */
	@Test
	public void getSurvey() {
		ss.getSurvey(2);
	}
	
}
