package com.example.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;

import com.example.mapper.UserMapper;
import com.example.security.vo.CustomUserDetails;
import com.example.vo.Role;
import com.example.vo.User;

@Controller
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userMapper.getUserById(username);
		if (user == null) {
			throw new UsernameNotFoundException("사용자정보가 존재하지 않습니다.");
		}
		
		List<Role> roles = userMapper.getUserRolesByUserId(user.getId());

		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		
		return new CustomUserDetails(user.getId(), user.getEncryptPassword(), user.getName(), authorities);
	}
}
