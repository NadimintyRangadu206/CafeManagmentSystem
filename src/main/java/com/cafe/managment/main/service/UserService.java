package com.cafe.managment.main.service;

import java.util.List;

import com.cafe.managment.main.model.UserInfo;
import com.cafe.managment.main.request.ChangeUserPasswordRequest;
import com.cafe.managment.main.request.UserRequest;

public interface UserService {

	UserInfo saveUser(UserRequest userRequest);

	List<UserInfo> getAllUsers();

	UserRequest getUserById(int id);

	UserInfo changeUserPassword(ChangeUserPasswordRequest changeUserPasswordRequest);

//	ResponseObject userLogin(LoginRequest request);

	
}
