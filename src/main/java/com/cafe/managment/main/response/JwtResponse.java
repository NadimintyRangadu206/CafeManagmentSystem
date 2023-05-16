package com.cafe.managment.main.response;

import java.io.Serializable;

public class JwtResponse implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private final int userId;
	private final String jwttoken;
	
	public JwtResponse(int userId,String jwttoken) {
		
	
		this.userId = userId;
		this.jwttoken = jwttoken;
		
	}
	
	public int getUserId() {
		return userId;
	}

	public String getToken() {
		return this.jwttoken;
	}
}
