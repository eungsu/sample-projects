package com.example.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.service.UserService;
import com.example.web.form.UserRegisterForm;

@Controller
public class MainController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/registerform")
	public String registerform() {
		return "register-form";
	}
	
	@PostMapping("/register")
	public String register(UserRegisterForm form) {
		userService.registerUser(form);
		return "redirect:/registered";
	}
	
	@GetMapping("/registered")
	public String registered() {
		return "registered";
	}
	
	@GetMapping("/loginform")
	public String loginform() {
		return "login-form";
	}
}
