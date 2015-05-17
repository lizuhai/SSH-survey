package me.zhli.web.surveypark.util;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import me.zhli.web.surveypark.service.RightService;

/**
 * 提取所有权限的工具类
 */
public class ExtractAllRightsUtil {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		ClassLoader loader = ExtractAllRightsUtil.class.getClassLoader();
		URL url = loader.getResource("me/zhli/web/surveypark/struts2/action");
		File dir = new File(url.toURI());
		File [] files = dir.listFiles();
		String fileName = "";
		
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		RightService rs = (RightService) ac.getBean("rightService");
		
		for (File file : files) {
			fileName = file.getName();
			if(fileName.endsWith(".class") && !fileName.equalsIgnoreCase("BaseAction.class")) {
//				System.out.println(file.getName());
				processAction(fileName, rs);
			}
		}
	}
	
	/**
	 * 处理 Action 类，捕获所有 url 地址，形成权限
	 */
	public static void processAction(String fileName, RightService rightService) {
		try {
			String pkgName = "me.zhli.web.surveypark.struts2.action";
			String simpleClassName = fileName.substring(0, fileName.indexOf(".class"));
			String className = pkgName + "." + simpleClassName;
//			System.out.println(className);
			// 得到具体类
			Class<?> clazz = Class.forName(className);
			Method [] methods = clazz.getDeclaredMethods();
			Class<?> returnType = null;		// 返回值类型
			String methodName = null;		// 方法名
			Class<?>[] paramType = null;		// 参数类型
			String url = null;
			for (Method method : methods) {
				returnType = method.getReturnType();
				methodName = method.getName();
				paramType = method.getParameterTypes();
				if(returnType == String.class
						&& !ValidateUtil.isValidate(paramType)
						&& Modifier.isPublic(method.getModifiers())) {
					if(methodName.equals("execute")) {
						url = "/" + simpleClassName;
					} else {
						url = "/" + simpleClassName + "_" + methodName;
					}
					rightService.appendRightByUrl(url);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
