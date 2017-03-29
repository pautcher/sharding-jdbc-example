package com.bz.open.sharding.ucenter.dao.impl;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;

/**
 * 
 * @author zhangyuxin85@gmail.com
 *
 * @param <T>
 */
public abstract class BaseDAOImpl<T> {

	Logger log = LoggerFactory.getLogger(BaseDAOImpl.class);

	private Class<T> entityClass;

	@Resource(name = "hibernateTemplate")
	private HibernateTemplate hibernateTemplate;

	@SuppressWarnings("unchecked")
	public BaseDAOImpl() {
		ParameterizedType type = (ParameterizedType) (getClass().getGenericSuperclass());
		entityClass = (Class<T>) type.getActualTypeArguments()[0];
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings("unchecked")
	public T get(final java.io.Serializable id) {

		try {

			return getHibernateTemplate().get(entityClass, id);

		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			return null;

		}
	}

	@SuppressWarnings("unchecked")
	public T save(final T t) {

		try {
			getHibernateTemplate().save(t);
			return t;
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			return null;
		}
	}

	public T saveOrUpdate(final T t) {

		try {

			getHibernateTemplate().saveOrUpdate(t);
			return t;
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			return null;

		}
	}

	public T update(final T t) {

		try {

			getHibernateTemplate().update(t);
			return t;

		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			return null;

		}
	}

	public void delete(final T t) {

		try {
			getHibernateTemplate().delete(t);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			return;

		}
	}

}
