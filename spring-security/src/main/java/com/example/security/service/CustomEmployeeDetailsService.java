package com.example.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.mapper.EmployeeMapper;
import com.example.security.vo.CustomUserDetails;
import com.example.vo.Employee;
import com.example.vo.Role;

@Service
public class CustomEmployeeDetailsService implements UserDetailsService {

	@Autowired
	EmployeeMapper employeeMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee employee = employeeMapper.getEmployeeById(username);
		if (employee == null) {
			throw new UsernameNotFoundException("직원정보가 존재하지 않습니다.");
		}
		
		List<Role> roles = employeeMapper.getEmployeeRolesByEmployeeId(employee.getId());
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		
		return new CustomUserDetails(employee.getId(), employee.getEncryptPassword(), employee.getName(), authorities);
	}
}
