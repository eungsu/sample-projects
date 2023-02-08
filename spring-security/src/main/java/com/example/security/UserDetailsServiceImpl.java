package com.example.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;

import com.example.mapper.UserMapper;
import com.example.vo.User;

@Controller
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userMapper.getUserById(username);
		if (user == null) {
			throw new UsernameNotFoundException("사용자정보가 존재하지 않습니다.");
		}
		
		return new CustomUserDetails(user.getId(), user.getEncryptPassword(), user.getName(), List.of());
	}
}
