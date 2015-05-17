package me.zhli.web.surveypark.cache;

import java.lang.reflect.Method;

import me.zhli.web.surveypark.util.StringUtil;

import org.springframework.cache.interceptor.KeyGenerator;

/**
 * 自定义缓存 key 生成器
 */
public class SurveyKeyGenerator implements KeyGenerator {
	/**
	 * 
	 */
	@Override
	public Object generate(Object arg0, Method arg1, Object... arg2) {
		String className = arg0.getClass().getSimpleName();
		String name = arg1.getName();
		String params = StringUtil.arr2Str(arg2);
		String key = className + "." + name + "(" + params + ")";
		System.out.println(key);
		return key;
	}

}
