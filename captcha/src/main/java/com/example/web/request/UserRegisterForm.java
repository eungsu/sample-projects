package com.example.web.request;

public class UserRegisterForm {

	private String email;
	private String password;
	private String name;
	private String captchaKey;
	private String captchaValue;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCaptchaKey() {
		return captchaKey;
	}
	public void setCaptchaKey(String captchaKey) {
		this.captchaKey = captchaKey;
	}
	public String getCaptchaValue() {
		return captchaValue;
	}
	public void setCaptchaValue(String captchaValue) {
		this.captchaValue = captchaValue;
	}
	
	
}
