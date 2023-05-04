package com.cafe.managment.main.request;

public class UserRequest {

	
	private String userName;
	
	private String emailId;

	private String password;

	private String status;

	private String role;

	public UserRequest() {
		super();
		
	}

	public UserRequest(String userName, String emailId, String password, String status, String role) {
		super();
		this.userName = userName;
		this.emailId = emailId;
		this.password = password;
		this.status = status;
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserRequest [userName=" + userName + ", emailId=" + emailId + ", password=" + password + ", status="
				+ status + ", role=" + role + "]";
	}
	
	
}
