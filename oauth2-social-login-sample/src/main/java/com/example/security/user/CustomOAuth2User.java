package com.example.security.user;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.example.vo.User;

public class CustomOAuth2User extends LoginUser implements OAuth2User {

	private String providerType;
	private Collection<GrantedAuthority> authorities;
	private Map<String, Object> attributes;
	
	public CustomOAuth2User(User user, Map<String, Object> attributes) {
		super(user);
		this.providerType = user.getProviderType();
		this.authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRoleType()));
		this.attributes = attributes;
	}
	
	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	@Override
	public String getName() {
		return getId();
	}
	
	public String getProviderType() {
		return providerType;
	}
}
