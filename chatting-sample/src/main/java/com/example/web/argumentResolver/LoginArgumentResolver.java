package com.example.web.argumentResolver;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.example.annotation.Login;
import com.example.util.SessionUtils;

@Component
public class LoginArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		
		Login loginAnnotation = parameter.getParameterAnnotation(Login.class);
		if (loginAnnotation == null) {
			return null;
		}
		
		if (SessionUtils.getAttribute("LOGIN_EMPLOYEE") != null) {
			return SessionUtils.getAttribute("LOGIN_EMPLOYEE");
		}
		if (SessionUtils.getAttribute("LOGIN_CUSTOMER") != null) {
			return SessionUtils.getAttribute("LOGIN_CUSTOMER");
		}
		return null;
	}
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(Login.class);
	}
}
