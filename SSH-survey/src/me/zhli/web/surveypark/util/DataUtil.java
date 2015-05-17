package me.zhli.web.surveypark.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.util.Set;

import me.zhli.web.surveypark.model.BaseEntity;

/**
 * 数据处理工具类
 */
public class DataUtil {

	/**
	 * 使用 MD5 算法进行加密（32 位）
	 * @param src
	 * 			要用 MD5 算法进行加密的字符串
	 * @return
	 * 			加密过后的字符串
	 */
	public static String md5(String src) {
		try{
			byte[] bytes = src.getBytes();
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] targ = md5.digest(bytes);
			// System.out.println(new String(targ));
			// System.out.println(targ.length);	// 16
			
			char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
			StringBuffer sb = new StringBuffer();
			for (byte b : targ) {
				// 高四位
				sb.append(chars[(b >> 4) & 0x0F]);
				// 低四位
				sb.append(chars[b & 0x0F]);
			}
//			System.out.println(sb.toString());
			return sb.toString();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 深度复制，复制的是整个对象图
	 * @param src
	 * @return
	 */
	public static Serializable deeplyCopy(Serializable src) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(src);
			oos.close();
			baos.close();
			
			byte[] bytes = baos.toByteArray();
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			Serializable copy = (Serializable) ois.readObject();
			ois.close();
			bais.close();
			return copy;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 抽取所有实体 id， 形成字符串
	 */
	public static String extractEntityIds(Set<? extends BaseEntity> entities) {
		String temp = "";
		if(ValidateUtil.isValidate(entities)) {
			for (BaseEntity entity : entities) {
				temp = temp + entity.getId() + ",";
			}
			return temp.substring(0, temp.length() - 1);
		}
		return temp;
	}
}
