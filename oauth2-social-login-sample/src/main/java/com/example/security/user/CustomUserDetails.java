package com.example.security.user;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.vo.User;

public class CustomUserDetails extends LoginUser implements UserDetails {

	private static final long serialVersionUID = 4027385031456313960L;
	
	private String password;
	private Collection<GrantedAuthority> authorities;
	
	public CustomUserDetails(User user) {
		super(user);
		this.password = user.getPassword();
		this.authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRoleType()));
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
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
