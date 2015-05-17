package me.zhli.web.ssh.survypark.test;

import java.sql.SQLException;

import me.zhli.web.surveypark.model.User;
import me.zhli.web.surveypark.service.UserService;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUserService {

	private static UserService userService;
	
	@SuppressWarnings("resource")
	@BeforeClass
	public static void initUserService() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		userService = (UserService) ac.getBean("userService");
	}
	
	@Test
	public void insertUser() throws SQLException {
		User u = new User();
		u.setEmail("aa@123.com");
		u.setId(1);
		u.setName("li");
		u.setNickName("hehe");
		u.setPassword("123");
		userService.saveEntity(u);
	}
	
}
