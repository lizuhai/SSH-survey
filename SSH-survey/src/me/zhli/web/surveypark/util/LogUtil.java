package me.zhli.web.surveypark.util;

import java.text.DecimalFormat;
import java.util.Calendar;

public class LogUtil {

	/**
	 * 生成日志表名称: logs_2014_12
	 * @param offset
	 * 				偏移量：0：当前月
	 */
	public static String generateLogTableName(int offset) {
		Calendar c = Calendar.getInstance();
		// 2014
		int year = c.get(Calendar.YEAR);
		// 0-11: c.get(Calendar.MONTH)
		int month = c.get(Calendar.MONTH) + 1 + offset;
		if(month > 12) {
			year ++ ;
			month -= 12;
		}
		if(month < 1) {
			year --;
			month += 12;
		}
		
		// 格式化月份成两位数
		DecimalFormat df = new DecimalFormat();
		df.applyPattern("00");
		
		return "logs_" + year + "_" + df.format(month);
	}
	
}
