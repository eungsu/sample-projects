package com.example.security.user;

import com.example.vo.User;

import lombok.Getter;

@Getter
public class LoginUser {

	private String id;
	private String nickname;
	private String email;
	
	public LoginUser(User user) {
		this.id = user.getId();
		this.nickname = user.getName();
		this.email = user.getEmail();
	}
}
