package com.cafe.managment.main.service;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.cafe.managment.main.config.JwtTokenUtil;
import com.cafe.managment.main.exception.CafeException;
import com.cafe.managment.main.model.UserInfo;
import com.cafe.managment.main.repository.UserRepository;
import com.cafe.managment.main.request.LoginRequest;
import com.cafe.managment.main.request.UserRequest;
import com.cafe.managment.main.response.ResponseObject;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	public UserRepository userRepository;


	@Qualifier("authentication")
   AuthenticationManager authentication;

     @Autowired
	public CustomUserDetailsServiceImpl customUserDetailsServiceImpl;

	@Autowired
	public JwtTokenUtil jwtTokenUtil;

	@Override
	public UserInfo saveUser(UserRequest userRequest) {

		Optional<UserInfo> optional = userRepository.findByEmailId(userRequest.getEmailId());
		UserInfo user = null;

		if (optional.isPresent()) {

			try {
				user = optional.get();
				throw new CafeException(400, "EmailId is already exist");
			} catch (CafeException e) {
				e.getLocalizedMessage();
			}

		} else {

			if (userRequest != null) {
				user = new UserInfo();
			
			}

		}

		BeanUtils.copyProperties(userRequest, user);

		user.setStatus("true");
		user.setRole("Admin");
		user.getIsActive();
//		user.getUpdatedDate(new Date(0, 0, 0));
		UserInfo saveUser = userRepository.saveAndFlush(user);

		return saveUser;
	}
//
//	@Override
//	public ResponseObject userLogin(LoginRequest request) {
//
//		try {
//
//			Authentication auth = authentication
//					.authenticate(new UsernamePasswordAuthenticationToken(request.getEmailId(), request.getPassword()));
//
//			if (auth.isAuthenticated()) {
//				if (customUserDetailsServiceImpl.userInfo().getStatus().equalsIgnoreCase("true")) {
//
//					return new ResponseObject(
//							jwtTokenUtil.generateToken(customUserDetailsServiceImpl.user().getEmailId(),
//									customUserDetailsServiceImpl.user().getRole()),
//							HttpStatus.OK);
//				} else {
//					return new ResponseObject("Waiting for admin approval", HttpStatus.BAD_REQUEST);
//				}
//			}
//		} catch (CafeException e) {
//
//			throw new CafeException(400, "EmailId and Password is Wrong");
//		}
//		return new ResponseObject("Bad Request", HttpStatus.BAD_REQUEST);
//	}

}
