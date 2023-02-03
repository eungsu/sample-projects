package com.example.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.service.UserService;
import com.example.web.request.UserRegisterForm;

@Controller
public class MainController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/register")
	public String registerform() {
		return "register-form";
	}
	
	@PostMapping("/register")
	public String register(UserRegisterForm userRegisterForm) {
		userService.registerUser(userRegisterForm);
		return "redirect:/registered";
	}
	
	@GetMapping("/registered")
	public String completed() {
		return "completed";
	}
	
}