package com.example.security.oauth.info;

import java.util.Map;

public class KakaoOAuth2UserInfo extends OAuth2UserInfo {

	public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
		super(attributes);
	}
	
	@Override
	public String getId() {
		return getAttributes().get("id").toString();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public String getName() {
		Map<String, Object> properties = (Map<String, Object>) getAttributes().get("properties");
		if (properties == null) {
			return null;
		}
		return (String) properties.get("nickname");
	}
	
	@Override
	public String getEmail() {
		return (String) getAttributes().get("account_email");
	}
	
}
