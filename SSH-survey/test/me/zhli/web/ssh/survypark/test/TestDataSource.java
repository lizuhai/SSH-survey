package me.zhli.web.ssh.survypark.test;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDataSource {

	@SuppressWarnings("resource")
	@Test
	public void getConnection() throws SQLException {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		DataSource dataSource = (DataSource) ac.getBean("dataSource");
		System.out.println(dataSource.getConnection());
	}
	
}
