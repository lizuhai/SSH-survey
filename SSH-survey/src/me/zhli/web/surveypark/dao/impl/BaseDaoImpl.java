package me.zhli.web.surveypark.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;

import me.zhli.web.surveypark.dao.BaseDao;

@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

	// 注入 sessionFactory
	@Resource
	private SessionFactory sessionFactory;
	
	private Class<T> clazz;
	public BaseDaoImpl() {
		// 得到泛型化的超类
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];
	}
	
	public void saveEntity(T t){
		sessionFactory.getCurrentSession().save(t);
	}
	
	public void updateEntity(T t){
		sessionFactory.getCurrentSession().update(t);
	}
	
	public void saveOrUpdateEntity(T t){
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}
	
	public void deleteEntity(T t){
		sessionFactory.getCurrentSession().delete(t);
	}
	
	public void batchEntityByHQL(String hql, Object ... objects){
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			query.setParameter(i, objects[i]);
		}
		query.executeUpdate();
	}
	
	// read
	public T loadEntity(Integer id){
		return (T) sessionFactory.getCurrentSession().load(clazz, id);
	}
	
	public T getEntity(Integer id){
		return (T) sessionFactory.getCurrentSession().get(clazz, id);
	}
	
	
	public List<T> findEntityByHQL(String hql, Object ... objects){
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		if(objects != null) {
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i, objects[i]);
			}
		}
		return query.list();
	}
	
	public Object uniqueResult(String hql, Object ... objects) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			query.setParameter(i, objects[i]);
		}
		return query.uniqueResult();
	}
	
	// 执行原生 SQL 语句
	@Override
	public void executeSQL(String sql, Object... objects) {
		SQLQuery q = sessionFactory.getCurrentSession().createSQLQuery(sql);
		for (int i = 0; i < objects.length; i++) {
			q.setParameter(i, objects[i]);
		}
		q.executeUpdate();
	}
	
	// 执行 sql 的原生查询 (根据 clazz 是否为空而决定是否封装成一个实体)
	public List<?> executeSQLQuery(Class<?> clazz, String sql, Object ...objects) {
		SQLQuery q = sessionFactory.getCurrentSession().createSQLQuery(sql);
		// 添加实体类
		if(clazz != null) {
			q.addEntity(clazz);
		}
		for (int i = 0; i < objects.length; i++) {
			q.setParameter(i, objects[i]);
		}
		return q.list();
	}
}
