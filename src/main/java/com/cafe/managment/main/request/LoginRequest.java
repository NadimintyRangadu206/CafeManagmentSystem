package com.cafe.managment.main.request;

import java.io.Serializable;

public class LoginRequest  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String emailId;
	private String password;
	
	public LoginRequest() {
		
	}

	
	public LoginRequest(String emailId, String password) {
		super();
		this.emailId = emailId;
		this.password = password;
	}


	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
