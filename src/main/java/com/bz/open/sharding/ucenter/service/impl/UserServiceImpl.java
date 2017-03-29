package com.bz.open.sharding.ucenter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bz.open.sharding.ucenter.dao.UserDAO;
import com.bz.open.sharding.ucenter.model.User;
import com.bz.open.sharding.ucenter.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public User save(User user) {
		return userDAO.save(user);

	}

}
