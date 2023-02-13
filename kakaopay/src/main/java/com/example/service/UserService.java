package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mapper.UserMapper;
import com.example.vo.User;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	public void addNewUser(User user) {
		User savedUser = userMapper.getUserByEmail(user.getEmail());
		if (savedUser != null) {
			throw new RuntimeException("이미 가입된 이메일입니다.");
		}
		userMapper.insertUser(user);
	}
	
}
