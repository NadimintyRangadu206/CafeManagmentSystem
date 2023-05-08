package com.cafe.managment.main.service;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cafe.managment.main.repository.UserRepository;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	private com.cafe.managment.main.model.User user;
	
	@Override
	public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
	
		user=userRepository.findByyEmailId(emailId);
		
		if(Objects.isNull(user)) {
			return new User(user.getEmailId(),user.getPassword(),new ArrayList<>());
			
		}else {
			throw new UsernameNotFoundException("User Not found...");
		}
		
	}
	
	public com.cafe.managment.main.model.User user(){
		
		return user;
	}

}
