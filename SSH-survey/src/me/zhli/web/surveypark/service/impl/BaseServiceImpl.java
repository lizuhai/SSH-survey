package me.zhli.web.surveypark.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import me.zhli.web.surveypark.dao.BaseDao;
import me.zhli.web.surveypark.service.BaseService;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

	private BaseDao<T> dao;
	
	private Class<T> clazz;
	
	@SuppressWarnings("unchecked")
	public BaseServiceImpl() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];
	}
	
	// 注入dao
	@Resource
	public void setDao(BaseDao<T> dao) {
		this.dao = dao;
	}
	
	@Override
	public void saveEntity(T t) {
		dao.saveEntity(t);
	}

	@Override
	public void updateEntity(T t) {
		dao.updateEntity(t);
	}

	@Override
	public void saveOrUpdateEntity(T t) {
		dao.saveOrUpdateEntity(t);
	}

	@Override
	public void deleteEntity(T t) {
		dao.deleteEntity(t);
	}

	@Override
	public void batchEntityByHQL(String hql, Object... objects) {
		dao.batchEntityByHQL(hql, objects);
	}

	@Override
	public T loadEntity(Integer id) {
		return dao.loadEntity(id);
	}

	@Override
	public T getEntity(Integer id) {
		return dao.getEntity(id);
	}

	@Override
	public List<T> findEntityByHQL(String hql, Object... objects) {
		return dao.findEntityByHQL(hql, objects);
	}
	
	public Object uniqueResult(String hql, Object ... objects) {
		return dao.uniqueResult(hql, objects);
	}
	
	/**
	 * 查询所有实体
	 */
	public List<T> findAllEntities() {
		String hql = "from " + clazz.getSimpleName();
		return this.findEntityByHQL(hql);
	}
	
	// 执行原生 SQL 语句
	@Override
	public void executeSQL(String sql, Object... objects) {
		dao.executeSQL(sql, objects);
	}
	
	// 执行 sql 的原生查询 (根据 clazz 是否为空而决定是否封装成一个实体)
	public List<?> executeSQLQuery(Class<?> clazz, String sql, Object ...objects) {
		return dao.executeSQLQuery(clazz, sql, objects);
	}
}
