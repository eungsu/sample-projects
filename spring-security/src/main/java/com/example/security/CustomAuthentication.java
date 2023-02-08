package com.example.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class CustomAuthentication extends UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = 1L;
	private String userType;
	
	public CustomAuthentication(Object principal, Object credentials) {
		super(principal, credentials);
	}
	
	public String getUserType() {
		return userType;
	}
	
	public void setUserType(String userType) {
		this.userType = userType;
	}
}
