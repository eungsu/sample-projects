package com.example.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.UserService;
import com.example.web.form.UserRegisterForm;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/register")
	public String form() {
		return "user/register-form";
	}
	
	@PostMapping("/register")
	public String register(UserRegisterForm userRegisterForm) {
		userService.saveUser(userRegisterForm);
		
		return "redirect:/user/registered";
	}
	
	@GetMapping("/registered")
	public String registered() {
		return "user/completed";
	}
}
