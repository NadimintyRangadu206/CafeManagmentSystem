package com.cafe.managment.main.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cafe.managment.main.model.UserInfo;

public class UserInfoUserDetails implements UserDetails {

	public int getUserId() {
		return userId;
	}


	private static final long serialVersionUID = 1L;
	
	     private int userId;
	     
	  private String userName;
	  
       private String password;
       
       private List<GrantedAuthority> authorities;
       
       
	
	public UserInfoUserDetails(UserInfo userInfo) {
	        userId=userInfo.getId();
	    userName=userInfo.getUserName();
	    password=userInfo.getPassword();
		authorities=Arrays.stream(userInfo.getRole().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
	}

	@Override
	public String getPassword() {
		
		return password;
	}

	@Override
	public String getUsername() {

		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
	
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
	
		return true;
	}

	@Override
	public boolean isEnabled() {
	
		return true;
	}

}
