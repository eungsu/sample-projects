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
	
	public void saveUser(UserRegisterForm userRegisterForm) {
		User user = new User();
		BeanUtils.copyProperties(userRegisterForm, user);
		user.setEncryptPassword(passwordEncoder.encode(userRegisterForm.getPassword()));
		
		userMapper.insertUser(user);
	}
}
