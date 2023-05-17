package com.cafe.managment.main.request;

public class ChangeUserPasswordRequest {

	private int id;
	private String newPassword;
	private String oldPassword;
	private String confirmPassword;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	
	@Override
	public String toString() {
		return "ChangeUserPasswordRequest [id=" + id + ", newPassword=" + newPassword + ", oldPassword=" + oldPassword
				+ ", confirmPassword=" + confirmPassword + "]";
	}
	
	
}
