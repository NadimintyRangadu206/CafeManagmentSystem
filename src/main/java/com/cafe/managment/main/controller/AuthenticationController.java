package com.cafe.managment.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe.managment.main.config.JwtTokenUtil;
import com.cafe.managment.main.request.LoginRequest;
import com.cafe.managment.main.service.CustomUserDetailsServiceImpl;

@RestController
@RequestMapping("api/v1/")
public class AuthenticationController {
	
	@Autowired
   private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private CustomUserDetailsServiceImpl customUserDetailsServiceImpl;
	
	private AuthenticationManager authenticationManager;
	
	@PostMapping("authenticate")
	public String authenticationUser(@RequestBody LoginRequest loginRequest) {
//		
//		ResponseObject responseObject =null;
//		
//		try {
//			final UserDetails userDetails=customUserDetailsServiceImpl.loadUserByUsername(loginRequest.getEmailId());
//			final String token=jwtTokenUtil.generateToken(userDetails);
//		}catch (Exception e) {
//			// TODO: handle exception
//		}
//		return responseObject;
		
		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
		
		if(authentication.isAuthenticated()) {
			return jwtTokenUtil.generateToken(loginRequest.getUserName());
			
		}else {
			throw new UsernameNotFoundException("Invalid user request !");
		}
		
		
	}

}
