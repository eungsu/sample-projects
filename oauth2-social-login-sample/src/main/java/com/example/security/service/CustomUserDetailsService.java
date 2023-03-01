package com.example.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.mapper.UserMapper;
import com.example.security.user.CustomUserDetails;
import com.example.vo.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User savedUser = userMapper.getUserById(username);
		if (savedUser == null) {
			throw new UsernameNotFoundException("사용자 정보가 존재하지 않습니다.");
		}
		return new CustomUserDetails(savedUser);
	}

}
