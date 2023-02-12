package com.example.security;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.example.security.service.CustomEmployeeDetailsService;
import com.example.security.service.CustomUserDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	@Autowired
	private CustomEmployeeDetailsService employeeDetailsService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeHttpRequests()
		.antMatchers("/", "/user/register", "/user/registered", "/emp/register", "/emp/registered", "/login", "/logout").permitAll()
		.antMatchers("/h2-console/**").permitAll()
		.antMatchers("/emp/**").hasAnyRole("EMPLOYEE", "ADMIN")
		.antMatchers("/admin/**").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.loginProcessingUrl("/login")
		.and()
		.logout()
		.logoutUrl("/logout")
		.and()
		.addFilter(authenticationFilter())
		.exceptionHandling()
		.accessDeniedHandler(accessDeniedHandler())
		.and()
		.headers().frameOptions().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Bean
	public CustomAccessDeniedHandler accessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}
	
	@Bean
	public CustomAuthenticationFilter authenticationFilter() throws Exception {
		CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
		filter.setAuthenticationManager(authenticationManager());
		filter.setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler("/"));
		filter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/login?error=fail"));
		return filter;
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		Map<String, UserDetailsService> map = Map.of("user", userDetailsService, "employee", employeeDetailsService);
		return new CustomAuthenticationProvider(map, passwordEncoder);
	}
}
