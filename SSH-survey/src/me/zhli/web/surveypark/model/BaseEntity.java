package me.zhli.web.surveypark.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 抽象的实体超类，专门用于继承
 */
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = -2468462096026497031L;

	public abstract Integer getId();
	public abstract void setId(Integer id);
	
	/**
	 * 格式为：Entity{attribute1:value,attribute2:value...}
	 */
	@Override
	public String toString() {
		try {
			StringBuffer buffer = new StringBuffer();
			Class<?> clazz = this.getClass();
			String simpleName = clazz.getSimpleName();
			buffer.append(simpleName);
			buffer.append("{");
			// 
			Field [] fs = clazz.getDeclaredFields();
			Class<?> ftype = null;
			String fname = null;
			Object fvalue = null;
			for (Field f : fs) {
				f.setAccessible(true);
				ftype = f.getType();
				fname = f.getName();
				fvalue = f.get(this);
				// 是否是基本数据类型
				if((ftype.isPrimitive() 
						|| ftype == Integer.class
						|| ftype == Long.class
						|| ftype == Short.class
						|| ftype == Boolean.class
						|| ftype == Character.class
						|| ftype == Double.class
						|| ftype == Float.class
						|| ftype == String.class)
						&& !Modifier.isStatic(f.getModifiers())) {
					buffer.append(fname);
					buffer.append(":");
					buffer.append(fvalue);
					buffer.append(",");
				}
			}
			buffer.deleteCharAt(buffer.length() - 1);
			buffer.append("}");
			return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
