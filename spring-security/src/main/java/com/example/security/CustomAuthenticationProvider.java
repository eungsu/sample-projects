package com.example.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomAuthenticationProvider implements AuthenticationProvider {

	private UserDetailsServiceImpl userDetailsService;
	private EmployeeDetailsServiceImpl employeeDetailsService;
	private PasswordEncoder passwordEncoder;
	
	public CustomAuthenticationProvider(UserDetailsServiceImpl userDetailsService, 
			EmployeeDetailsServiceImpl employeeDetailsService,
			PasswordEncoder passwordEncoder) {
		this.userDetailsService = userDetailsService;
		this.employeeDetailsService = employeeDetailsService;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		CustomAuthenticationToken customAuthentication = (CustomAuthenticationToken) authentication;
		System.out.println(customAuthentication.getName());
		System.out.println(customAuthentication.getCredentials());
		System.out.println(customAuthentication.getUserType());
		
		
		return null;
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return CustomAuthenticationToken.class.isAssignableFrom(authentication);
	}
	
}
