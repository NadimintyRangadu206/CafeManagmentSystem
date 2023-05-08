package com.cafe.managment.main.service;

import com.cafe.managment.main.model.User;
import com.cafe.managment.main.request.LoginRequest;
import com.cafe.managment.main.request.UserRequest;
import com.cafe.managment.main.response.ResponseObject;

public interface UserService {

	User saveUser(UserRequest userRequest);

	ResponseObject userLogin(LoginRequest request);

	
}
