package com.cafe.managment.main.service;

import com.cafe.managment.main.model.UserInfo;
import com.cafe.managment.main.request.LoginRequest;
import com.cafe.managment.main.request.UserRequest;
import com.cafe.managment.main.response.ResponseObject;

public interface UserService {

	UserInfo saveUser(UserRequest userRequest);

//	ResponseObject userLogin(LoginRequest request);

	
}
