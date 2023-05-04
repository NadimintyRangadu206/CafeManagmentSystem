package com.cafe.managment.main.service;

import com.cafe.managment.main.model.User;
import com.cafe.managment.main.request.UserRequest;

public interface UserService {

	User saveUser(UserRequest userRequest);

}
