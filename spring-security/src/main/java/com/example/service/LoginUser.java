package com.example.service;

public class LoginUser {

	private final String id;
	private final String userType;
	private final String name;
	
	public LoginUser(String id, String userType, String name) {
		this.id = id;
		this.userType = userType;
		this.name = name;
	}

	public String getId() {
		return id;
	}
	
	public String getUserType() {
		return userType;
	}

	public String getName() {
		return name;
	}

}
