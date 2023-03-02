package com.example.security.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.mapper.UserMapper;
import com.example.security.oauth.exception.OAuthProviderMissMatchException;
import com.example.security.oauth.info.OAuth2UserInfo;
import com.example.security.oauth.info.OAuth2UserInfoFactory;
import com.example.security.user.CustomOAuth2User;
import com.example.vo.User;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
		OAuth2User oAuth2User = delegate.loadUser(userRequest);
		
		String providerType = userRequest.getClientRegistration().getRegistrationId();
		OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(providerType, oAuth2User.getAttributes());
		User savedUser = userMapper.getUserById(userInfo.getId());
		
		if (savedUser != null) {
			if (!providerType.equals(savedUser.getProviderType())) {
				throw new OAuthProviderMissMatchException("소셜로그인 공급자가 일치하지 않습니다.");
			}
			updateUser(savedUser, userInfo);
		} else {
			savedUser = createUser(userInfo, providerType);
		}
		
		return new CustomOAuth2User(savedUser, userInfo.getAttributes());
	}
	
	private User createUser(OAuth2UserInfo userInfo, String providerType) {
		User user = new User();
		user.setId(userInfo.getId());
		user.setName(userInfo.getName());
		user.setEmail(userInfo.getEmail());
		user.setProviderType(providerType);
		user.setRoleType("ROLE_USER");
		user.setCreatedDate(new Date());
		user.setUpdatedDate(new Date());
		
		userMapper.insertUser(user);
		
		return user;
	}
	
	private User updateUser(User user, OAuth2UserInfo userInfo) {
		if (userInfo.getName() != null && !user.getName().equals(userInfo.getName())) {
			user.setName(userInfo.getName());
		}
		userMapper.updateUser(user);
		
		return user;
	}
}
