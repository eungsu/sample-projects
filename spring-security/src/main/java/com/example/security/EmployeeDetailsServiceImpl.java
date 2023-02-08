package com.example.security;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.mapper.EmployeeMapper;
import com.example.vo.Employee;

@Service
public class EmployeeDetailsServiceImpl implements UserDetailsService {

	@Autowired
	EmployeeMapper employeeMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee employee = employeeMapper.getEmployeeById(username);
		if (employee == null) {
			throw new UsernameNotFoundException("직원정보가 존재하지 않습니다.");
		}
		
		Collection<? extends GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(employee.getAuthority()));
		return new CustomUserDetails(employee.getId(), employee.getEncryptPassword(), employee.getName(), authorities);
	}
}
