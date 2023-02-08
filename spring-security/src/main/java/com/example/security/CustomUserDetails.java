package com.example.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.service.LoginUser;

public class CustomUserDetails extends LoginUser implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private final String encryptPassword;
	private final Collection<? extends GrantedAuthority> authorities;
	
	public CustomUserDetails(String id, String encryptPassword, String userType, String name, Collection<? extends GrantedAuthority> authorities) {
		super(id, userType, name);
		this.encryptPassword = encryptPassword;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return encryptPassword;
	}

	@Override
	public String getUsername() {
		return getId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	
}
