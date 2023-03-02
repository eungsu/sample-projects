package com.example.security.oauth.exception;

public class OAuthProviderMissMatchException extends RuntimeException {

	private static final long serialVersionUID = 7938123176061362252L;

	public OAuthProviderMissMatchException(String message) {
		super(message);
	}
}
