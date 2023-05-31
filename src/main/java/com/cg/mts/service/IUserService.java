package com.cg.mts.service;

import com.cg.mts.exception.UserCreationError;
import com.cg.mts.pojo.User;

public interface IUserService {

	public User addUser(User user) throws UserCreationError;

	public User removeUser(User user);
}
