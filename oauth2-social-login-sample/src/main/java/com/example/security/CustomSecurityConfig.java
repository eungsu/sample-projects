package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.security.service.CustomOAuth2UserService;
import com.example.security.service.CustomUserDetailsService;

@EnableWebSecurity
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private CustomOAuth2UserService customOAuth2UserService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/user/**").hasRole("USER")
			.antMatchers("/amdin/**").hasRole("ADMIN")
			.anyRequest().permitAll()
			.and()
				.formLogin()
				.loginPage("/loginform")
				.usernameParameter("id")
				.passwordParameter("password")
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/")
				.failureUrl("/loginform")
			.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/")
			.and()
				.oauth2Login()
				.loginPage("/loginform")
				.defaultSuccessUrl("/")
				.failureUrl("/loginform")
				.userInfoEndpoint()
				.userService(customOAuth2UserService);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
	}
	
}
