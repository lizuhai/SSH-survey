package me.zhli.web.surveypark.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import me.zhli.web.surveypark.model.Page;
import me.zhli.web.surveypark.model.Question;
import me.zhli.web.surveypark.model.Survey;

public class App {

	/**
	 * 测试序列化
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		Survey s1 = new Survey();
		s1.setTitle("s1");
		
		Page p1 = new Page();
		p1.setTitle("p1");
		
		Question q1 = new Question();
		q1.setTitle("q1");
		
		Question q2 = new Question();
		q1.setTitle("q2");
		
		p1.setSurvey(s1);
		p1.getQuestions().add(q1);
		p1.getQuestions().add(q2);
		
		//-----------------------------
		Page newPage = new Page();
		newPage.setSurvey(p1.getSurvey());
		newPage.setQuestions(p1.getQuestions());
		newPage.setTitle(p1.getTitle());
		
		System.out.println("通过调试查看两个的哈希码");	// 不是深度复制，survey、question、title 的 hashCode 一样，说明只是增加了对象的引用
		
		// 串行化实现深度复制
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(p1);
		oos.close();
		baos.close();
		
		byte[] bytes = baos.toByteArray();
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		ObjectInputStream ois = new ObjectInputStream(bais);
		Page copy = (Page) ois.readObject();
		ois.close();
		bais.close();
		
		System.out.println(copy);
	}
}
