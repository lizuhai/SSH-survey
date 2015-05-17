package me.zhli.web.surveypark.util;

/**
 * 字符串工具类
 */
public class StringUtil {
	/**
	 * 将字符串转换成数组，按照 tag 分割
	 */
	public static String[] str2Arr(String str, String tag) {
		if(ValidateUtil.isValidate(str)) {
			return str.split(tag);
		}
		return null;
	}

	/**
	 * 判断 values 数组中是否含有指定的 vlaue
	 */
	public static boolean contais(String[] values, String value) {
		if(ValidateUtil.isValidate(values)) {
			for (String s : values) {
				if(s.equals(value))
					return true;
			}
		}
		return false;
	}

	/**
	 * 将数组转化成字符串, 使用 "," 分割
  	 */
	public static String arr2Str(Object[] arr) {
		String temp = "";
		if(ValidateUtil.isValidate(arr)) {
			for (Object s : arr) {
				temp = temp + s + ",";
			}
			return temp.substring(0, temp.length() - 1);
		}
		return temp;
	}
	
	
	/**
	 * 获得一个指定字符串的描述信息
	 */
	public static String getDescString(String str) {
		if(str != null && str.trim().length() > 30) {
			return str.substring(0, 30);
		}
		return str;
	}
}
