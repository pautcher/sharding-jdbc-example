package com.bz.open.sharding.ucenter.dao;

import java.io.Serializable;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

public class HibernateInterceptor extends EmptyInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6948606695119204412L;

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {

		return super.onSave(entity, id, state, propertyNames, types);
	}

	@Override
	public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {

		super.onDelete(entity, id, state, propertyNames, types);
	}

	@Override
	public Object getEntity(String entityName, Serializable id) {

		Object o = super.getEntity(entityName, id);

		return o;
	}

	@Override
	public String onPrepareStatement(String sql) {
		return super.onPrepareStatement(sql);
	}

}
