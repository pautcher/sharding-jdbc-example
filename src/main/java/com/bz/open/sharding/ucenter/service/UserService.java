package com.bz.open.sharding.ucenter.service;

import com.bz.open.sharding.ucenter.model.User;
import com.bz.open.sharding.ucenter.model.UserApplication;

public interface UserService {

	User save(User user);

	UserApplication getUserApplication(int id);

}
