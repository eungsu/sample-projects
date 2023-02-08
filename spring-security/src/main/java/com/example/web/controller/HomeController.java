package com.example.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/register")
	public String form() {
		return "register-form";
	}
	
	@PostMapping("/register")
	public String register() {
		
		return "redirect:/registered";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login-form";
	}
}
