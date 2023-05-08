package com.cafe.managment.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe.managment.main.constatnts.ErrorMessages;
import com.cafe.managment.main.constatnts.SuccessMessages;
import com.cafe.managment.main.exception.CafeException;
import com.cafe.managment.main.model.User;
import com.cafe.managment.main.request.LoginRequest;
import com.cafe.managment.main.request.UserRequest;
import com.cafe.managment.main.response.ResponseObject;
import com.cafe.managment.main.service.UserService;

@RestController
@RequestMapping("api/v1/user/")
public class UserController {

	@Autowired
	public UserService userService;

	@PostMapping("save")
	public ResponseObject saveUser(@RequestBody UserRequest userRequest) {

		ResponseObject responseObject = null;

		User user = userService.saveUser(userRequest);
		try {
			if (user != null) {
				responseObject = new ResponseObject(SuccessMessages.USER_IS_ADDED_SUCCESSFULLY, HttpStatus.OK);
			} else {
				responseObject = new ResponseObject(null, ErrorMessages.SOMETTHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
			}
		} catch (CafeException e) {
			responseObject = new ResponseObject(null, e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return responseObject;
	}

	@PostMapping("/login")
	public ResponseObject userLogin(@RequestBody LoginRequest request) {

		ResponseObject responseObject = null;

		

		try {
		
		responseObject = new ResponseObject(userService.userLogin(request),SuccessMessages.USER_IS_LONG_IN_SUCCESSFULLY, HttpStatus.OK);	
			
			
		} catch (CafeException e) {
			responseObject = new ResponseObject(null, e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return responseObject;

	}
}
