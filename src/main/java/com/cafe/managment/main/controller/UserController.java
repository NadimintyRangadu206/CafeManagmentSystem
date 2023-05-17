package com.cafe.managment.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe.managment.main.constatnts.ErrorMessages;
import com.cafe.managment.main.constatnts.SuccessMessages;
import com.cafe.managment.main.exception.CafeException;
import com.cafe.managment.main.model.UserInfo;
import com.cafe.managment.main.request.UserRequest;
import com.cafe.managment.main.response.ResponseObject;
import com.cafe.managment.main.service.UserService;

@RestController
@RequestMapping("api/v1/user/")
public class UserController extends BaseController {

	@Autowired
	public UserService userService;

	@PostMapping("save")
	public ResponseObject saveUser(@RequestBody UserRequest userRequest) {

		ResponseObject responseObject = null;

		UserInfo user = userService.saveUser(userRequest);
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

	@GetMapping("get/all/users")
	public ResponseObject getAllUsers() {

		ResponseObject responseObject = null;

		try {
			List<UserInfo> listOfUser = userService.getAllUsers();

			responseObject = getResponse(listOfUser);

		} catch (Exception e) {
			responseObject = new ResponseObject(null, e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
		}
		return responseObject;

	}
}
