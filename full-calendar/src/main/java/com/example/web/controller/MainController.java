package com.example.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		return "register-form";
	}
	
	@PostMapping("/register")
	public String register(String id) {
		return "redirect:/registered";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login-form";
	}
	
	@PostMapping("/login")
	public String login(String id, String password) {
		return "redirect:/";
	}
}
