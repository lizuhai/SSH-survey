package me.zhli.web.surveypark.dao;

import java.util.List;
/**
 * BaseDao
 * @param <T>
 */
public interface BaseDao<T> {

	// write
	public void saveEntity(T t);
	public void updateEntity(T t);
	public void saveOrUpdateEntity(T t);
	public void deleteEntity(T t);
	
	public void batchEntityByHQL(String hql, Object ... objects);
	
	// read
	public T loadEntity(Integer id);
	public T getEntity(Integer id);
	public List<T> findEntityByHQL(String hql, Object ... objects);
	// 单值检索，检索结果有且只有一条记录
	public Object uniqueResult(String hql, Object ... objects);
	
	// 执行原生的 sql 语句
	public void executeSQL(String sql, Object ... objects);
	
	// 执行 sql 的原生查询 (根据 clazz 是否为空而决定是否封装成一个实体)
	public List<?> executeSQLQuery(Class<?> clazz, String sql, Object ...objects);
}
