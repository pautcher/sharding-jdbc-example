package com.bz.open.sharding.ucenter.dao.impl;

import org.springframework.stereotype.Repository;

import com.bz.open.sharding.ucenter.dao.UserApplicationDAO;
import com.bz.open.sharding.ucenter.model.UserApplication;

@Repository
public class UserApplicationDAOImpl extends BaseDAOImpl<UserApplication> implements UserApplicationDAO {

	@Override
	public UserApplication getById(int id) {
		return get(id);
	}

}
