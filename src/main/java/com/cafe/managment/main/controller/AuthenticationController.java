package com.cafe.managment.main.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe.managment.main.config.JwtTokenUtil;
import com.cafe.managment.main.config.UserInfoUserDetails;
import com.cafe.managment.main.exception.CafeException;
import com.cafe.managment.main.model.UserInfo;
import com.cafe.managment.main.repository.UserRepository;
import com.cafe.managment.main.request.LoginRequest;
import com.cafe.managment.main.response.JwtResponse;
import com.cafe.managment.main.response.ResponseObject;
import com.cafe.managment.main.service.CustomUserDetailsServiceImpl;

@RestController
@RequestMapping("api/v1/")
public class AuthenticationController {
	
	@Autowired
   public  JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	public CustomUserDetailsServiceImpl customUserDetailsServiceImpl;
	
	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public AuthenticationManager authenticationManager;
	
	@Autowired
	public BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostMapping("authenticate")
	public ResponseObject authenticationUser(@RequestBody LoginRequest loginRequest) {
		
		ResponseObject responseObject =null;
		
		try {
			final UserDetails userDetails=customUserDetailsServiceImpl.loadUserByUsername(loginRequest.getUserName());
			final String token=jwtTokenUtil.generateToken(userDetails);
			Optional<UserInfo> userOptional=userRepository.findByUserName(loginRequest.getUserName());
			
			if(userOptional.isPresent()) {
				
				if ("N".equalsIgnoreCase(userOptional.get().getIsActive())) {
					throw new CafeException(400, "User is Deactivated");
				}
			}
			
			UserInfoUserDetails user=(UserInfoUserDetails) userDetails;
			
			if (userOptional.isPresent()) {
              
				authenticate(loginRequest.getUserName(),loginRequest.getPassword(),userDetails,userOptional.get());
				
			}
			responseObject= new ResponseObject(new JwtResponse(user.getUserId(), token));
			
		}catch (Exception e) {
			responseObject = new ResponseObject(null, e.getMessage(), HttpStatus.BAD_REQUEST);
		}
			
		return responseObject;
	}

	private void authenticate(String userName, String password, UserDetails userDetails, UserInfo userInfo) {
	
		boolean isPasswordMatch = bCryptPasswordEncoder.matches(password, userDetails.getPassword());
		if (isPasswordMatch && userName.equalsIgnoreCase(userDetails.getUsername())) {
				
				userRepository.saveAndFlush(userInfo);
				
		} else {
	
			userRepository.saveAndFlush(userInfo);
			
			
		}
		
	}

}
