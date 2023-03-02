package com.example.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.mapper.UserMapper;
import com.example.vo.User;
import com.example.web.form.UserRegisterForm;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void registerUser(UserRegisterForm form) {
		User savedUser = userMapper.getUserById(form.getId());
		if (savedUser != null) {
			throw new RuntimeException("아이디와 비밀번호가 이미 사용중입니다.");
		}
		
		User user = new User();
		BeanUtils.copyProperties(form, user);
		
		user.setPassword(passwordEncoder.encode(form.getPassword()));
		user.setProviderType("local");
		
		userMapper.insertUser(user);
	}

	public User getUser(String userId) {
		return userMapper.getUserById(userId);
	}
}
